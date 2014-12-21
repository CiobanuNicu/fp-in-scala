package fpInScala.exercises.chapter4

import org.scalatest.{FlatSpec, ShouldMatchers}
import fpInScala.dataStructures._

class Exercise3Test extends FlatSpec with ShouldMatchers {
  val plus: (Int, Int) => Int = (a, b) => a + b
  val div: (Int, Int) => Double = (x, y) => x.toDouble / y.toDouble
  val concat: (String, String) => String = (s, t) => s + t

  "An Exercise 4.3 Solution" should "return None when the first argument is None for any combinator f" in {
    Exercise3.map2(None, Some(1))(plus) should be(None)
    Exercise3.map2(None, Some(5))(div) should be(None)
    Exercise3.map2(None, Some("bar"))(concat) should be(None)
  }

  it should "return None when the second argument is None for any combinator f" in {
    Exercise3.map2(Some(1), None)(plus) should be(None)
    Exercise3.map2(Some(5), None)(div) should be(None)
    Exercise3.map2(Some("bar"), None)(concat) should be(None)
  }

  it should "return the result of f wrapped in Some when both arguments are Some for any combinator f" in {
    Exercise3.map2(Some(1), Some(7))(plus) should be(Some(8))
    Exercise3.map2(Some(5), Some(2))(div) should be(Some(2.5))
    Exercise3.map2(Some("foo"), Some("bar"))(concat) should be(Some("foobar"))
  }

  it should "work with alternateMap2A" in {
    Exercise3.alternateMap2A(None, Some(1))(plus) should be(None)
    Exercise3.alternateMap2A(None, Some(5))(div) should be(None)
    Exercise3.alternateMap2A(None, Some("bar"))(concat) should be(None)
    Exercise3.alternateMap2A(Some(1), None)(plus) should be(None)
    Exercise3.alternateMap2A(Some(5), None)(div) should be(None)
    Exercise3.alternateMap2A(Some("bar"), None)(concat) should be(None)
    Exercise3.alternateMap2A(Some(1), Some(7))(plus) should be(Some(8))
    Exercise3.alternateMap2A(Some(5), Some(2))(div) should be(Some(2.5))
    Exercise3.alternateMap2A(Some("foo"), Some("bar"))(concat) should be(Some("foobar"))
  }

  it should "work with alternateMap2B" in {
    Exercise3.alternateMap2B(None, Some(1))(plus) should be(None)
    Exercise3.alternateMap2B(None, Some(5))(div) should be(None)
    Exercise3.alternateMap2B(None, Some("bar"))(concat) should be(None)
    Exercise3.alternateMap2B(Some(1), None)(plus) should be(None)
    Exercise3.alternateMap2B(Some(5), None)(div) should be(None)
    Exercise3.alternateMap2B(Some("bar"), None)(concat) should be(None)
    Exercise3.alternateMap2B(Some(1), Some(7))(plus) should be(Some(8))
    Exercise3.alternateMap2B(Some(5), Some(2))(div) should be(Some(2.5))
    Exercise3.alternateMap2B(Some("foo"), Some("bar"))(concat) should be(Some("foobar"))
  }
}
