package fpInScala.exercises

import org.scalatest.{FlatSpec, ShouldMatchers}
import fpInScala.dataStructures._

class Exercise3Point23Test extends FlatSpec with ShouldMatchers {
  def identity [A, B] (x: A, y: B): (A, B) = (x, y)
  val plus: (Int, Int) => Int = (x, y) => x + y
  val minus: (Int, Int) => Int = (x, y) => x - y
  def nil [A] (x: A, y: A): List[A] = Nil

  "An Exercise 3.23 Solution" should "return Nil for Nil and Nil, regardless of f" in {
    Exercise3Point23.zipWith(Nil, Nil)(identity) should be (Nil)
    Exercise3Point23.zipWith(Nil, Nil)(plus) should be (Nil)
    Exercise3Point23.zipWith(Nil, Nil)(minus) should be (Nil)
    Exercise3Point23.zipWith(Nil, Nil)(nil) should be (Nil)
  }

   it should "return Nil if either argument is Nil even though the other is not" in {
    Exercise3Point23.zipWith(Nil, List(1))(plus) should be (Nil)
    Exercise3Point23.zipWith(List(1), Nil)(plus) should be (Nil)
  }

  it should "return List(3) for List(2) and List(1) with plus" in {
    Exercise3Point23.zipWith(List(2), List(1))(plus) should be (List(3))
  }

  it should "return a result corresponding to the sums in the shorter list with plus" in {
    Exercise3Point23.zipWith(List(1, 2, 3), List(1, 2))(plus) should be (List(2, 4))
  }

  it should "return List(5, 7 9) for List(1, 2, 3) List(4, 5, 6) with plus" in {
    Exercise3Point23.zipWith(List(1, 2, 3), List(4, 5, 6))(plus) should be (List(5, 7, 9))
  }

  it should "return List(3, 3, 3) for List(4, 5, 6) and List(1, 2, 3) with minus" in {
    Exercise3Point23.zipWith(List(4, 5, 6), List(1, 2, 3))(minus) should be (List(3, 3, 3))
  }

  it should "return List(foo, foo, foo) for List(a, b, c) and List(x, y, z) with (_, _) => foo" in {
    Exercise3Point23.zipWith(List("a", "b", "c"), List("x", "y", "z"))((_, _) => "foo") should be (List("foo", "foo", "foo"))
  }
}
