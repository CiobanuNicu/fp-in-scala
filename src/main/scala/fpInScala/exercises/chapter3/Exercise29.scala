package fpInScala.exercises.chapter3

import fpInScala.dataStructures._

object Exercise29 {
  // Generalize size, maximum, depth, and map, writing a new function fold that abstracts over their similarities.
  // Re-implement them in terms of this more general function. Can you draw an analogy between this fold function and
  // the left and right folds for List?

  // Defer to the implementations on Tree
  def fold [A, B] (t: Tree[A]) (l: A => B) (b: (B, B) => B): B = Tree.fold(t)(l)(b)
  def size [A] (t: Tree[A]): Int = Tree.size(t)
  def maximum (t: Tree[Int]): Int = Tree.maximum(t)
  def depth [A] (t: Tree[A]): Int = Tree.depth(t)
  def map [A, B] (t: Tree[A]) (f: A => B): Tree[B] = Tree.map(t)(f)
}
