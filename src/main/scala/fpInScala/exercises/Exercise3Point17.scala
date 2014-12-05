package fpInScala.exercises

import fpInScala.dataStructures._
import fpInScala.dataStructures.List._

object Exercise3Point17 {
  // Write a function that turns each value in a List[Double] into a String.
  // You can use the expression d.toString to convert some d: Double to a String.

  def toStrings (ds: List[Double]): List[String] = foldRight(ds, Nil: List[String]) ((a, b) => Cons(a.toString, b))
}
