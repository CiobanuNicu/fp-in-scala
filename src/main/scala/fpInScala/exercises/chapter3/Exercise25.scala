package fpInScala.exercises.chapter3

import fpInScala.dataStructures._

object Exercise25 {
  // Write a function size that counts the number of nodes (leaves and branches) in a tree.

  def size [A] (t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l, r) => 1 + size(l) + size(r)
  }
}
