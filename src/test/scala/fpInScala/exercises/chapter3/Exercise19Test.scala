package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise19Test extends FlatSpec with ShouldMatchers {
  val alwaysTrue: Int => Boolean = x => true
  val alwaysFalse: Int => Boolean = x => false
  val isEven: Int => Boolean = x => x % 2 == 0

  "An Exercise 3.19 Solution" should "filter nothing with an always-true predicate" in {
    Exercise19.filter(List(1, 2, 3))(alwaysTrue) should be (List(1, 2, 3))
  }

  it should "filter everything with an always-false predicate" in {
    Exercise19.filter(List(1, 2, 3, 4, 5))(alwaysFalse) should be (Nil)
  }

  it should "remove all odd numbers from a list with an isEven predicate" in {
    Exercise19.filter(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))(isEven) should be (List(2, 4, 6, 8, 10))
  }
}
