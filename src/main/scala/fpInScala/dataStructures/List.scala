package fpInScala.dataStructures

sealed trait List [+A]
case object Nil extends List[Nothing]
case class Cons [+A] (head: A, tail: List[A]) extends List[A]

object List {
  def foldRight [A, B] (as: List[A], z: B) (f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sum (ns: List[Int]): Int = foldRight(ns, 0)(_ + _)

  def product (ds: List[Double]): Double = foldRight(ds, 1.0)(_ *_)

  def apply [A] (as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}
