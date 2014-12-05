package fpInScala.exercises

import org.scalatest.{ShouldMatchers, FlatSpec}
import fpInScala.dataStructures._

class Exercise3Point17Test extends FlatSpec with ShouldMatchers {
  "An Exercise 3.17 Solution" should "convert Nil to Nil" in {
    Exercise3Point17.toStrings(Nil) should be (Nil)
  }

  it should "convert a single-element list to strings" in {
    Exercise3Point17.toStrings(List(1.0)) should be (List("1.0"))
  }

  it should "convert a multiple-element list to strings" in {
    Exercise3Point17.toStrings(List(1.2, 3.4, 5.6, 7.8)) should be (List("1.2", "3.4", "5.6", "7.8"))
  }
}
