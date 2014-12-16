package fpInScala.exercises.chapter3

import fpInScala.dataStructures._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise27Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.27 Solution" should "return the depth of a 0-deep Tree" in {
    val zeroDeep =
      Leaf("a")
    Exercise27.depth(zeroDeep) should be (0)
  }

  it should "return the depth of 1-deep Tree" in {
    val oneDeep =
      Branch(
        Leaf(1.0),
        Leaf(2.0)
      )
    Exercise27.depth(oneDeep) should be (1)
  }

  it should "return the depth of a 2-deep Tree" in {
    val twoDeep =
      Branch(
        Leaf(1.0),
        Branch(
          Leaf(2.0),
          Leaf(3.0)
        )
      )
    Exercise27.depth(twoDeep) should be (2)
  }

  it should "return the depth of a 3-deep Tree" in {
    val threeDeep =
      Branch(
        Branch(
          Leaf(2),
          Branch(
            Leaf(3),
            Leaf(4)
          )
        ),
        Branch(
          Leaf(5),
          Leaf(6)
        )
      )
    Exercise27.depth(threeDeep) should be (3)
  }

  it should "return the depth of a 4-deep Tree" in {
    val fourDeep =
      Branch(
        Branch(
          Branch(
            Leaf('c'),
            Leaf('d')
          ),
          Branch(
            Branch(
              Leaf('e'),
              Leaf('f')
            ),
            Leaf('g')
          )
        ),
        Leaf('h')
      )
    Exercise27.depth(fourDeep) should be (4)
  }

  it should "return the depth of a 5-deep Tree" in {
    val fiveDeep =
      Branch(
        Branch(
          Branch(
            Branch(
              Branch(
                Leaf(1),
                Leaf(2)
              ),
              Leaf(3)
            ),
            Leaf(4)
          ),
          Leaf(5)
        ),
        Leaf(6)
      )
    Exercise27.depth(fiveDeep) should be (5)
  }
}
