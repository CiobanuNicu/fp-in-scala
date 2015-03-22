package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise13Test extends FlatSpec with ShouldMatchers {
  "Stream.zipWith" should "return an empty stream when a non-empty stream is zipped with an empty stream" in {
    Stream.empty[Int].zipWith(Stream(1, 2, 3))(_ + _).toList should be (List())
  }

  it should "return an empty stream when an empty stream is zipped with a non-empty stream" in {
    Stream(1, 2, 3).zipWith(Stream.empty[Int])(_ + _).toList should be (List())
  }

  it should "return a stream of the return values from a combining function zipping two non-empty streams together" in {
    Stream(1, 3, 5).zipWith(Stream(7, 9, 11))(_ + _).toList should be (List(8, 12, 16))
  }

  it should "return a stream of the same length as the shorter stream when two non-empty streams are zipped together" in {
    Stream(1, 3, 5).zipWith(Stream(7, 9, 11, 13))(_ + _).toList should be (List(8, 12, 16))
    Stream(1, 3, 5, 7).zipWith(Stream(7, 9, 11))(_ + _).toList should be (List(8, 12, 16))
  }
}