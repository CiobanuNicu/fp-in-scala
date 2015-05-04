package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.SimpleRNG
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class Exercise9Test extends Properties("Exercise9.map in terms of flatMap") {
  property("Exercise9.double generates a Double between 0 and 1, not including 1, in a still more elegant way") = forAll { (n: Int) =>
    val rng = SimpleRNG(n)
    val (nextRand, _) = Exercise9.double(rng)
    nextRand >= 0.0 && nextRand < 1.0
  }
}
