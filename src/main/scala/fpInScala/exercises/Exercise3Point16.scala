package fpInScala.exercises

import fpInScala.dataStructures._
import fpInScala.dataStructures.List._

object Exercise3Point16 {
  // Write a function that transforms a list of integers by adding 1 to each element.
  // (Reminder: this should be a pure function that returns a new List!)

  def increment (ints: List[Int]): List[Int] = foldRight(ints, Nil: List[Int]) ((a, b) => Cons(a + 1, b))
}