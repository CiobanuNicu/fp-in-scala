package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise6Test extends FlatSpec with ShouldMatchers {
  "Stream.headOption" should "return None from an empty stream" in {
    Stream().headOption.isEmpty should be (true)
  }

  it should "return Some of the first element when the stream is not empty" in {
    Stream(1, 2, 3).headOption should be (Some(1))
    Stream('a', 'b', 'c').headOption should be (Some('a'))
    Stream(2.3, 4.5, 6.7).headOption should be (Some(2.3))
  }
}
