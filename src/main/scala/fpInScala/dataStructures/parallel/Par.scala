package fpInScala.dataStructures.parallel

object Par {
  // Creates a computation that immediately results in the value a,
  // promoting a constant value to a parallel computation.
  def unit [A] (a: A): Par[A] = ???

  // Combines the result of two parallel computations with a binary function
  def map2 [A, B, C] (parL: Par[A], parR: Par[B]) (f: (A, B) => C): Par[C] = ???

  // Marks a computation for concurrent evaluation by run.
  // The evaluation won’t actually occur until forced by run.
  def fork [A] (a: => Par[A]): Par[A] = ???

  // Wraps its unevaluated argument in a Par and marks it for concurrent evaluation by run
  def lazyUnit [A] (a: => A): Par[A] = fork(unit(a))

  // Fully evaluates a given Par, spawning parallel computations
  // as requested by fork and extracting the resulting value.
  def run [A] (a: Par[A]): A = ???
}

trait Par [A] { }
