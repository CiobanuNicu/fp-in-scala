package fpInScala.dataStructures.parallel

object Par {
  def unit [A] (a: => A): Par[A] = ???

  def get [A] (a: Par[A]): A = ???

  def map2 [A, B, C] (parL: Par[A], parR: Par[B]) (f: (A, B) => C): Par[C] = ???
}

trait Par [A] { }
