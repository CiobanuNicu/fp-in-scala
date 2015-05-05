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

  def map2 [A, B, C] (ra: Rand[A], rb: Rand[B]) (f: (A, B) => C): Rand[C] = flatMap(ra)(a => map(rb)(b => f(a, b)))

  def sequence [A] (fs: List[Rand[A]]): Rand[List[A]] = {
    fs.foldRight(unit(List[A]())) {
      (a, b) => map2(a, b)(_ :: _)
    }
  }

  def ints (count: Int): Rand[List[Int]] = sequence(List.fill(count)(r => Exercise1.nonNegativeInt(r)))
}
