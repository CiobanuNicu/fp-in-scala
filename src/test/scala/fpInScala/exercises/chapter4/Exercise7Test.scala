package fpInScala.exercises.chapter4

import fpInScala.dataStructures.list._
import fpInScala.dataStructures.either._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise7Test extends FlatSpec with ShouldMatchers {
  "Either.sequence(List())" should "return Right(List())" in {
    Exercise7.sequence(List()) should be (Right(List()))
  }

  "Either.sequence(List(Right, Left))" should "return Left" in {
    Exercise7.sequence(List(Right(7), Left("error"))) should be (Left("error"))
  }

  "Either.sequence(List(Right(success), Right(success)))" should "return Right(List(success, success))" in {
    Exercise7.sequence(List(Right("success"), Right("success"))) should be (Right(List("success", "success")))
  }

  val eitherParseInt: String => Either[Exception, Int] = s => Either.Try(s.toInt)

  "Either.traverse(List())(eitherParseInt)" should "return Right(List())" in {
    Exercise7.traverse(List())(eitherParseInt) should be(Right(List()))
  }

  "Either.traverse(List('7', 'not an int')(eitherParseInt)" should "return Left(NumberFormatException)" in {
    val result = Exercise7.traverse(List("7", "not an int"))(eitherParseInt)
    result match {
      case Left(e) => e.getClass should be (classOf[NumberFormatException])
      case _ => fail(s"Was expecting Left(NumberFormatException), got ${result.getClass.getSimpleName}")
    }
  }

  "Either.traverse(List('1', '2', '3')(eitherParseInt)" should "return Right(List(1, 2, 3))" in {
    Exercise7.traverse(List("1", "2", "3"))(eitherParseInt) should be (Right(List(1, 2, 3)))
  }
}
