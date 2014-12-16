package fpInScala.exercises.chapter3

import fpInScala.dataStructures._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise12Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.12 Solution" should "be able to reverse the empty list" in {
    Exercise12.reverse(Nil) should be (Nil)
  }

  it should "be able to reverse a list of 1 element" in {
    Exercise12.reverse(List(1)) should be (List(1))
  }

  it should "be able to reverse a list of 2 elements" in {
    Exercise12.reverse(List(1, 2)) should be (List(2, 1))
  }

  it should "be able to reverse a list of 3 elements" in {
    Exercise12.reverse(List(1, 2, 3)) should be (List(3, 2, 1))
  }
}
