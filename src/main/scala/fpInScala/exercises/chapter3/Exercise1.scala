package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list.List._
import fpInScala.dataStructures.list._

object Exercise1 {

  // What will be the result of the following match expression?
  val x = List(1,2,3,4,5) match {
    case Cons(a, Cons(2, Cons(4, _))) => a
    case Nil => 42
    case Cons(a, Cons(b, Cons(3, Cons(4, _)))) => a + b
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  // val x: Int = 3
}
