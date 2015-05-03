package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.RNG

object Exercise7 {
  // Hard: If you can combine two RNG transitions, you should be able to combine a whole list of them.
  // Implement sequence for combining a List of transitions into a single transition.
  // Use it to reimplement the ints function you wrote before.
  // For the latter, you can use the standard library function List.fill(n)(x) to make a list with x repeated n times.
  type Rand[+A] = RNG => (A, RNG)

  def unit [A] (a: A): Rand[A] = rng => (a, rng)

  def map2 [A, B, C] (ra: Rand[A], rb: Rand[B]) (f: (A, B) => C): Rand[C] = rng => {
    val (a, rng1) = ra(rng)
    val (b, rng2) = rb(rng1)
    (f(a, b), rng2)
  }

  def sequence [A] (fs: List[Rand[A]]): Rand[List[A]] = {
    fs.foldRight(unit(List[A]())) {
      (a, b) => map2(a, b)(_ :: _)
    }
  }

  def ints (count: Int): Rand[List[Int]] = sequence(List.fill(count)(r => Exercise1.nonNegativeInt(r)))
}
