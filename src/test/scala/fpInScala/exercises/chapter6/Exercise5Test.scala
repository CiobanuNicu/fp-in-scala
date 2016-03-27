package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.SimpleRNG
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class Exercise5Test extends Properties("Exercise5.double") {
  property("Generates a Double between 0 and 1, not including 1, in a more elegant way") = forAll { (n: Long) =>
    val rng = SimpleRNG(n)
    val (nextRand, _) = Exercise5.double(rng)
    nextRand >= 0.0 && nextRand < 1.0
  }
}
