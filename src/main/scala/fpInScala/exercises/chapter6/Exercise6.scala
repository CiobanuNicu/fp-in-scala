package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.RNG

object Exercise6 {
  // Write the implementation of map2 based on the following signature.
  // This function takes two actions, ra and rb, and a function f for combining their results,
  // and returns a new action that combines them:
  type Rand[+A] = RNG => (A, RNG)

  def map2 [A, B, C] (ra: Rand[A], rb: Rand[B]) (f: (A, B) => C): Rand[C] = rng => {
    val (a, rng1) = ra(rng)
    val (b, rng2) = rb(rng1)
    (f(a, b), rng2)
  }
}
