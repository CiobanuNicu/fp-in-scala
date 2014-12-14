package fpInScala.exercises

import fpInScala.dataStructures._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise3Point26Test extends FlatSpec with ShouldMatchers with TableDrivenPropertyChecks {
  "An Exercise 3.26 Solution" should "return the value of the Leaf when it is the entire tree" in {
    Exercise3Point26.maximum(Leaf(5)) should be (5)
    Exercise3Point26.maximum(Leaf(4)) should be (4)
    Exercise3Point26.maximum(Leaf(3)) should be (3)
    Exercise3Point26.maximum(Leaf(2)) should be (2)
    Exercise3Point26.maximum(Leaf(1)) should be (1)
  }

  it should "return the greater of two leaves when the tree is a branch" in {
    Exercise3Point26.maximum(Branch(Leaf(2), Leaf(3))) should be (3)
    Exercise3Point26.maximum(Branch(Leaf(10), Leaf(7))) should be (10)
    Exercise3Point26.maximum(Branch(Leaf(4), Leaf(4))) should be (4)
  }

  it should "return the maximum value of an arbitrarily nested tree" in {
    Exercise3Point26.maximum(Branch(Branch(Leaf(10), Leaf(7)), Leaf(15))) should be (15)
    Exercise3Point26.maximum(Branch(Branch(Leaf(10), Leaf(7)), Branch(Leaf(15), Leaf(21)))) should be (21)
    Exercise3Point26.maximum(Branch(Branch(Leaf(10), Leaf(7)), Branch(Branch(Leaf(15), Leaf(21)), Branch(Branch(Leaf(3), Branch(Leaf(99), Leaf(18))), Branch(Leaf(12), Leaf(11)))))) should be (99)
  }
}
