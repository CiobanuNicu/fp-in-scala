package fpInScala.exercises

import org.scalatest.{FlatSpec, ShouldMatchers}
import Exercise2Point2._

class Exercise2Point2Test extends FlatSpec with ShouldMatchers {
  val loToHi = (a: Int, b: Int) => a <= b

  "An Exercise 2.2 Solution" should "return true for an empty array" in {
    isSorted(Array(), loToHi) should be (true)
  }

  it should "return true for an array of 1" in {
    isSorted(Array(1), loToHi) should be (true)
  }

  it should "return true for an array that is sorted using natural ordering" in {
    val sorted = Array(55, 55, 56, 57, 58, 59, 208)
    isSorted(sorted, loToHi) should be (true)
  }

  it should "return false for an array that is not sorted using natural ordering" in {
    val notSorted: Array[Int] = Array(1, 3, 2, 4, 4)
    isSorted(notSorted, loToHi) should be (false)
  }
}
