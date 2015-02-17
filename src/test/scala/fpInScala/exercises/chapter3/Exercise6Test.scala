package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list._
import fpInScala.exercises.chapter3.Exercise6._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise6Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.6 Solution" should "return Nil for Nil" in {
    init(Nil) should be (Nil)
  }

  it should "return Nil for a list of 1 element" in {
    init(List(1)) should be (Nil)
  }

  it should "return List(1) for List(1, 2)" in {
    init(List(1, 2)) should be (List(1))
  }
}
