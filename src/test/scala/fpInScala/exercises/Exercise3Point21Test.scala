package fpInScala.exercises

import org.scalatest.{FlatSpec, ShouldMatchers}
import fpInScala.dataStructures._

class Exercise3Point21Test extends FlatSpec with ShouldMatchers {
  val alwaysTrue: Int => Boolean = x => true
  val alwaysFalse: Int => Boolean = x => false
  val isEven: Int => Boolean = x => x % 2 == 0

  "An Exercise 3.19 Solution" should "filter nothing with an always-true predicate" in {
    Exercise3Point21.filter(List(1, 2, 3))(alwaysTrue) should be (List(1, 2, 3))
  }

  it should "filter everything with an always-false predicate" in {
    Exercise3Point21.filter(List(1, 2, 3, 4, 5))(alwaysFalse) should be (Nil)
  }

  it should "remove all odd numbers from a list with an isEven predicate" in {
    Exercise3Point21.filter(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))(isEven) should be (List(2, 4, 6, 8, 10))
  }
}

