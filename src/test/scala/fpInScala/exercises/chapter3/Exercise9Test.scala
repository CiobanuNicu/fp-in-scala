package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise9Test extends FlatSpec with ShouldMatchers with TableDrivenPropertyChecks {

  val listsAndLengths = Table(
    ("lists",                             "expected lengths"),
    (Nil,                                 0),
    (List(0),                             1),
    (List(0, 0),                          2),
    (List(0, 0, 0),                       3),
    (List(0, 0, 0, 0),                    4),
    (List(0, 0, 0, 0, 0),                 5),
    (List(0, 0, 0, 0, 0, 0),              6),
    (List(0, 0, 0, 0, 0, 0, 0),           7),
    (List(0, 0, 0, 0, 0, 0, 0, 0),        8),
    (List(0, 0, 0, 0, 0, 0, 0, 0, 0),     9),
    (List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), 10)
  )

  forAll (listsAndLengths) { (list: List[Int], expectedLength: Int) =>
    Exercise9.length(list) should be === expectedLength
  }
}
