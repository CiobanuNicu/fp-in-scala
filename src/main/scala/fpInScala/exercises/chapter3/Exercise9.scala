package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list._

object Exercise9 {
  // Compute the length of a list using foldRight.

  // The implementation I'll put on the List companion object itself.
  def length [A] (as: List[A]): Int = List.length(as)
}
