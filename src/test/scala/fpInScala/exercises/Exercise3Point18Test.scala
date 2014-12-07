package fpInScala.exercises

import org.scalatest.{ShouldMatchers, FlatSpec}
import fpInScala.dataStructures._

class Exercise3Point18Test extends FlatSpec with ShouldMatchers {

  "An Exercise 3.18 Solution" should "map Nil to Nil with any function" in {
    Exercise3Point18.map(Nil) (x => x) should be (Nil)
    Exercise3Point18.map(Nil) ((x: String) => x + " mapped") should be (Nil)
    Exercise3Point18.map(Nil) ((x: Int) => x + 1) should be (Nil)
    Exercise3Point18.map(Nil) (plusOne) should be (Nil)
    Exercise3Point18.map(Nil) (doubleToString) should be (Nil)
  }

  val plusOne: Int => Int = x => x + 1

  it should "map an increment function across a single-element list" in {
    Exercise3Point18.map(List(1)) (plusOne) should be (List(2))
  }

  it should "map an increment function across multiple-element list" in {
    Exercise3Point18.map(List(1, 2, 3, 4, 5)) (plusOne) should be (List(2, 3, 4, 5, 6))
  }

  val doubleToString: Double => String = d => d.toString

   it should "map a double to string function across a single-element list" in {
    Exercise3Point18.map(List(1.0)) (doubleToString) should be (List("1.0"))
  }

  it should "map a double to string function across a multiple-element list" in {
    Exercise3Point18.map(List(1.2, 3.4, 5.6, 7.8)) (doubleToString) should be (List("1.2", "3.4", "5.6", "7.8"))
  }
}
