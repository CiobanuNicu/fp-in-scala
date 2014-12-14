package fpInScala.exercises

import org.scalatest.{FlatSpec, ShouldMatchers}
import fpInScala.dataStructures._

class Exercise3Point29Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.29 Solution" should "show that Tree.size in terms of Tree.fold should be correct" in {
    val size1 = Leaf(1)
    val size2 = Branch(Leaf(1), Leaf(2))
    val size3 = Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))
    val size4 = Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4)))

    Exercise3Point29.size(size1) should be (Exercise3Point25.size(size1))
    Exercise3Point29.size(size2) should be (Exercise3Point25.size(size2))
    Exercise3Point29.size(size3) should be (Exercise3Point25.size(size3))
    Exercise3Point29.size(size4) should be (Exercise3Point25.size(size4))
  }

  it should "show that Tree.maximum in terms of Tree.fold should be correct" in {
    val leaf5 = Leaf(5)
    val leaf4 = Leaf(4)
    val leaf3 = Leaf(3)
    val leaf2 = Leaf(2)
    val leaf1 = Leaf(1)

    Exercise3Point29.maximum(leaf5) should be (Exercise3Point26.maximum(Leaf(5)))
    Exercise3Point29.maximum(leaf4) should be (Exercise3Point26.maximum(Leaf(4)))
    Exercise3Point29.maximum(leaf3) should be (Exercise3Point26.maximum(Leaf(3)))
    Exercise3Point29.maximum(leaf2) should be (Exercise3Point26.maximum(Leaf(2)))
    Exercise3Point29.maximum(leaf1) should be (Exercise3Point26.maximum(Leaf(1)))


    val max3 = Branch(Leaf(2), Leaf(3))
    val max10 = Branch(Leaf(10), Leaf(7))
    val max4 = Branch(Leaf(4), Leaf(4))

    Exercise3Point29.maximum(max3) should be (Exercise3Point26.maximum(max3))
    Exercise3Point29.maximum(max10) should be (Exercise3Point26.maximum(max10))
    Exercise3Point29.maximum(max4) should be (Exercise3Point26.maximum(max4))

    val max15 = Branch(Branch(Leaf(10), Leaf(7)), Leaf(15))
    val max21 = Branch(Branch(Leaf(10), Leaf(7)), Branch(Leaf(15), Leaf(21)))
    val max99 = Branch(Branch(Leaf(10), Leaf(7)), Branch(Branch(Leaf(15), Leaf(21)), Branch(Branch(Leaf(3), Branch(Leaf(99), Leaf(18))), Branch(Leaf(12), Leaf(11)))))

    Exercise3Point29.maximum(max15) should be (Exercise3Point26.maximum(max15))
    Exercise3Point29.maximum(max21) should be (Exercise3Point26.maximum(max21))
    Exercise3Point29.maximum(max99) should be (Exercise3Point26.maximum(max99))
  }

  it should "show that Tree.depth in terms of Tree.fold should be correct" in {
    val zeroDeep = Leaf("a")
    val oneDeep = Branch(Leaf(1.0), Leaf(2.0))
    val twoDeep = Branch(Leaf(1.0), Branch(Leaf(2.0), Leaf(3.0)))
    val threeDeep = Branch(Branch(Leaf(2), Branch(Leaf(3), Leaf(4))), Branch(Leaf(5), Leaf(6)))
    val fourDeep = Branch(Branch(Branch(Leaf('c'), Leaf('d')), Branch(Branch(Leaf('e'), Leaf('f')), Leaf('g'))), Leaf('h'))
    val fiveDeep = Branch(Branch(Branch(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3)), Leaf(4)), Leaf(5)), Leaf(6))

    Exercise3Point29.depth(zeroDeep)  should be (Exercise3Point27.depth(zeroDeep))
    Exercise3Point29.depth(oneDeep)   should be (Exercise3Point27.depth(oneDeep))
    Exercise3Point29.depth(twoDeep)   should be (Exercise3Point27.depth(twoDeep))
    Exercise3Point29.depth(threeDeep) should be (Exercise3Point27.depth(threeDeep))
    Exercise3Point29.depth(fourDeep)  should be (Exercise3Point27.depth(fourDeep))
    Exercise3Point29.depth(fiveDeep)  should be (Exercise3Point27.depth(fiveDeep))
  }

  it should "show that Tree.map in terms of Tree.fold should be correct" in {
    val plusOne: Int => Int = x => x + 1
    val toString: Int => String = x => x.toString
    val inHalf: Int => Double = x => x / 2.0

    val leaf3 = Leaf(3)
    val smallTree = Branch(Leaf(2), Leaf(3))

    val bigTree =
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

    Exercise3Point29.map(leaf3)(plusOne) should be (Exercise3Point28.map(leaf3)(plusOne))
    Exercise3Point29.map(smallTree)(toString) should be (Exercise3Point28.map(smallTree)(toString))
    Exercise3Point29.map(bigTree)(inHalf) should be (Exercise3Point28.map(bigTree)(inHalf))
  }

}
