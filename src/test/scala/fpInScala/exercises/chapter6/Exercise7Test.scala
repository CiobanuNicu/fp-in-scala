package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.SimpleRNG
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class Exercise7Test extends Properties("Exercise7.sequence, Exercise7.ints") {
  property("sequence(List(unit(1), unit(2), unit(3)))(rng)._1 should return List(1, 2, 3)") = forAll { (n: Long) =>
    import Exercise7._

    val r = SimpleRNG(n)
    sequence(List(unit(1), unit(2), unit(3)))(r)._1 == List(1, 2, 3)
  }

  property("Produces a list of random integers, of the count specified, implemented in terms of Exercise7.sequence") =
    forAll { (n: Long, c: Int) =>
      val rng = SimpleRNG(n)
      val count = Math.abs(c - 1) % 500 // Keep the lists short-ish

      val (randomInts, _) = Exercise7.ints(count)(rng)

      randomInts.size == count && randomInts.forall(i => i >= 0 && i <= Int.MaxValue)
    }

}
