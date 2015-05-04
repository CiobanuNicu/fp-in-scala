package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.RNG

object Exercise9 {
  // Reimplement map and map2 in terms of flatMap. The fact that this is possible is what we're referring
  // to when we say that flatMap is more powerful than map and map2.
  type Rand[+A] = RNG => (A, RNG)

  def unit [A] (a: A): Rand[A] = rng => (a, rng)

  def flatMap [A, B] (f: Rand[A]) (g: A => Rand[B]): Rand[B] = rng => {
    val (a, rng2) = f(rng)
    g(a)(rng2)
  }

  def map [A, B] (s: Rand[A]) (f: A => B): Rand[B] = flatMap(s)(a => unit(f(a)))

  def double: Rand[Double] = map(Exercise1.nonNegativeInt)(_ / (Int.MaxValue.toDouble + 1))
}
