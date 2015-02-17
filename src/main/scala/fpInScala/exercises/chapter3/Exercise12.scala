package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list.List

object Exercise12 {
  // Write a function that returns the reverse of a list (given List(1,2,3) it returns List(3,2,1)).
  // See if you can write it using a fold.

  // The implementation for this function can be found on the List companion object
  def reverse [A] (as: List[A]): List[A] = List.reverse(as)
}
