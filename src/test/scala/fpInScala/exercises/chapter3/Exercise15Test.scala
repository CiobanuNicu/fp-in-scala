package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise15Test extends FlatSpec with ShouldMatchers {

  "An Exercise 3.15 Solution" should "flatten Nil to Nil" in {
    Exercise15.concatenate(Nil) should be (Nil)
  }

  it should "flatten a List of Nils to Nil" in {
    Exercise15.concatenate(List(Nil, Nil, Nil)) should be (Nil)
  }

  it should "flatten a List of single-element Lists" in {
    Exercise15.concatenate(List(List(1), List(2), List(3))) should be (List(1, 2, 3))
  }

  it should "flatten a List of multiple-element Lists" in {
    Exercise15.concatenate(List(List(1, 2), List(2, 3), List(3, 4))) should be (List(1, 2, 2, 3, 3, 4))
  }
}
