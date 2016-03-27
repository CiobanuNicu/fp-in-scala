package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.SimpleRNG
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class Exercise4Test extends Properties("Exercise4.ints") {
  property("Produces a list of random integers, of the count specified") = forAll { (n: Long, c: Int) =>
    val rng = SimpleRNG(n)
    val count = Math.abs(c - 1) % 500 // Keep the lists short-ish

    val (randomInts, _) = Exercise4.ints(count)(rng)

    randomInts.size == count && randomInts.forall(i => i >= 0 && i <= Int.MaxValue)
  }
}
