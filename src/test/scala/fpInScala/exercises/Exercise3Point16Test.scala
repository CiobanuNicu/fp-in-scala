package fpInScala.exercises

import org.scalatest.{FlatSpec, ShouldMatchers}
import fpInScala.dataStructures._

class Exercise3Point16Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.16 Solution" should "increment Nil to Nil" in {
    Exercise3Point16.increment(Nil) should be (Nil)
  }

  it should "increment a single-element list" in {
    Exercise3Point16.increment(List(1)) should be (List(2))
  }

  it should "increment a multiple-element list" in {
    Exercise3Point16.increment(List(1, 2, 3, 4, 5)) should be (List(2, 3, 4, 5, 6))
  }
}
