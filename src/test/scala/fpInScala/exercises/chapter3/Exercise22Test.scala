package fpInScala.exercises.chapter3

import fpInScala.dataStructures._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise22Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.22 Solution" should "return Nil for Nil and Nil" in {
    Exercise22.addLists(Nil, Nil) should be (Nil)
  }

  it should "return Nil if either argument is Nil even though the other is not" in {
    Exercise22.addLists(Nil, List(1)) should be (Nil)
    Exercise22.addLists(List(1), Nil) should be (Nil)
  }

  it should "return List(3) for List(2) and List(1)" in {
    Exercise22.addLists(List(2), List(1)) should be (List(3))
  }

  it should "return a result corresponding to the sums in the shorter list" in {
    Exercise22.addLists(List(1, 2, 3), List(1, 2)) should be (List(2, 4))
  }

  it should "return List(5, 7 9) for List(1, 2, 3) and List(4, 5, 6)" in {
    Exercise22.addLists(List(1, 2, 3), List(4, 5, 6)) should be (List(5, 7, 9))
  }
}
