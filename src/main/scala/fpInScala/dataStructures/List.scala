package fpInScala.dataStructures

sealed trait List [+A]
case object Nil extends List[Nothing]
case class Cons [+A] (head: A, tail: List[A]) extends List[A]

object List {
  def foldRight [A, B] (as: List[A], z: B) (f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  @annotation.tailrec
  def foldLeft [A, B] (as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    case Nil => z
  }

  def sum (ns: List[Int]): Int = foldLeft(ns, 0)(_ + _)

  def product (ds: List[Double]): Double = foldLeft(ds, 1.0)(_ * _)

  def length [A] (as: List[A]): Int = foldLeft(as, 0)((len, a) => len + 1)

  def reverse [A] (as: List[A]): List[A] = foldLeft(as, Nil: List[A]) ((b, a) => Cons(a, b))

  def appendRight [A] (a1: List[A], a2: List[A]): List[A] = foldRight(a1, a2)(Cons(_, _))

  def appendLeft [A] (a1: List[A], a2: List[A]): List[A] = foldLeft(reverse(a1), a2)((b, a) => Cons(a, b))

  def concatenate [A] (ls: List[List[A]]): List[A] = foldRight(ls, Nil: List[A]) (appendLeft)

  def apply [A] (as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}
