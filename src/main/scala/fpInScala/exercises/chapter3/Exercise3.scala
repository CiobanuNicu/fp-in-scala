package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list._

object Exercise3 {
  // Using the same idea, implement the function setHead for replacing
  // the first element of a List with a different value.

  def setHead [A] (as: List[A], h: A): List[A] = as match {
    case Nil => Cons(h, Nil)
    case Cons(r, t) => Cons(h, t)
  }
}
