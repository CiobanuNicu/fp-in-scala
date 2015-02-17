package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list.List

object Exercise15 {
  // Hard: Write a function that concatenates a list of lists into a single list. Its runtime should be linear
  // in the total length of all lists. Try to use functions we have already defined.

  // Once again, we defer to the implementation on List
  def concatenate [A] (ls: List[List[A]]): List[A] = List.concatenate(ls)
}
