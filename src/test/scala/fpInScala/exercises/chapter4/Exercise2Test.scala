package fpInScala.exercises.chapter4

import fpInScala.dataStructures.option._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise2Test extends FlatSpec with ShouldMatchers {
  "An Exercise 4.2 Solution" should "be None for an empty Seq" in {
    Exercise2.variance(Seq()) should be (None)
  }

  it should "be NaN for a seq of NaN" in {
    Exercise2.variance(Seq(Double.NaN)).map(_.isNaN).getOrElse(false) should be (right = true)
  }

  it should "be 0 for a single-element Seq" in {
    Exercise2.variance(Seq(3.4)) should be (Some(0))
  }

  it should "be 18.879088888888887 for a multiple-element Seq" in {
    Exercise2.variance(Seq(3.4, 10.45, 0.02)) should be (Some(18.879088888888887))
  }
}
