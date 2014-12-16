package fpInScala.exercises.chapter3

import fpInScala.dataStructures._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise28Test extends FlatSpec with ShouldMatchers {

  "An Exercise 3.28 Solution" should "be able to map a single Leaf" in {
    Exercise28.map(Leaf(3))(x => x + 1) should be (Leaf(4))
  }

  it should "be able to map a Branch of Leaves" in {
    Exercise28.map(Branch(Leaf(2), Leaf(3)))(x => x.toString) should be (Branch(Leaf("2"), Leaf("3")))
  }

  it should "be able to map a Branch of Branches and Leaves" in {
    val before =
      Branch(
        Branch(
          Leaf(3),
          Branch(
            Leaf(4),
            Leaf(5)
          )
        ),
        Branch(
          Branch(
            Leaf(6),
            Leaf(8)
          ),
          Leaf(7)
        )
      )
    val after =
      Branch(
        Branch(
          Leaf(1.5),
          Branch(
            Leaf(2.0),
            Leaf(2.5)
          )
        ),
        Branch(
          Branch(
            Leaf(3.0),
            Leaf(4.0)
          ),
          Leaf(3.5)
        )
      )
    Exercise28.map(before)(x => x / 2.0) should be (after)
  }
}
