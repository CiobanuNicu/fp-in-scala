package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.SimpleRNG
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class Exercise2Test extends Properties("Exercise2.double") {
  property("Generates a Double between 0 and 1, not including 1") = forAll { (n: Long) =>
    val rng = SimpleRNG(n)
    val (nextRand, _) = Exercise2.double(rng)
    nextRand >= 0.0 && nextRand < 1.0
  }
}
