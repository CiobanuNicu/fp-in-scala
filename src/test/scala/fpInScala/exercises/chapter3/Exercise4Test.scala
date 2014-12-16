package fpInScala.exercises.chapter3

import fpInScala.dataStructures._
import fpInScala.exercises.chapter3.Exercise4._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise4Test extends FlatSpec with ShouldMatchers {

  "An Exercise 3.4 Solution" should "return the original list when n <= 0" in {
    drop(List(1, 2, 3), 0) should be (List(1, 2, 3))
  }

  it should "return Nil when n is greater than list length" in {
    drop(List(1, 2, 3), 5) should be (Nil)
  }

  it should "equal the behavior of tail when n is 1" in {
    drop(List(1, 2, 3), 1) should be (Exercise2.tail(List(1, 2, 3)))
  }

  it should "return Nil when n is equal to list length" in {
    drop(List(1, 2, 3), 3) should be (Nil)
  }

  it should "return List(3) for List(1, 2, 3), 2" in {
    drop(List(1, 2, 3), 2) should be (List(3))
  }
}
