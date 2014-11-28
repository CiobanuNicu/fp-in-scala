package fpInScala.exercises

import org.scalatest.{FlatSpec, ShouldMatchers}
import fpInScala.dataStructures._
import fpInScala.exercises.Exercise3Point3._

class Exercise3Point3Test extends FlatSpec with ShouldMatchers {

  "An Exercise 3.3 Solution" should "return List(1) for Nil, 1" in {
    setHead(Nil, 1) should be (List(1))
  }

  it should "return List(a) for List(b), a" in {
    setHead(List("a"), "b") should be (List("b"))
  }

  it should "return List(4, 2, 3) for List(1, 2, 3), 4" in {
    setHead(List(1, 2, 3), 4) should be (List(4, 2, 3))
  }
}
