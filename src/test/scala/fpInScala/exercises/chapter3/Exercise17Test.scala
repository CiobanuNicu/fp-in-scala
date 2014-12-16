package fpInScala.exercises.chapter3

import fpInScala.dataStructures._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise17Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.17 Solution" should "convert Nil to Nil" in {
    Exercise17.toStrings(Nil) should be (Nil)
  }

  it should "convert a single-element list to strings" in {
    Exercise17.toStrings(List(1.0)) should be (List("1.0"))
  }

  it should "convert a multiple-element list to strings" in {
    Exercise17.toStrings(List(1.2, 3.4, 5.6, 7.8)) should be (List("1.2", "3.4", "5.6", "7.8"))
  }
}
