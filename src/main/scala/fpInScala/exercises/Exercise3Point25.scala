package fpInScala.exercises

import fpInScala.dataStructures._

object Exercise3Point25 {
  // Write a function size that counts the number of nodes (leaves and branches) in a tree.

  // Defer to the implementation in Tree
  def size [A] (t: Tree[A]): Int = Tree.size(t)
}
