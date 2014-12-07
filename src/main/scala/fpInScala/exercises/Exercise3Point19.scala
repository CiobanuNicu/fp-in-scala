package fpInScala.exercises

import fpInScala.dataStructures._

object Exercise3Point19 {
  // Write a function filter that removes elements from a list unless they satisfy a given predicate.
  // Use it to remove all odd numbers from a List[Int].

  // Once again, I defer to the implementation in List.filter
  def filter [A] (as: List[A]) (f: A => Boolean): List[A] = List.filter(as)(f)
}
