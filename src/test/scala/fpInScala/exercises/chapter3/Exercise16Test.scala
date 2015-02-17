package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise16Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.16 Solution" should "increment Nil to Nil" in {
    Exercise16.increment(Nil) should be (Nil)
  }

  it should "increment a single-element list" in {
    Exercise16.increment(List(1)) should be (List(2))
  }

  it should "increment a multiple-element list" in {
    Exercise16.increment(List(1, 2, 3, 4, 5)) should be (List(2, 3, 4, 5, 6))
  }
}
