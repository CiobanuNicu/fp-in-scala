package fpInScala.dataStructures.parallel

import java.util.concurrent.{Callable, TimeUnit, Future, ExecutorService}

object Par {
  // The simplest possible model for Par[A] might be ExecutorService => A.
  // This would obviously make run trivial to implement. But it might be nice
  // to defer the decision of how long to wait for a computation, or whether
  // to cancel it, to the caller of run. So Par[A] becomes ExecutorService => Future[A],
  // and run simple returns the Future:
  type Par[A] = ExecutorService => Future[A]

  // Creates a computation that immediately results in the value a,
  // promoting a constant value to a parallel computation.
  // Unit is represented as a function that returns a UnitFuture, which
  // is a simple implementation of Future that just wraps a constant
  // value. It doesn't use the ExecutorService at all. It's always done
  // and can't be canceled. Its get method simply returns the value we gave it.
  def unit [A] (a: A): Par[A] = (es: ExecutorService) => UnitFuture(a)

  private case class UnitFuture [A] (get: A) extends Future[A] {
    def isDone: Boolean = true
    def get (timeout: Long, unit: TimeUnit): A = get
    def isCancelled: Boolean = false
    def cancel (mayInterruptIfRunning: Boolean): Boolean = false
  }

  // Combines the result of two parallel computations with a binary function
  // Here, map2 doesn't evaluate the call to f in a separate logical thread,
  // in accord with out design choice of having fork be the sole function in
  // the API for controlling parallelism. We can always do fork(map2(a,b)(f))
  // if we want the evaluation of f to occur in a separate thread
  def map2 [A, B, C] (a: Par[A], b: Par[B]) (f: (A, B) => C): Par[C] =
    (es: ExecutorService) => {
      val (af, bf) = (a(es), b(es))
      Map2Future(af, bf, f)
    }

  case class Map2Future [A, B, C] (a: Future[A], b: Future[B], f: (A, B) => C) extends Future[C] {
    @volatile var cache: Option[C] = None

    def isDone: Boolean = cache.isDefined
    def isCancelled: Boolean = a.isCancelled || b.isCancelled

    def cancel (mayInterruptIfRunning: Boolean): Boolean = a.cancel(mayInterruptIfRunning) || b.cancel(mayInterruptIfRunning)

    def get (): C = compute(Long.MaxValue)
    def get (timeout: Long, unit: TimeUnit): C = compute(TimeUnit.NANOSECONDS.convert(timeout, unit))

    private def compute (timeoutInNanoseconds: Long): C = cache match {
      case Some(c) => c
      case None =>
        val start = System.nanoTime
        val ar = a.get(timeoutInNanoseconds, TimeUnit.NANOSECONDS)
        val stop = System.nanoTime

        val aTime = stop - start
        val br = b.get(timeoutInNanoseconds - aTime, TimeUnit.NANOSECONDS)
        val ret = f(ar, br)

        cache = Some(ret)
        ret
    }

  }

  // Marks a computation for concurrent evaluation by run.
  // The evaluation won’t actually occur until forced by run.
  // This is the simplest and most natural implementation of fork, but there are some problems with it --
  // for one, the outer Callable will block waiting for the "inner" task to complete. Since this blocking
  // occupies a thread in our thread pool, or whatever resource backs the ExecutorService, this implies
  // that we're losing out on some potential parallelism. Essentially, we're using two threads when one
  // should suffice. This is a symptom of a more serious problem with the implementation that we'll discuss
  // later on.
  def fork [A] (a: => Par[A]): Par[A] = es => es.submit(new Callable [A] { def call (): A = a(es).get })

  // Wraps its unevaluated argument in a Par and marks it for concurrent evaluation by run
  def lazyUnit [A] (a: => A): Par[A] = fork(unit(a))

  // Fully evaluates a given Par, spawning parallel computations
  // as requested by fork and extracting the resulting value.
  // Note that since Par is represented as a function that needs an ExecutorService,
  // the creation of the Future doesn't actually happen until this ExecutorService is
  // provided. Is it really that simple? Let's assume it is for now, and revise our model
  // if we find it doesn't allow some functionality we'd like.
  def run [A] (es: ExecutorService) (a: Par[A]): Future[A] = a(es)

  def sum (ints: IndexedSeq[Int]): Par[Int] =
    if (ints.size <= 1) {
      Par.unit(ints.headOption getOrElse 0)
    } else {
      val (l, r) = ints.splitAt(ints.length / 2)
      Par.map2(Par.fork(sum(l)), Par.fork(sum(r))) (_ + _)
    }
}
