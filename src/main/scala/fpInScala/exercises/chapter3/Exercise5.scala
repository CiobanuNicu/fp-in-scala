package fpInScala.exercises.chapter3

import fpInScala.dataStructures._

object Exercise5 {
  // Implement dropWhile, which removes elements from the List prefix as long as they match a predicate.

  @annotation.tailrec
  def dropWhile [A] (l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t)(f)
    case _ => l
  }
}
