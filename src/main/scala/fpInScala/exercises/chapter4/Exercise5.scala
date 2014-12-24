package fpInScala.exercises.chapter4

import fpInScala.dataStructures._

object Exercise5 {
  // Implement traverse. It's straightforward to do using map and sequence, but try for a more efficient implementation
  // that only looks at the list once. In fact, implement sequence in terms of traverse.

  // Defer to the implementation on Option
  def traverse [A, B] (a: List[A]) (f: A => Option[B]): Option[List[B]] = Option.traverse(a)(f)
}
