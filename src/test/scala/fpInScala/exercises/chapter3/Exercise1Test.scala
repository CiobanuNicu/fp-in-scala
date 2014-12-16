package fpInScala.exercises.chapter3

import fpInScala.exercises.chapter3.Exercise1._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise1Test extends FlatSpec with ShouldMatchers {

  "An Exercise 3.1 Solution" should "return the correct value for x out of a complex pattern match" in {
    x should be (3)
  }
}
