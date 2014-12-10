package fpInScala.exercises

import fpInScala.dataStructures._

object Exercise3Point21 {
  // Use flatMap to implement filter.
  def filter [A] (as: List[A]) (f: A => Boolean): List[A] = List.flatMap(as)(a => if (f(a)) List(a) else Nil)
}
