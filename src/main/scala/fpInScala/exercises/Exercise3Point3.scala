package fpInScala.exercises

import fpInScala.dataStructures._

object Exercise3Point3 {
  // Using the same idea, implement the function setHead for replacing
  // the first element of a List with a different value.

  def setHead [A] (as: List[A], h: A): List[A] = as match {
    case Nil => Cons(h, Nil)
    case Cons(r, t) => Cons(h, t)
  }
}
