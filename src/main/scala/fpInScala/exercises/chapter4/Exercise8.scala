package fpInScala.exercises.chapter4

object Exercise8 {
  // In this implementation, map2 is only able to report one error, even if both the name and the age are invalid. What
  // would you need to change in order to report both errors? Would you change map2 or the signature of mkPerson? Or
  // could you create a new data type that captures this requirement better than Either does, with some additional
  // structure? How would orElse, traverse, and sequence behave differently for that data type?
  case class Person (name: Name, age: Age)

  sealed case class Name(value: String)
  sealed case class Age(value: Int)

  object Person {
    def mkName (name: String): Eithers[String, Name] =
      if (name == "" || name == null) Errors(Seq("Name is empty."))
      else Success(Name(name))

    def mkAge (age: Int): Eithers[String, Age] =
      if (age < 0) Errors(Seq("Age is out of range."))
      else Success(Age(age))

    def mkPerson (name: String, age: Int): Eithers[String, Person] =
      mkName(name).map2(mkAge(age))(Person(_, _))
  }

  trait Eithers [+E, +A] {
    def map [B] (f: A => B): Eithers[E, B] = flatMap(a => Success(f(a)))

    def flatMap [EE >: E, B] (f: A => Eithers[EE, B]): Eithers[EE, B] = this match {
      case Success(a) => f(a)
      case Errors(e) => Errors(e)
    }

    def map2 [EE >: E, B, C] (b: Eithers[EE, B]) (f: (A, B) => C): Eithers[EE, C] = this match {
      case Success(a) => b map (f(a, _))
      case Errors(myErrors) => b match {
        case Errors(yourErrors) => Errors(myErrors ++ yourErrors)
        case _ => Errors(myErrors)
      }
    }

    def orElse [EE >: E, B >: A] (b: => Eithers[EE, B]): Eithers[EE, B] = (this, b) match {
      case (Success(_), _) => this
      case (Errors(_), Success(_)) => b
      case (Errors(myErrors), Errors(yourErrors)) => Errors(myErrors ++ yourErrors)
    }
  }

  case class Errors [+E] (get: Seq[E]) extends Eithers[E, Nothing]
  case class Success [+A] (get: A) extends Eithers[Nothing, A]

  object Eithers {
    def traverse [E, A, B] (as: List[A]) (f: A => Eithers[E, B]): Eithers[E, List[B]] =
      as.foldRight [Eithers[E, List[B]]] (Success(Nil)) {
        (a, b) => f(a).map2(b)(_ :: _)
      }

    def sequence [E, A] (es: List[Eithers[E, A]]): Eithers[E, List[A]] = traverse(es)(identity)
  }
}
