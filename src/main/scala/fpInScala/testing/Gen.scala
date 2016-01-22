package fpInScala.testing

import fpInScala.dataStructures.state.State
import fpInScala.purelyFunctionalState.RNG

case class Gen [A] (sample: State[RNG, A]) {
  def flatMap [B] (f: A => Gen[B]): Gen[B] = Gen {
    sample.flatMap(a => f(a).sample)
  }
}

object Gen {
  def choose (start: Int, stopExclusive: Int): Gen[Int] = Gen {
    State { RNG.nonNegativeInt } map (n => start + n % (stopExclusive - start))
  }

  def unit [A] (a: => A): Gen[A] = Gen {
    State.unit(a)
  }

  def boolean: Gen[Boolean] = Gen { State { RNG.boolean } }

  def listOfN [A] (n: Int, g: Gen[A]): Gen[List[A]] = Gen { State.sequence { List.fill(n)(g.sample) } }
}
