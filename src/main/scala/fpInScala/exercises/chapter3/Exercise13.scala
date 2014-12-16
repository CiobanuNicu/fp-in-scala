package fpInScala.exercises.chapter3

import fpInScala.dataStructures.List._
import fpInScala.dataStructures._

object Exercise13 {
  // Hard: Can you write foldLeft in terms of foldRight? How about the other way around?
  // Implementing foldRight via foldLeft is useful because it lets us implement foldRight tail-recursively,
  // which means it works even for large lists without overflowing the stack.

  def foldRightInTermsOfFoldLeftAndReverse [A, B] (as: List[A], z: B) (f: (A, B) => B): B =
    foldLeft(reverse(as), z) ((a, b) => f(b, a))

  def foldRightInTermsOfFoldLeft [A, B] (as: List[A], z: B) (f: (A, B) => B): B =
    foldLeft(as, (b: B) => b) ((g, a) => b => g(f(a, b)))(z)

  def foldLeftInTermsOfFoldRight [A, B] (as: List[A], z: B) (f: (B, A) => B): B =
    foldRight(as, (b: B) => b) ((a, g) => b => g(f(b, a)))(z)
}
