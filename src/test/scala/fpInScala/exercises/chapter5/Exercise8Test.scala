package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise8Test extends FlatSpec with ShouldMatchers {
  "Stream.constant" should "generate an infinite stream of whatever value was provided" in {
    Stream.constant(4.3).take(1).toList should be (List(4.3))
    Stream.constant(1D).take(2).toList should be (List(1D, 1D))
    Stream.constant("ox").take(3).toList should be (List("ox", "ox", "ox"))
    Stream.constant(false).take(4).toList should be (List(false, false, false, false))
    Stream.constant(1).take(5).toList should be (List(1, 1, 1, 1, 1))
    Stream.constant('r').take(6).toList should be (List('r', 'r', 'r', 'r', 'r', 'r'))
  }
}
