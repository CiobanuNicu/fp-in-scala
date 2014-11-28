package fpInScala.exercises

import org.scalatest.{FlatSpec, ShouldMatchers}
import fpInScala.dataStructures._
import fpInScala.exercises.Exercise3Point5._

class Exercise3Point5Test extends FlatSpec with ShouldMatchers {

  private val always: (Any) => Boolean = (n: Any) => true
  private val never: (Any) => Boolean = (n: Any) => false

  "An Exercise 3.5 Solution" should "return Nil whenever passed Nil, regardless of the predicate" in {
    dropWhile(Nil)(always) should be (Nil)
  }

  it should "return Nil when given an always-truthy predicate" in {
    dropWhile(List(1, 2, 3))(always) should be (Nil)
  }

  it should "return the original list when given a never-truthy predicate" in {
    dropWhile(List(1, 2, 3))(never) should be (List(1, 2, 3))
  }

  it should "stop dropping at the first list element that fails the predicate" in {
    dropWhile(List(1, 2, 3, 4, 5, 4, 3, 2, 1))(n => n < 5) should be (List(5, 4, 3, 2, 1))
  }
}
