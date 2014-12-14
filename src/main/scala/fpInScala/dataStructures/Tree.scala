package fpInScala.dataStructures

sealed trait Tree [+A]
case class Leaf [A] (value: A) extends Tree[A]
case class Branch [A] (left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def size [A] (t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l, r) => 1 + size(l) + size(r)
  }

  def depth [A] (t: Tree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l, r) => 1 + (depth(l) max depth(r))
  }
}
