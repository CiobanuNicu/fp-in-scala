package fpInScala.exercises.chapter4

import fpInScala.dataStructures.either._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise6Test extends FlatSpec with ShouldMatchers {
  "Left of anything map f" should "return the same Left" in {
    val left: Either[Int, String] = Left(5)

    val ae = new ArithmeticException()
    val lae: Either[Exception, Int] = Left(ae)

    left.map(_.toUpperCase) should be (left)
    lae.map(_ / 7) should be (lae)
  }

  "Right of x map f" should "return Right of f of x" in {
    val foo: Either[Int, String] = Right("foo")
    val fortyNine: Either[Exception, Int] = Right(49)

    foo.map(_.toUpperCase) should be (Right("FOO"))
    fortyNine.map(_ / 7) should be (Right(7))
  }

  "Left of anything flatMap f" should "return the same Left" in {
    val leftOfFive = Left(5): Either[Int, String]

    val ae = new ArithmeticException()
    val lae = Left(ae): Either[Exception, Int]

    leftOfFive.flatMap(Right(_)) should be (leftOfFive)
    lae.flatMap(Right(_)) should be (Left(ae))
  }

  "Right of x flatMap f" should "return f of x" in {
    val foo = Right("foo"): Either[Int, String]
    val fortyNine: Either[Exception, Int] = Right(49)

    foo.flatMap(s => Right(s.reverse)) should be (Right("oof"))
    fortyNine.flatMap(i => Right(i + 1)) should be (Right(50))
  }

  "Left of anything orElse alternate" should "return the alternate" in {
    val five: Either[Int, String] = Left(5)
    val lae: Either[Exception, Int] = Left(new ArithmeticException)

    five.orElse(Right("alternate")) should be (Right("alternate"))
    lae.orElse(Right(12)) should be (Right(12))
  }

  "Right of anything orElse alternate" should "return the same Right" in {
    val bar: Either[Int, String] = Right("bar")
    val fortyTwo: Either[Exception, Int] = Right(42)

    bar.orElse(Right("baz")) should be (bar)
    fortyTwo.orElse(Right(21)) should be (fortyTwo)
  }

  "Left of anything map2 anything, f" should "return that same Left" in {
    val ten: Either[Int, String] = Left(10)
    val ae = new ArithmeticException
    val lae = Left(ae): Either[Exception, Int]

    ten.map2(Right("success"))(_ + _) should be (ten)
    lae.map2(Left(new IndexOutOfBoundsException))(_ + _) shouldEqual lae
  }

  "Right of x map2 Left of y, f" should "return Left of y" in {
    val qux: Either[Int, String] = Right("qux")
    val twentySeven: Either[Exception, Int] = Right(27)
    val twentyFive: Either[Int, String] = Left(25)
    val ia = new IllegalArgumentException
    val lia: Either[Exception, Int] = Left(ia)

    qux.map2(twentyFive)(_.concat(_)) should be (twentyFive)
    twentySeven.map2(lia)(_ * _) should be (Left(ia))
  }

  "Right of x map2 Right of y, f" should "return Right of f, x, y" in {
    val foo: Either[Int, String] = Right("foo")
    val bar: Either[Int, String] = Right("bar")

    foo.map2(bar)((a, b) => a.concat(b.toUpperCase)) should be (Right("fooBAR"))
  }
}
