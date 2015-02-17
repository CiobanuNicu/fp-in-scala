package fpInScala.exercises.chapter4

import fpInScala.dataStructures.list._
import fpInScala.dataStructures.option._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise4Test extends FlatSpec with ShouldMatchers {
  "An Exercise 4.4 Solution" should "return return None if any List element is None" in {
    Exercise4.sequence(List(None)) should be (None)
    Exercise4.sequence(List(Some(1), Some(2), Some(3), None)) should be (None)
  }

  it should "return an Option of all the List elements when none of them are undefined" in {
    Exercise4.sequence(List()) should be (Some(List()))
    Exercise4.sequence(List(Some(1), Some(2), Some(3))) should be (Some(List(1, 2, 3)))
  }
}
