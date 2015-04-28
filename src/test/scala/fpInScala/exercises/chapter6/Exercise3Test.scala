package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.SimpleRNG
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class Exercise3Test extends Properties("Exercise3.intDouble, Exercise3.doubleInt, Exercise3.double3") {
  property("Exercise3.intDouble produces a pair of Int and Double") = forAll { (n: Int) =>
    val rng = SimpleRNG(n)
    val ((nextInt, nextDouble), _) = Exercise3.intDouble(rng)

    nextInt >= 0 && nextInt <= Int.MaxValue && nextDouble >= 0.0 && nextDouble < 1.0
  }

  property("Exercise3.doubleInt produces a pair of Double and Int") = forAll { (n: Int) =>
    val rng = SimpleRNG(n)
    val ((nextDouble, nextInt), _) = Exercise3.doubleInt(rng)

    nextInt >= 0 && nextInt <= Int.MaxValue && nextDouble >= 0.0 && nextDouble < 1.0
  }

  property("Exercise3.double3 produces a triple of Double, Double, Double") = forAll { (n: Int) =>
    val rng = SimpleRNG(n)
    val ((d1, d2, d3), _) = Exercise3.double3(rng)
    (d1 >= 0.0 && d1 < 1.0) &&
    (d2 >= 0.0 && d2 < 1.0) &&
    (d3 >= 0.0 && d3 < 1.0)
  }
}
