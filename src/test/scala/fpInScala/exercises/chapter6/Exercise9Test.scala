package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.SimpleRNG
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class Exercise9Test extends Properties("Exercise9.map and map2 in terms of flatMap") {
  property("Exercise9.double generates a Double between 0 and 1, not including 1, in a still more elegant way") = forAll { (n: Int) =>
    val rng = SimpleRNG(n)
    val (nextRand, _) = Exercise9.double(rng)
    nextRand >= 0.0 && nextRand < 1.0
  }

  property("sequence(List(unit(1), unit(2), unit(3)))(rng)._1 should return List(1, 2, 3)") = forAll { (n: Int) =>
    import Exercise9._

    val r = SimpleRNG(n)
    sequence(List(unit(1), unit(2), unit(3)))(r)._1 == List(1, 2, 3)
  }

  property("Produces a list of random integers, of the count specified, implemented in terms of Exercise7.sequence") =
    forAll { (n: Int, c: Int) =>
      val rng = SimpleRNG(n)
      val count = Math.abs(c - 1) % 500 // Keep the lists short-ish

      val (randomInts, _) = Exercise9.ints(count)(rng)

      randomInts.size == count && randomInts.forall(i => i >= 0 && i <= Int.MaxValue)
    }
}
