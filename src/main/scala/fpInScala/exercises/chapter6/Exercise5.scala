package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.RNG

object Exercise5 {
  // Use map to reimplement double in a more elegant way. See exercise 6.2.
  type Rand[+A] = RNG => (A, RNG)

  def map [A, B] (s: Rand[A]) (f: A => B): Rand[B] = rng => {
    val (a, rng2) = s(rng)
    (f(a), rng2)
  }

  def double: Rand[Double] = map(Exercise1.nonNegativeInt)(_ / (Int.MaxValue.toDouble + 1))
}
