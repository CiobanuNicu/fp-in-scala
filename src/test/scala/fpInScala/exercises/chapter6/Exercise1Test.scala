package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.SimpleRNG
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class Exercise1Test extends Properties("RNG") {
  property("RNG.nextInt produces a value between 0 and Int.MaxValue, inclusive") = forAll { (n: Int) =>
    val rng = SimpleRNG(n)
    val (nextRand, _) = Exercise1.nonNegativeInt(rng)
    nextRand >= 0 && nextRand <= Int.MaxValue
  }
}
