package fpInScala.testing

import fpInScala.testing.Prop._
import fpInScala.purelyFunctionalState.{SimpleRNG, RNG}
import fpInScala.dataStructures.stream.Stream

case class Prop (run: (MaxSize, TestCases, RNG) => Result) {
  def && (p: Prop): Prop = Prop {
    (maxSize, testCases, rng) => run(maxSize, testCases, rng) match {
      case Passed => p.run(maxSize, testCases, rng)
      case f: Falsified => f
    }
  }

  def || (p: Prop): Prop = Prop {
    (maxSize, testCases, rng) => run(maxSize, testCases, rng) match {
      case Falsified(failedCase, successes) => p.label(failedCase).run(maxSize, testCases, rng)
      case p => p
    }
  }

  def label (tag: String): Prop = Prop {
    (maxSize, testCases, rng) => run(maxSize, testCases, rng) match {
      case Falsified(failedCase, successes) => Falsified(s"$tag\n$failedCase", successes)
      case p => p
    }
  }
}

object Prop {
  type FailedCase = String
  type MaxSize = Int
  type SuccessCount = Int
  type TestCases = Int
//  type Result = Option[(FailedCase, SuccessCount)]

  def run (p: Prop, maxSize: MaxSize = 100, testCases: TestCases = 100, rng: RNG = SimpleRNG(System.currentTimeMillis())): Unit =
    p.run(maxSize, testCases, rng) match {
      case Falsified(msg, n) =>
        println(s"! Falsified after $n passed tests:\n $msg")
      case Passed =>
        println(s"+ OK, passed $testCases tests.")
    }

  def forAll [A] (g: SGen[A]) (f: A => Boolean): Prop = forAll(g(_))(f)

  def forAll [A] (g: Int => Gen[A]) (f: A => Boolean): Prop = Prop {
    (maxSize: MaxSize, n: TestCases, rng: RNG) =>
      val casesPerSize = n + (maxSize - 1) / maxSize
      val props: Stream[Prop] =
        Stream.from(0).take((n min maxSize) + 1).map(i => forAll(g(i))(f))
      val prop: Prop =
        props.map(p => Prop { (maxSize, _, rng) =>
          p.run(maxSize, casesPerSize, rng)
        }).toList.reduce(_ && _)
      prop.run(maxSize, n, rng)
  }

  def forAll [A] (as: Gen[A]) (f: A => Boolean): Prop = Prop {
    (maxSize: MaxSize, n: TestCases, rng: RNG) => randomStream(as)(rng).zip(Stream.from(0)).take(n).map {
      case (a, i) => try {
        if (f(a)) Passed else Falsified(a.toString, i)
      } catch { case e: Exception => Falsified(buildMsg(a, e), i) }
    }.find(_.isFalsified).getOrElse(Passed)
  }

  def randomStream [A] (g: Gen[A]) (rng: RNG): Stream[A] =
    Stream.unfold(rng)(rng => Some(g.sample.run(rng)))

  def buildMsg [A] (s: A, e: Exception): String =
    s"test case $s\n" +
    s"generated an exception: ${e.getMessage}\n" +
    s"stack trace:\n ${e.getStackTrace.mkString("\n")}"
}
