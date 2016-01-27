package fpInScala.testing

import fpInScala.dataStructures.state.State
import fpInScala.purelyFunctionalState.RNG

case class Gen [A] (sample: State[RNG, A]) {
  def flatMap [B] (f: A => Gen[B]): Gen[B] = Gen {
    sample.flatMap(a => f(a).sample)
  }

  def listOfN (size: Gen[Int]): Gen[List[A]] = size flatMap(i => Gen.listOfN(i, this))
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

  def union [A] (g1: Gen[A], g2: Gen[A]): Gen[A] = boolean flatMap { if (_) g1 else g2 }

  def weighted [A] (g1: (Gen[A], Double), g2: (Gen[A], Double)): Gen[A] = {
    val g1Weight = g1._2.abs
    val g2Weight = g2._2.abs
    val g1Probability = g1Weight / (g1Weight + g2Weight)

    Gen {
      State(RNG.double).flatMap(d => if (d < g1Probability) g1._1.sample else g2._1.sample)
    }
  }
}
