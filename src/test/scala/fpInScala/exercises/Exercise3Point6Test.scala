package fpInScala.exercises

import org.scalatest.{FlatSpec, ShouldMatchers}
import fpInScala.dataStructures._
import fpInScala.exercises.Exercise3Point6._

class Exercise3Point6Test extends FlatSpec with ShouldMatchers {
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
