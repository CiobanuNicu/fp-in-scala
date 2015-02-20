package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise2Test extends FlatSpec with ShouldMatchers {
  "Stream.take(n)" should "return an empty Stream if n is less than or equal to 0" in {
    Stream(1, 2, 3).take(0).toList should be (List())
    Stream(1, 2, 3).take(-5).toList should be (List())
  }

  it should "return the first element only if n is equal to 1" in {
    Stream(1, 2, 3).take(1).toList should be (List(1))
  }

  it should "return as many elements as were taken in a new stream if n is greater than 1" in {
    Stream(1, 2, 3, 4, 5, 6).take(3).toList should be (List(1, 2, 3))
  }

  it should "return the entire stream if more than its length was taken" in {
    Stream(1, 2, 3, 4, 5).take(27).toList should be (List(1, 2, 3, 4, 5))
  }
}
