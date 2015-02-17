package fpInScala.exercises.chapter3

import fpInScala.dataStructures.tree._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise25Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.25 Solution" should "return the size of a Leaf as 1" in {
    Exercise25.size(Leaf(1)) should be (1)
  }

  it should "return the size of a Branch of 2 Leaves as 3" in {
    Exercise25.size(Branch(Leaf(1), Leaf(2))) should be (3)
  }

  it should "return the size of a Branch of 1 Leaf and another Branch of 2 Leaves as 5" in {
    Exercise25.size(Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))) should be (5)
  }

  it should "return the size of a Branch of 2 Branches of 2 Leaves as 7" in {
    Exercise25.size(Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4)))) should be (7)
  }
}
