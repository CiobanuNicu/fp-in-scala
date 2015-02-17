package fpInScala.exercises.chapter4

import fpInScala.dataStructures.list._
import fpInScala.dataStructures.option._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise5Test extends FlatSpec with ShouldMatchers {
  val parseInt: String => Option[Int] = (s: String) => try { Some(s.toInt) } catch { case e: Throwable => None }

  "An Exercise 4.5 Solution" should "return None if any element in the list yields None" in {
    Exercise5.traverse(List("not an int"))(parseInt) should be (None)
  }

  it should "return an Option of empty list for empty list" in {
    Exercise5.traverse(List())(parseInt) should be (Some(List()))
  }

  it should "return an Option of the List of results if all the operations were successful" in {
    Exercise5.traverse(List("1", "2", "3"))(parseInt) should be (Some(List(1, 2, 3)))
  }
}
