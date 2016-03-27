package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.SimpleRNG
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class Exercise8Test extends Properties("Exercise8.nonNegativeIntLessThan") {
  property("Produces a non-negative integer, less than some other non-negative integer") = forAll { (n: Long) =>
    val rng = SimpleRNG(n)
    val (sentinel, nextRNG) = Exercise1.nonNegativeInt(rng)

    val (lessThanTheSentinel, _) = Exercise8.nonNegativeLessThan(sentinel)(nextRNG)

    (lessThanTheSentinel < sentinel || lessThanTheSentinel == 0) && lessThanTheSentinel >= 0
  }
}
