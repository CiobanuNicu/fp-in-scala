package fpInScala.exercises.chapter8

import fpInScala.testing.Gen
import fpInScala.testing.SGen._
import fpInScala.testing.Prop._

object Exercise14Test {
  def main (args: Array[String]): Unit = {

    val ints = Gen.choose(2, Integer.MAX_VALUE)

    val sortedInts = forAll(listOf1(ints)) { ints =>
      val sorted = ints.sorted

      sorted.sliding(2).forall {
        case left :: right :: _ => left <= right
        case _ => true
      } && ints.forall(sorted.contains) && sorted.forall(ints.contains)
    }

    run(sortedInts)
  }
}
