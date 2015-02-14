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
}
