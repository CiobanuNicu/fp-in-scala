package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise3Test extends FlatSpec with ShouldMatchers {
  "Stream.takeWhile(p: A => Boolean)" should "return the empty stream when the predicate is always false" in {
    Stream(1, 2, 3).takeWhile(x => false).toList should be (List())
  }

  it should "return the stream itself when the predicate is always true" in {
    Stream(4, 5, 6, 7, 8, 9, 10).takeWhile(x => true).toList should be (List(4, 5, 6, 7, 8, 9, 10))
  }

  it should "return the portions of the stream that satisfied the predicate if they don't all do" in {
    val dividesBy3: (Int) => Boolean = x => x % 3 == 0
    Stream(3, 6, 9, 12, 15, 20, 30, 40).takeWhile(dividesBy3).toList should be (List(3, 6, 9, 12, 15))
  }
}
