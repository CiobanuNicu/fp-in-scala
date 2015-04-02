package fpInScala.exercises.chapter4

import fpInScala.dataStructures.list._
import fpInScala.dataStructures.either._

object Exercise7 {
  // Implement sequence and traverse for Either.
  // These should return the first error that's encountered, if there is one.

  // Defer to the implementations on Either
  def sequence [E, A] (es: List[Either[E, A]]): Either[E, List[A]] = Either.sequence(es)
  def traverse [E, A, B] (as: List[A]) (f: A => Either[E, B]): Either[E, List[B]] = Either.traverse(as)(f)
}
