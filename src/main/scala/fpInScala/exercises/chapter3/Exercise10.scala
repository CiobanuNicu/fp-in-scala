package fpInScala.exercises.chapter3

import fpInScala.dataStructures.List

object Exercise10 {

  // Our implementation of foldRight is not tail-recursive and will result in a StackOverflowError for large lists
  // (we say it's not stack-safe). Convince yourself that this is the case, and then write another general
  // list-recursion function, foldLeft, that is tail-recursive, using the techniques we discussed
  // in the previous chapter.

  // Again, I'll put the implementation on List itself:
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = List.foldLeft(as, z)(f)

}
