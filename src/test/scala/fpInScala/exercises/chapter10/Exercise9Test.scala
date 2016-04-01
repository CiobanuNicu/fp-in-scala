package fpInScala.exercises.chapter10

import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.forAll

class Exercise9Test extends Properties("Monoid.ordered") {
  property("returns true for empty int indexed sequences") = forAll (emptyInts) (Exercise9.ordered)

  property("returns true for int indexed sequences of size 1") = forAll (intsOfSize1) (Exercise9.ordered)

  property("returns true for ordered int indexed sequences") = forAll (orderedInts) (Exercise9.ordered)

  property("returns false for unordered int indexed sequences") = forAll (unorderedInts) (!Exercise9.ordered(_))

  private def emptyInts: Gen[IndexedSeq[Int]] = Gen.const(IndexedSeq.empty[Int])

  private def intsOfSize1: Gen[IndexedSeq[Int]] = Gen.posNum[Int].flatMap(IndexedSeq[Int](_))

  private def orderedInts: Gen[IndexedSeq[Int]] = Gen.posNum[Int].flatMap(start => Gen.sized(size => {
    val c = new Array[Int](size)
    c.zipWithIndex.foreach { case (_, idx) => c(idx) = start + idx }
    c.toIndexedSeq
  })).suchThat(_.length >= 2)

  private def unorderedInts: Gen[IndexedSeq[Int]] = Gen.nonEmptyContainerOf[IndexedSeq, Int](Gen.posNum[Int]).suchThat(unordered => {
    unordered.length >= 2 && unordered.sliding(2).exists(v => v(1) < v(0))
  })
}
