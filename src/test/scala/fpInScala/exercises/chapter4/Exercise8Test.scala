package fpInScala.exercises.chapter4

import fpInScala.exercises.chapter4.Exercise8._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise8Test extends FlatSpec with ShouldMatchers {

  "Person.mkName" should "perform correctly" in {
    Person.mkName("") should be (Errors(Seq("Name is empty.")))
    Person.mkName(null) should be (Errors(Seq("Name is empty.")))
    Person.mkName("Zachary") should be (Success(Name("Zachary")))
    Person.mkName("Hubert") should be (Success(Name("Hubert")))
  }

  "Person.mkAge" should "perform correctly" in {
    Person.mkAge(-5) should be (Errors(Seq("Age is out of range.")))
    Person.mkAge(0) should be (Success(Age(0)))
    Person.mkAge(10) should be (Success(Age(10)))
    Person.mkAge(38) should be (Success(Age(38)))
  }

  "Person.mkPerson" should "return a person when there are no errors" in {
    Person.mkPerson("Abraham", 42) should be (Success(Person(Name("Abraham"), Age(42))))
    Person.mkPerson("Theodore", 29) should be (Success(Person(Name("Theodore"), Age(29))))
  }

  "Person.mkPerson" should "report single errors" in {
    Person.mkPerson(null, 25) should be (Errors(Seq("Name is empty.")))
    Person.mkPerson("", 25) should be (Errors(Seq("Name is empty.")))
    Person.mkPerson("Grover", -5) should be (Errors(Seq("Age is out of range.")))
  }

  "Person.mkPerson" should "report multiple errors" in {
    val result = Person.mkPerson(null, -5)

    result match {
      case Success(_) => fail("Got Success when Errors was expected")
      case Errors(errorList) => {
        errorList should contain ("Name is empty.")
        errorList should contain ("Age is out of range.")
      }
    }
  }

  "Eithers.orElse" should "return itself when it's a Success" in {
    Success("Winner!").orElse(Success("Another Winner!")) should be (Success("Winner!"))
    Success("Winner!").orElse(Errors(Seq("Loser", "But it's not my fault"))) should be (Success("Winner!"))
  }

  "Eithers.orElse" should "return the alternate when it's Errors but the alternate is a Success" in {
    Errors(Seq("Close but nice try")).orElse(Success("Consolation prize")) should be (Success("Consolation prize"))
  }

  "Eithers.orElse" should "return Errors combining the errors from both itself and the alternate when the alternate is also Errors" in {
    val result = Errors(Seq("Insult")).orElse(Errors(Seq("Injury")))

    result match {
      case Success(_) => fail("Got Success when Errors was expected")
      case Errors(errorList) => {
        errorList should contain ("Insult")
        errorList should contain ("Injury")
      }
    }
  }

  val parseInt: String => Eithers[String, Int] = (s: String) =>
    try {
      Success(s.toInt)
    } catch {
      case e: Throwable => Errors(Seq(e.getMessage))
    }

  "Eithers.traverse" should "return a Success of empty list for an empty list" in {
    Eithers.traverse(List())(parseInt) should be (Success(List()))
  }

  "Eithers.traverse" should "return a Success of list of all integers parsed in the input list" in {
    Eithers.traverse(List("1", "2", "3"))(parseInt) should be (Success(List(1, 2, 3)))
  }

  "Eithers.traverse" should "return an Errors of list of one error for a list of string that won't parse" in {
    Eithers.traverse(List("not an int"))(parseInt) should be (Errors(Seq("For input string: \"not an int\"")))
  }

  "Eithers.traverse" should "return an Errors of list of two errors for a list of two strings that won't parse" in {
    Eithers.traverse(List("a", "b"))(parseInt) should be (Errors(Seq("For input string: \"a\"", "For input string: \"b\"")))
  }

  "Eithers.sequence" should "return Success(List()) for List()" in {
    Eithers.sequence(List()) should be (Success(List()))
  }

  "Eithers.sequence" should "return Success(List(1)) for List(Success(1)" in {
    Eithers.sequence(List(Success(1))) should be (Success(List(1)))
  }

  "Eithers.sequence" should "return Success(List(1, 2)) for List(Success(1), Success(2)" in {
    Eithers.sequence(List(Success(1), Success(2))) should be (Success(List(1, 2)))
  }

  "Eithers.sequence" should "return Errors(Seq(foo)) for List(Errors(Seq(foo))" in {
    Eithers.sequence(List(Errors(Seq("foo")))) should be (Errors(Seq("foo")))
  }

  "Eithers.sequence" should "return Errors(Seq(foo, bar)) for List(Errors(Seq(foo)), Errors(Seq(bar)))" in {
    Eithers.sequence(List(Errors(Seq("foo")), Errors(Seq("bar")))) should be (Errors(Seq("foo", "bar")))
  }

  "Eithers.sequence" should "return Errors(Seq(foo, bar)) for List(Success(7), Errors(Seq(foo)), Errors(Seq(bar)), Success(8))" in {
    Eithers.sequence(List(Success(7), Errors(Seq("foo")), Errors(Seq("bar")), Success(8))) should be (Errors(Seq("foo", "bar")))
  }
}
