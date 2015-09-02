package fpInScala.dataStructures.parallel

import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.{CountDownLatch, Callable, ExecutorService}

object Par {
  sealed trait Future [A] {
    private[parallel] def apply (onNext: A => Unit, onError: Throwable => Unit): Unit
  }

  // The simplest possible model for Par[A] might be ExecutorService => A.
  // This would obviously make run trivial to implement. But it might be nice
  // to defer the decision of how long to wait for a computation, or whether
  // to cancel it, to the caller of run. So Par[A] becomes ExecutorService => Future[A],
  // and run simple returns the Future:
  type Par[A] = ExecutorService => Future[A]

  // Simply passes the value to the continuation. Note that ExecutorService isn't needed.
  def unit [A] (a: A): Par[A] = es => new Future[A] {
    def apply (onNext: A => Unit, onError: Throwable => Unit = throw _): Unit = onNext(a)
  }

  // We can now implement map2 using an Actor to collect the result from both arguments. The code is straightforward,
  // and there are no race conditions to worry about, since we know
  // that the Actor will only process one message at a time.
  def map2 [A, B, C] (p1: Par[A], p2: Par[B]) (f: (A, B) => C): Par[C] =
    es => new Future[C] {
      def apply (onNext: (C) => Unit, onError: Throwable => Unit): Unit = {
        var ar: Option[A] = None
        var br: Option[B] = None

        // An actor that awaits both results, combines them with f, and passes the result to onNext.
        val combiner = Actor[Either[A, B]] (es) {
          // If the A result came in first, stores it in ar and waits for the B. If the A result came last and we
          // already have our B, calls f with both results and passes the resulting C to the callback, onNext
          case Left(a) => br match {
            case None => ar = Some(a)
            case Some(b) => eval(es) (onNext(f(a, b)))
          }

          // Analogously, if the B result came in first, stores it in br and waits for the A. If the B result came
          // last and we already have our A, calls f with both results and passes the resulting C to the callback,
          // onNext.
          case Right(b) => ar match {
            case None => br = Some(b)
            case Some(a) => eval(es) (onNext(f(a, b)))
          }
        }

        // Passes the actor as a continuation to both sides. On the A side, we wrap the result in Left, and on the B
        // side, we wrap it in Right. These are the constructors of the Either data type, and they serve to indicate
        // to the actor where the result came from.
        p1(es)(a => combiner ! Left(a), onError)
        p2(es)(b => combiner ! Right(b), onError)
      }
    }

  // We can "lift" any function of type A => B to become a function that takes Par[A] and returns Par[B];
  // we can map any function over a Par:
  def map [A, B] (a: Par[A]) (f: A => B): Par[B] = map2(a, unit(())) ((a,_) => f(a))

  def parMap [A, B] (ps: List[A]) (f: A => B): Par[List[B]] = sequence(ps.map(asyncF(f)))

  // Implement parFilter, which filters elements of a list in parallel.
  def parFilter [A] (as: List[A]) (f: A => Boolean): Par[List[A]] = {
    map(sequence(as map asyncF(a => if (f(a)) List(a) else Nil))) (_.flatten)
  }

  // Hard: Write this function, called sequence. No additional primitives are required. Do not call run.
  def sequence [A] (ps: List[Par[A]]): Par[List[A]] = ps.foldRight[Par[List[A]]] (unit(Nil)) ((h, t) => map2(h, t)(_ :: _))

  // Eval forks off evaluation of a and returns immediately.
  // The callback will be invoked asynchronously on another thread.
  def fork [A] (a: => Par[A]): Par[A] = es => new Future[A] {
    def apply (onNext: A => Unit, onError: Throwable => Unit = throw _): Unit =
      eval(es) (a(es)(onNext, onError))
  }

  // A helper function to evaluate an action asynchronously using some ExecutorService
  def eval (es: ExecutorService) (r: => Unit): Unit = es.submit(new Callable[Unit] { def call = r })

  // For working on fixed-size threadpools, this certainly avoids deadlock. The only problem is that we aren't actually
  // forking a separate logical thread to evaluate fa. So delay(hugeComputation)(es) for some ExecutorService es, would
  // run hugeComputation in the main thread, which is exactly what we wanted to avoid. This is still a useful
  // combinator, though, since it lets us delay instantiation of a computation until it's actually needed.
  def delay [A] (fa: => Par[A]): Par[A] = es => fa(es)

  // Wraps its unevaluated argument in a Par and marks it for concurrent evaluation by run
  def lazyUnit [A] (a: => A): Par[A] = fork(unit(a))

  def run [A] (es: ExecutorService) (p: Par[A]): A = {
    val ref = new AtomicReference[A]
    val latch = new CountDownLatch(1)
    p(es)({ a => ref.set(a); latch.countDown() }, { e => latch.countDown(); throw e })
    latch.await()
    ref.get
  }

  def sum (ints: IndexedSeq[Int]): Par[Int] =
    if (ints.size <= 1) {
      Par.unit(ints.headOption getOrElse 0)
    } else {
      val (l, r) = ints.splitAt(ints.length / 2)
      Par.map2(Par.fork(sum(l)), Par.fork(sum(r))) (_ + _)
    }

  // This API already enables a rich set of operations. Here's a simple example: using lazyUnit,
  // write a function to convert any function A => B to one that evaluates its result asynchronously.
  def asyncF [A, B] (f: A => B): A => Par[B] = a => lazyUnit(f(a))

  // Why just two? If it's useful to be able to choose between two parallel computations based on the results
  // of a first, it should be certainly be useful to choose between N computations:
  def choiceN [A] (n: Par[Int]) (choices: List[Par[A]]): Par[A] = es => choices(run(es)(n))(es)

  // Re-implemented in terms of choiceN
  def choice [A] (cond: Par[Boolean]) (t: Par[A], f: Par[A]): Par[A] = choiceN(map(cond)(c => if (c) 0 else 1)) (List(t, f))

  def choiceMap [K, V] (key: Par[K]) (choices: Map[K, Par[V]]): Par[V] = es => choices(run(es)(key))(es)
}
