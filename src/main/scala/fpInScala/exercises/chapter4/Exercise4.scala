package fpInScala.exercises.chapter4

import fpInScala.dataStructures._

object Exercise4 {
  // Write a function sequence that combines a list of Options into one Option containing a list of all the Some values
  // in the original list. If the original list contains None even once, the result of the function should be None;
  // otherwise the result should be Some with a list of all the values.

  // Defer to the implementation on Option
  def sequence[A](a: List[Option[A]]): Option[List[A]] = Option.sequence(a)
}
