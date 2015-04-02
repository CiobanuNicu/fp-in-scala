package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise16Test extends FlatSpec with ShouldMatchers {
  "Stream.scanRight" should "be like a foldRight that returns a stream of the intermediate results" in {
    Stream(1, 2, 3).scanRight(0)(_ + _).toList should be (List(6, 5, 3, 0))
  }
}
