package fpInScala.exercises.chapter3

import fpInScala.dataStructures._

object Exercise26 {
  // Write a function maximum that returns the maximum element in a Tree[Int].
  // (Note: In Scala, you can use x.max(y) or x max y to compute the maximum of two integers x andy.)

  def maximum (t: Tree[Int]): Int = t match {
    case Leaf(n) => n
    case Branch(l, r) => maximum(l) max maximum(r)
  }
}
