package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list.List._
import fpInScala.dataStructures.list._

object Exercise17 {
  // Write a function that turns each value in a List[Double] into a String.
  // You can use the expression d.toString to convert some d: Double to a String.

  def toStrings (ds: List[Double]): List[String] = foldRight(ds, Nil: List[String]) ((a, b) => Cons(a.toString, b))
}
