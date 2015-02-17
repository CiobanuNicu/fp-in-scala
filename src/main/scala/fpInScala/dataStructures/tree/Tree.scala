package fpInScala.dataStructures.tree

sealed trait Tree [+A]
case class Leaf [A] (value: A) extends Tree[A]
case class Branch [A] (left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def size [A] (t: Tree[A]): Int = fold(t)(x => 1)(1 + _ + _)

  def maximum (t: Tree[Int]): Int = fold(t)(x => x)(_ max _)

  def depth [A] (t: Tree[A]): Int = fold(t)(x => 0)((x, y) => 1 + (x max y))

  def map [A, B] (t: Tree[A]) (f: A => B): Tree[B] = fold(t)(a => Leaf(f(a)): Tree[B])(Branch(_, _))

  def fold [A, B] (t: Tree[A]) (z: A => B) (b: (B, B) => B): B = t match {
    case Leaf(x) => z(x)
    case Branch(l, r) => b(fold(l)(z)(b), fold(r)(z)(b))
  }
}
