package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.RNG

object Exercise8 {
  // Implement flatMap, and then use it to implement nonNegativeLessThan.
  type Rand[+A] = RNG => (A, RNG)

  def unit [A] (a: A): Rand[A] = rng => (a, rng)

  def flatMap [A, B] (f: Rand[A]) (g: A => Rand[B]): Rand[B] = rng => {
    val (a, rng2) = f(rng)
    g(a)(rng2)
  }

  def nonNegativeLessThan (n: Int): Rand[Int] = flatMap(Exercise1.nonNegativeInt)(i => {
    if (n == 0) {
      unit(n)
    } else {
      val mod = i % n
      if (i + (n - 1) - mod >= 0)
        unit(mod)
      else
        nonNegativeLessThan(n)
    }
  })
}
