package fpInScala.dataStructures.parallel

object Par {
  def unit [A] (a: => A): Par[A] = ???

  def get [A] (a: Par[A]): A = ???
}

trait Par [A] { }
