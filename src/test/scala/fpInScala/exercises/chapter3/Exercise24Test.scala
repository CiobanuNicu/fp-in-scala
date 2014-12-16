package fpInScala.exercises.chapter3

import fpInScala.dataStructures._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise24Test extends FlatSpec with ShouldMatchers {

  "An Exercise 3.24 Solution" should "show that Nil is not a subsequence of Nil" in {
    Exercise24.hasSubsequence(Nil, Nil) should be (false)
  }

  it should "show that List(2, 3, 4) is not a subsequence of List(1, 2, 3)" in {
    Exercise24.hasSubsequence(List(1, 2, 3), List(2, 3, 4)) should be (false)
  }

  it should "show that List(1, 2, 3, 4) would have List(1, 2), List(2, 3), and List(4) as subsequences" in {
    Exercise24.hasSubsequence(List(1, 2, 3, 4), List(1, 2)) should be (true)
    Exercise24.hasSubsequence(List(1, 2, 3, 4), List(2, 3)) should be (true)
    Exercise24.hasSubsequence(List(1, 2, 3, 4), List(4)) should be (true)
  }

  it should "show that any List is a subsequence of itself" in {
    Exercise24.hasSubsequence(List(1), List(1)) should be (true)
    Exercise24.hasSubsequence(List(1, 2), List(1, 2)) should be (true)
    Exercise24.hasSubsequence(List(1, 2, 3), List(1, 2, 3)) should be (true)
    Exercise24.hasSubsequence(List(1, 2, 3, 4), List(1, 2, 3, 4)) should be (true)
    Exercise24.hasSubsequence(List(1, 2, 3, 4, 5), List(1, 2, 3, 4, 5)) should be (true)
  }

  it should "show that any tail is a subsequence of its list" in {
    val list:Cons[Int] = List(1, 2, 3, 4, 5, 6).asInstanceOf[Cons[Int]]

    Exercise24.hasSubsequence(list, list.tail) should be (true)
  }
}
