package fpInScala.exercises.chapter8

import org.scalacheck.{Prop, Arbitrary, Gen, Properties}
import org.scalacheck.Prop.{forAll, BooleanOperators}

object ListSum {
  def sum (l: List[Int]): Int = l.foldLeft[Int](0)(_ + _)
}

class Exercise1Test extends Properties("sum: List[Int] => Int") {
  property("Sum of List should equal sum of reversed list") = forAll { (l: List[Int]) =>
    ListSum.sum(l) == ListSum.sum(l.reverse)
  }

  val emptyLists = Gen.const(List[Int]())

  property("Sum of empty list should be zero") = forAll(emptyLists) { ListSum.sum(_) == 0 }

  val listsOfAllTheSameValue: Gen[List[Int]] = for {
    size <- Gen.choose(1, 5000)
    value <- Arbitrary.arbitrary[Int]
  } yield List.fill[Int](size)(value)

  property("Sum of lists of all the same value should equal length times value") = forAll(listsOfAllTheSameValue) { l =>
    ListSum.sum(l) == l.head * l.size
  }

  property("Sum of a list should be the sum of two of its arbitrary sub-lists") = forAll { (l: List[Int]) =>
    (l.size > 0) ==> {
      Gen.choose(1, l.size).sample.exists(randomIndex => {
        val (left, right) = l.splitAt(randomIndex)
        ListSum.sum(l) == ListSum.sum(left) + ListSum.sum(right)
      })
    }
  }
}
