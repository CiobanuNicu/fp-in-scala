package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise7Test extends FlatSpec with ShouldMatchers {
  "Stream.map" should "have no effect on an empty stream" in {
    Stream[Int]().map(x => x * 2).toList should be (List())
  }

  it should "transform a stream of strings into a stream of integers given a function from String to Int" in {
    Stream("1", "2", "3", "4", "5", "6").map(_.toInt).toList should be (List(1, 2, 3, 4, 5, 6))
  }

  it should "transform a stream of integers into a stream of different integers given a function from Int to Int" in {
    Stream(1, 2, 3, 4, 5).map(_ + 1).toList should be (List(2, 3, 4, 5, 6))
  }


  "Stream.filter" should "return the same stream given an always true predicate" in {
    Stream(1, 2, 3, 4, 5).filter(x => true).toList should be (List(1, 2, 3, 4, 5))
  }

  it should "return an empty stream given an always false predicate" in {
    Stream('a', 'b', 'c', 'd', 'e', 'f', 'g').filter(x => false).toList should be (List())
  }

  it should "return a stream containing only the elements that satisfy the predicate" in {
    Stream(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).filter(_ % 2 == 0).toList should be (List(2, 4, 6, 8, 10))
  }


  "Stream.append" should "produce an empty stream when an empty stream is appended to another empty stream" in {
    Stream().append(Stream()).toList should be (List())
  }

  it should "produce the second stream when a non-empty stream is appended to an empty stream" in {
    Stream().append(Stream(1, 2, 3, 4, 5)).toList should be (List(1, 2, 3, 4, 5))
  }

  it should "produce the first stream when an empty stream is appended to a non-empty stream" in {
    Stream(1, 2, 3, 4, 5).append(Stream()).toList should be (List(1, 2, 3, 4, 5))
  }

  it should "allow streams of a supertype to be appended" in {
    Stream(1, 2, 3, 4, 5).append(Stream(6.0, 7.0, 8.0, 9.0, 10.0)).toList should be (List(1, 2, 3, 4, 5, 6.0, 7.0, 8.0, 9.0, 10.0))
  }


  "Stream.flatMap" should "produce an empty stream when the combining function returns empty streams" in {
    Stream(1, 2, 3).flatMap(x => Stream()).toList should be (List())
  }

  it should "produce a new Stream returned from the combining function applied to a single element stream" in {
    Stream(1).flatMap(x => Stream(4, 5, 6)).toList should be (List(4, 5, 6))
  }

  it should "produce a flattened stream of streams from a multiple element stream and a combining function that produces multiple element streams" in {
    Stream(1, 2, 3).flatMap(x => Stream(x + 5, x + 10, x + 15)).toList should be (List(6, 11, 16, 7, 12, 17, 8, 13, 18))
  }
}
