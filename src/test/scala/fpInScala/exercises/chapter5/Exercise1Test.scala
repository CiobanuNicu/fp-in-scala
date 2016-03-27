package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise1Test extends FlatSpec with ShouldMatchers with TableDrivenPropertyChecks {

  val lists = Table(
    "Possible list contents",
    Array(),
    Array(1),
    Array(1, 2),
    Array(1, 2, 3),
    Array(1, 2, 3, 4),
    Array(1, 2, 3, 4, 5),
    Array(1, 2, 3, 4, 5, 6)
  )

  forAll (lists) { (listContents: Array[_]) =>
    val expectedList = listContents.toList
    val streamOfContents = Stream(listContents: _*)

    streamOfContents.toList shouldEqual expectedList
  }
}
