package fpInScala.exercises

import fpInScala.dataStructures._

object Exercise3Point23 {
  // Generalize the function you just wrote so that it's not specific to integers or addition.
  // Name your generalized function zipWith.

  // Again, I'll defer to the implementation on List
  def zipWith [A, B] (list1: List[A], list2: List[A]) (f: (A, A) => B): List[B] = List.zipWith(list1, list2)(f)
}
