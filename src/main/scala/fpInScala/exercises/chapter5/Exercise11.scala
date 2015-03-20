package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._

object Exercise11 {
  // Write a more general stream-building function called unfold. It takes an initial state, and a function for
  // producing both the next state and the next value in the generated stream.
  def unfold [A, S] (z: S) (f: S => Option[(A, S)]): Stream[A] = Stream.unfold(z)(f)

  def fibsByUnfold: Stream[Int] = unfold ((0, 1)) { case (a, b) => Some((a, (b, a + b))) }
}
