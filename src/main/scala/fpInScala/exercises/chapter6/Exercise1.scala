package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.RNG

object Exercise1 {
  // Write a function that uses RNG.nextInt to generate a random integer between 0 and Int.MaxValue (inclusive).
  // Make sure to handle the corner case when nextInt returns Int.MinValue, which doesn't have a non-negative
  // counterpart.
  def nonNegativeInt (rng: RNG): (Int, RNG) = {
    val (posOrNeg, nextRng) = rng.nextInt
    val nextNonNegative = if (posOrNeg < 0) Math.abs(posOrNeg + 1) else posOrNeg
    (nextNonNegative, nextRng)
  }
}
