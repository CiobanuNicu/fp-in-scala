package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise15Test extends FlatSpec with ShouldMatchers {
  "Stream.tails" should "return from Stream(): Stream(Stream())" in {
    Stream().tails.toList should be (List(Empty))
  }

  it should "return from Stream(1, 2, 3): Stream(Stream(1, 2, 3), Stream(2, 3), Stream(3), Stream())" in {
    Stream(1, 2, 3).tails.map(_.toList).toList should be (List(List(1, 2, 3), List(2, 3), List(3), List()))
  }
}
