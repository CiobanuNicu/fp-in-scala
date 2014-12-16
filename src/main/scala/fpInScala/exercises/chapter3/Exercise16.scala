package fpInScala.exercises.chapter3

import fpInScala.dataStructures.List._
import fpInScala.dataStructures._

object Exercise16 {
  // Write a function that transforms a list of integers by adding 1 to each element.
  // (Reminder: this should be a pure function that returns a new List!)

  def increment (ints: List[Int]): List[Int] = foldRight(ints, Nil: List[Int]) ((a, b) => Cons(a + 1, b))
}