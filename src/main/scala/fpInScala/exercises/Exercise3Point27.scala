package fpInScala.exercises

import fpInScala.dataStructures.{Branch, Leaf, Tree}

object Exercise3Point27 {
  // Write a function depth that returns the maximum path length from the root of a tree to any leaf.

  // Defer to the implementation on Tree
  def depth [A] (t: Tree[A]): Int = Tree.depth(t)
}
