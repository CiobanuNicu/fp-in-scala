package fpInScala.exercises

import org.scalatest.{ShouldMatchers, FlatSpec}
import fpInScala.dataStructures._
import fpInScala.exercises.Exercise3Point2._

class Exercise3Point2Test extends FlatSpec with ShouldMatchers {

  "An Exercise 3.2 Solution" should "return Nil for an empty list" in {
    tail(Nil) should be (Nil)
  }

  it should "return Nil for List(1)" in {
    tail(List(1)) should be (Nil)
  }

  it should "return List(2, 3) for List(1, 2, 3)" in {
    tail(List(1, 2, 3)) should be (List(2, 3))
  }
}
