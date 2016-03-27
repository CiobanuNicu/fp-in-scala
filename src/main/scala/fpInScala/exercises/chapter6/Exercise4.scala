package fpInScala.exercises.chapter6

import fpInScala.purelyFunctionalState.RNG

object Exercise4 {
  // Write a function to generate a list of random integers.
  def ints (count: Int) (rng: RNG): (List[Int], RNG) = {
    @annotation.tailrec
    def go (c: Int) (s: (List[Int], RNG)): (List[Int], RNG) = {
      if (c <= 0) s
      else {
        val (l, r) = s
        val (i, r1) = Exercise1.nonNegativeInt(r)
        go(c - 1)((i :: l, r1))
      }
    }
    go(count)((List(), rng))
  }
}
