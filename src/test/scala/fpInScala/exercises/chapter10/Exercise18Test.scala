package fpInScala.exercises.chapter10

import fpInScala.monoids.Monoid
import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.forAll

class Exercise18Test extends Properties ("Monoid.bag") {
  import Monoid._

  property("solves the trivial case") = forAll { (x: Unit) =>
    bag(Vector("a", "rose", "is", "a", "rose")) == Map("a" -> 2, "rose" -> 2, "is" -> 1)
  }

  property("solves the generic case") = forAll (wordsAndFrequencies) {
    case (words, expectedBag) => bag(words) == expectedBag
  }

  def wordsAndFrequencies: Gen[(IndexedSeq[String], Map[String, Int])] = Gen.sized(sz => {
    Gen.listOfN(sz, Gen.alphaStr.suchThat(_.nonEmpty)).map(ws => {
      (ws.toIndexedSeq, ws.groupBy(identity).mapValues(_.size))
    }).suchThat(_._1.nonEmpty)
  })
}
