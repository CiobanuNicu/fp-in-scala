package fpInScala.dataStructures

import fpInScala.dataStructures.list._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, ShouldMatchers}

class CompanionObjectTests extends FlatSpec with ShouldMatchers with TableDrivenPropertyChecks {

  val expectedSums = Table(
    ("inputs",                                        "expected sums") ,
    (Array(0),                                         0),
    (Array(0, 1),                                      1),
    (Array(0, 1, 2),                                   3),
    (Array(0, 1, 2, 3),                                6),
    (Array(0, 1, 2, 3, 4),                            10),
    (Array(0, 1, 2, 3, 4, 5),                         15),
    (Array(0, 1, 2, 3, 4, 5, 6),                      21),
    (Array(0, 1, 2, 3, 4, 5, 6, 7),                   28),
    (Array(0, 1, 2, 3, 4, 5, 6, 7, 8),                36),
    (Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),             45),
    (Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10),         55),
    (Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),     66),
    (Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), 78)
  )

  forAll (expectedSums) { (inputs: Array[Int], expectedSum: Int) =>
    val list = List(inputs:_*)
    List.sum(list) shouldEqual expectedSum
  }


  val expectedProducts = Table(
    ("inputs",                                                                   "expected products"),
    (Array(0.0),                                                                         0.0),
    (Array(1.0),                                                                         1.0),
    (Array(1.0, 2.0),                                                                    2.0),
    (Array(1.0, 2.0, 3.0),                                                               6.0),
    (Array(1.0, 2.0, 3.0, 4.0),                                                         24.0),
    (Array(1.0, 2.0, 3.0, 4.0, 5.0),                                                   120.0),
    (Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0),                                              720.0),
    (Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0),                                        5040.0),
    (Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0),                                  40320.0),
    (Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0),                            362880.0),
    (Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0),                     3628800.0),
    (Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0),              39916800.0),
    (Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0),       479001600.0),
    (Array(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0,  9.0, 10.0, 11.0, 12.0),         0.0),
    (Array(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0,  0.0),         0.0)
  )

  forAll (expectedProducts) { (inputs: Array[Double], expectedProduct: Double) =>
    val list = List(inputs:_*)
    List.product(list) shouldEqual expectedProduct
  }
}
