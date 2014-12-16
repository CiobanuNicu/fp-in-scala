package fpInScala.exercises.chapter3

import fpInScala.dataStructures._

object Exercise22 {
  // Write a function that accepts two lists and constructs a new list by adding corresponding elements.
  // For example, List(1,2,3) and List(4,5,6) become List(5,7,9).

  def addLists (list1: List[Int], list2: List[Int]): List[Int] = (list1, list2) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, addLists(t1, t2))
  }
}
