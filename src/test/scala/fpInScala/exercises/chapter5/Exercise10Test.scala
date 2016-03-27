package fpInScala.exercises.chapter5

import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise10Test extends FlatSpec with ShouldMatchers {
  "Exercise10.fibs" should "return an infinite stream of the integers in the fibonacci sequence" in {
    Exercise10.fibs.take(7).toList should be (List(0, 1, 1, 2, 3, 5, 8))
  }
}
