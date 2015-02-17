package fpInScala.exercises.chapter3

import fpInScala.dataStructures.tree._

object Exercise27 {
  // Write a function depth that returns the maximum path length from the root of a tree to any leaf.

  def depth [A] (t: Tree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l, r) => 1 + (depth(l) max depth(r))
  }
}
