package fpInScala.dataStructures

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

  def sequence [A] (as: List[Option[A]]): Option[List[A]] = List.foldRight(as, Some(Nil): Option[List[A]]) {
    (a, b) => map2(a, b)(Cons(_, _))
  }

  def traverse [A, B] (as: List[A]) (f: A => Option[B]): Option[List[B]] = List.foldRight(as, Some(Nil): Option[List[B]]) {
    (a, b) => map2(f(a), b)(Cons(_, _))
  }
}
