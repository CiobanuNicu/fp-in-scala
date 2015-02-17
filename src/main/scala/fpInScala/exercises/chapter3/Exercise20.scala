package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list._

object Exercise20 {
  // Write a function flatMap that works like map except that the function given will return a list
  // instead of a single result, and that list should be inserted into the final resulting list.

  // Once again, I defer to the implementation in List
  def flatMap [A, B] (as: List[A]) (f: A => List[B]): List[B] = List.flatMap(as)(f)
}
