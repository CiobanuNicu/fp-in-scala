package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise14Test extends FlatSpec with ShouldMatchers {
  "Stream.startsWith" should "return true for Stream(1, 2, 3) startsWith Stream(1, 2, 3)" in {
    Stream(1, 2, 3) startsWith Stream(1, 2, 3) should be (true)
  }

  it should "return true for Stream(1, 2, 3) startsWith Stream(1, 2)" in {
    Stream(1, 2, 3) startsWith Stream(1, 2) should be (true)
  }

  it should "return true for Stream(1, 2, 3) startsWith Stream(1)" in {
    Stream(1, 2, 3) startsWith Stream(1) should be (true)
  }

  it should "return true for Stream(1, 2, 3) startsWith Stream()" in {
    Stream(1, 2, 3) startsWith Stream() should be (true)
  }

  it should "return true for Stream() startsWith Stream()" in {
    Stream() startsWith Stream() should be (true)
  }

  it should "return false for Stream() startsWith Stream(1)" in {
    Stream() startsWith Stream(1) should be (false)
  }

  it should "return false for Stream('a') startsWith Stream('b')" in {
    Stream('a') startsWith Stream('b') should be (false)
  }
}
