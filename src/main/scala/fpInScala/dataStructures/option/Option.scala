package fpInScala.dataStructures.option

import fpInScala.dataStructures.list
import fpInScala.dataStructures.list.{Cons, Nil}

sealed trait Option [+A] {
  def map [B] (f: A => B): Option[B] = this match {
    case Some(x) => Some(f(x))
    case None => None
  }

  def flatMap [B] (f: A => Option[B]): Option[B] = map(f) getOrElse None

  def getOrElse [B >: A] (default: => B): B = this match {
    case Some(x) => x
    case None => default
  }

  def orElse [B >: A] (ob: => Option[B]): Option[B] = map(Some(_)) getOrElse ob

  def filter (f: A => Boolean): Option[A] = flatMap((a) => if (f(a)) Some(a) else None)
}
case class Some [+A] (get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {
  def map2 [A, B, C] (a: Option[A], b: Option[B]) (f: (A, B) => C): Option[C] = a flatMap(x => b.map(y => f(x, y)))

  def sequence [A] (as: list.List[Option[A]]): Option[list.List[A]] = traverse(as)(x => x)

  def traverse [A, B] (as: list.List[A]) (f: A => Option[B]): Option[list.List[B]] = list.List.foldRight(as, Some(Nil): Option[list.List[B]]) {
    (a, b) => map2(f(a), b)(Cons(_, _))
  }
}
