package fpInScala.purelyFunctionalState

// Here's one possible interface to a random number generator:

trait RNG {
  def nextInt: (Int, RNG)
}

object RNG {
  def nonNegativeInt (rng: RNG): (Int, RNG) = {
    val (posOrNeg, nextRng) = rng.nextInt
    val nextNonNegative = if (posOrNeg < 0) Math.abs(posOrNeg + 1) else posOrNeg
    (nextNonNegative, nextRng)
  }

  def boolean (rng: RNG): (Boolean, RNG) = {
    val (nextInt, nextRng) = rng.nextInt
    (nextInt % 2 == 0, nextRng)
  }

  def double (rng: RNG): (Double, RNG) = {
    val (i, r) = nonNegativeInt(rng)
    (i / (Int.MaxValue.toDouble + 1), r)
  }
}
