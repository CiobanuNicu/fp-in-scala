package fpInScala.exercises.chapter3

import fpInScala.dataStructures.list._

object Exercise24 {
  // Hard: As an example, implement hasSubsequence for checking whether a List contains another List as a subsequence.
  // For instance, List(1,2,3,4) would have List(1,2), List(2,3), and List(4) as subsequences, among others.
  // You may have some difficulty finding a concise purely functional implementation that is also efficient.
  // That's okay. Implement the function however comes most naturally. We'll return to this implementation in chapter 5
  // and hopefully improve on it. Note: Any two values x and y can be compared for equality in Scala
  // using the expression x == y.

  def hasSubsequence [A] (sup: List[A], sub: List[A]): Boolean = List.hasSubsequence(sup, sub)
}
