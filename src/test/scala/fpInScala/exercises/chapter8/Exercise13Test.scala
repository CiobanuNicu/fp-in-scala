package fpInScala.exercises.chapter8

import fpInScala.testing.Gen
import fpInScala.testing.SGen._
import fpInScala.testing.Prop._

object Exercise13Test {

  def main (args: Array[String]): Unit = {
    val smallInt = Gen.choose(-10,10)

    val maxProp = forAll(listOf1(smallInt)) { ns =>
      val max = ns.max
      !ns.exists(_ > max)
    }

    run(maxProp)
  }
}
