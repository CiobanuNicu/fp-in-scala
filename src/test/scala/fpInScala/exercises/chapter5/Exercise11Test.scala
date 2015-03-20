package fpInScala.exercises.chapter5

import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise11Test extends FlatSpec with ShouldMatchers {
  "Stream.unfold" should "be able to produce the fibonacci sequence with the correct next-state/value function" in {
    Exercise11.fibsByUnfold.take(10).toList should be (List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34))
  }
}
