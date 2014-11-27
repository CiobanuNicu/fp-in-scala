package fpInScala.exercises

import org.scalatest.{FlatSpec, ShouldMatchers}
import Exercise3Point1._

class Exercise3Point1Test extends FlatSpec with ShouldMatchers {

  "An Exercise 3.1 Solution" should "return the correct value for x out of a complex pattern match" in {
    x should be (3)
  }
}
