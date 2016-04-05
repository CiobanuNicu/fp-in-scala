package fpInScala.exercises.chapter10

import fpInScala.monoids.WC
import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.forAll

class Exercise11Test extends Properties ("WC.wordCount") {
  property("counts the number of wordCount in a string") = forAll (spaceDelimitedWordStrings) {
    case (words: String, expectedWordCount: Int) => {
      WC.wordCount(words) == expectedWordCount
    }
  }

  def spaceDelimitedWordStrings: Gen[(String, Int)] = Gen.sized(sz => {
    Gen.listOfN(sz, Gen.alphaStr.suchThat(_.nonEmpty)).map(ws => (ws.mkString(" "), sz))
  })
}
