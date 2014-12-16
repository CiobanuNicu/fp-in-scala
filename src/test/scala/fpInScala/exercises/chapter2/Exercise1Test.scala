package fpInScala.exercises.chapter2

import fpInScala.exercises.chapter2.Exercise1._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise1Test extends FlatSpec with ShouldMatchers with TableDrivenPropertyChecks {
  val fibonaccis = Table(
    ("n", "fib of n"),
    (0,   0   ),
    (1,   1   ),
    (2,   1   ),
    (3,   2   ),
    (4,   3   ),
    (5,   5   ),
    (6,   8   ),
    (7,   13  ),
    (8,   21  ),
    (9,   34  ),
    (10,  55  ),
    (11,  89  ),
    (12,  144 ),
    (13,  233 ),
    (14,  377 ),
    (15,  610 ),
    (16,  987 ),
    (17,  1597),
    (18,  2584),
    (19,  4181),
    (20,  6765)
  )

  forAll (fibonaccis) { (n: Int, expectedFibOfN: Int) =>
    val actualFibOfN = fib(n)
    actualFibOfN should be === expectedFibOfN
  }
}
