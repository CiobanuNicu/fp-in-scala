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

  "Stream.mapViaUnfold" should "have no effect on an empty stream" in {
    Stream[Int]().mapViaUnfold(x => x * 2).toList should be (List())
  }

  it should "transform a stream of strings into a stream of integers given a function from String to Int" in {
    Stream("1", "2", "3", "4", "5", "6").mapViaUnfold(_.toInt).toList should be (List(1, 2, 3, 4, 5, 6))
  }

  it should "transform a stream of integers into a stream of different integers given a function from Int to Int" in {
    Stream(1, 2, 3, 4, 5).mapViaUnfold(_ + 1).toList should be (List(2, 3, 4, 5, 6))
  }

  "Stream.takeViaUnfold(n)" should "return an empty Stream if n is less than or equal to 0" in {
    Stream(1, 2, 3).takeViaUnfold(0).toList should be (List())
    Stream(1, 2, 3).takeViaUnfold(-5).toList should be (List())
  }

  it should "return the first element only if n is equal to 1" in {
    Stream(1, 2, 3).takeViaUnfold(1).toList should be (List(1))
  }

  it should "return as many elements as were taken in a new stream if n is greater than 1" in {
    Stream(1, 2, 3, 4, 5, 6).takeViaUnfold(3).toList should be (List(1, 2, 3))
  }

  it should "return the entire stream if more than its length was taken" in {
    Stream(1, 2, 3, 4, 5).takeViaUnfold(27).toList should be (List(1, 2, 3, 4, 5))
  }
}