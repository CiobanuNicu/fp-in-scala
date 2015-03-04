package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise9Test extends FlatSpec with ShouldMatchers {
  "Stream.from" should "generate a stream of infinitely incrementing integers starting from the argument supplied" in {
    Stream.from(1).take(10).toList should be (List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    Stream.from(2).take(5).toList should be (List(2, 3, 4, 5, 6))
    Stream.from(37).take(3).toList should be (List(37, 38, 39))
  }

  it should "wrap around to negative numbers once the maximum integer value is exceeded" in {
    Stream.from(Integer.MAX_VALUE - 1).take(3).toList should be (List(2147483646, 2147483647, -2147483648))
  }
}
