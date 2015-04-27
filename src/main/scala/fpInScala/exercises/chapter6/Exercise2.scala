package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.RNG

object Exercise2 {
  // Write a function to generate a Double between 0 and 1, not including 1.
  // Note: You can use Int.MaxValue to obtain the maximum positive integer value,
  // and you can use x.toDouble to convert an x: Int to a Double.
  def double (rng: RNG): (Double, RNG) = {
    val (nextInteger, nextRNG) = Exercise1.nonNegativeInt(rng)
    val maxIntAsDoublePlusOne = Int.MaxValue.toDouble + 1
    val nextDouble = nextInteger / maxIntAsDoublePlusOne
    (nextDouble, nextRNG)
  }
}