package fpInScala.exercises.chapter3

import fpInScala.dataStructures._

object Exercise2 {
  // Implement the function tail for removing the first element of a List. Note that the function takes constant time.
  // What are different choices you could make in your implementation if the List is Nil? We'll return to this question
  // in the next chapter.

  // If as is Nil, we could either:
  // * return Nil, or
  // * throw an Exception
  // * something else? not sure what else . . .
  def tail [A] (as: List[A]): List[A] = as match {
    // I prefer returning Nil
    case Nil => Nil
    case Cons(h, t) => t
  }
}
