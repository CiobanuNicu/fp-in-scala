package fpInScala.exercises.chapter3

import fpInScala.dataStructures._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise20Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.20 Solution" should "flatMap Nil to Nil, regardless of f" in {
    Exercise20.flatMap(Nil)(x => x) should be (Nil)
    Exercise20.flatMap(Nil)(_ => Nil) should be (Nil)
    Exercise20.flatMap(Nil)(x => List(scala.util.Random.nextInt())) should be (Nil)
  }

  it should "flatMap List(1, 2, 3) with (i => List(i, i) into List(1, 1, 2, 2, 3, 3)" in {
    Exercise20.flatMap(List(1, 2, 3))(i => List(i, i)) should be (List(1, 1, 2, 2, 3, 3))
  }
}
