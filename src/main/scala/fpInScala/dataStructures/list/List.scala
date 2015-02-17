package fpInScala.dataStructures.list

sealed trait List [+A]
case object Nil extends List[Nothing]
case class Cons [+A] (head: A, tail: List[A]) extends List[A]

object List {
  def foldRight [A, B] (as: List[A], z: B) (f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  @annotation.tailrec
  def foldLeft [A, B] (as: List[A], z: B) (f: (B, A) => B): B = as match {
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    case Nil => z
  }

  def sum (ns: List[Int]): Int = foldLeft(ns, 0) (_ + _)

  def product (ds: List[Double]): Double = foldLeft(ds, 1.0) (_ * _)

  def length [A] (as: List[A]): Int = foldLeft(as, 0) ((len, _) => len + 1)

  def reverse [A] (as: List[A]): List[A] = foldLeft(as, Nil: List[A]) ((b, a) => Cons(a, b))

  def appendRight [A] (a1: List[A], a2: List[A]): List[A] = foldRight(a1, a2)(Cons(_, _))

  def appendLeft [A] (a1: List[A], a2: List[A]): List[A] = foldLeft(reverse(a1), a2)((b, a) => Cons(a, b))

  def concatenate [A] (ls: List[List[A]]): List[A] = foldRight(ls, Nil: List[A]) (appendLeft)

  def map [A, B] (as: List[A]) (f: A => B): List[B] = foldRight(as, Nil: List[B]) ((a, b) => Cons(f(a), b))

  def flatMap [A, B] (as: List[A]) (f: A => List[B]): List[B] = concatenate(map(as)(f))

  def filter [A] (as: List[A]) (f: A => Boolean): List[A] = foldRight(as, Nil: List[A]) {
    (a, b) => if (f(a)) Cons(a, b) else b
  }

  def zipWith [A, B] (list1: List[A], list2: List[A]) (f: (A, A) => B): List[B] = (list1, list2) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
  }

  @annotation.tailrec
  def startsWith [A] (list: List[A], prefix: List[A]): Boolean = (list, prefix) match {
    case (_, Nil) => true
    case (Cons(h, t), Cons(pH, pT)) if h == pH => startsWith(t, pT)
    case _ => false
  }

  @annotation.tailrec
  def hasSubsequence [A] (list: List[A], sub: List[A]): Boolean = list match {
    case Nil => false
    case Cons(h, t) if startsWith(list, sub) => true
    case Cons(h, t) => hasSubsequence(t, sub)
  }

  def apply [A] (as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}
