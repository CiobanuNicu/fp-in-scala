package fpInScala.exercises.chapter8

import org.scalacheck.{Arbitrary, Gen, Properties}
import org.scalacheck.Prop.{forAll, BooleanOperators}

object ListMax {
  def max (l: List[Int]): Option[Int] = if (l.isEmpty)
    None
  else
    Some(l.foldLeft[Int](l.head)((acc, el) => if (el > acc) el else acc))
}

class Exercise2Test extends Properties("max: List[Int] => Int") {
  property("Max of a non-empty list should be greater than or equal to every element") = forAll { (l: List[Int]) =>
    (l.size > 0) ==> {
      ListMax.max(l).exists(m => l.forall(m >= _))
    }
  }

  val emptyLists = Gen.const(List[Int]())

  property("Max of the empty list should be None") = forAll(emptyLists) { l: List[Int] =>
    ListMax.max(l).isEmpty
  }

  val listsOfAllTheSameValue: Gen[List[Int]] = for {
    size <- Gen.choose(1, 5000)
    value <- Arbitrary.arbitrary[Int]
  } yield List.fill[Int](size)(value)

  property("Max of lists of all the same value should equal that value") = forAll(listsOfAllTheSameValue) { l =>
    ListMax.max(l).exists(_ == l.head)
  }
}
