package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.RNG

object Exercise3 {
  // Write functions to generate an (Int, Double) pair, a (Double, Int) pair, and a (Double, Double, Double) 3-tuple.
  // You should be able to reuse the functions you've already written.
  def intDouble (rng: RNG): ((Int, Double), RNG) = {
    val (i, r1) = Exercise1.nonNegativeInt(rng)
    val (d, r2) = Exercise2.double(r1)
    ((i, d), r2)
  }

  def doubleInt (rng: RNG): ((Double, Int), RNG) = {
    val (d, r1) = Exercise2.double(rng)
    val (i, r2) = Exercise1.nonNegativeInt(r1)
    ((d, i), r2)
  }

  def double3 (rng: RNG): ((Double, Double, Double), RNG) = {
    val (d1, r1) = Exercise2.double(rng)
    val (d2, r2) = Exercise2.double(r1)
    val (d3, r3) = Exercise2.double(r2)
    ((d1, d2, d3), r3)
  }
}
