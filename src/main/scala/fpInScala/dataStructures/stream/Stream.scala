package fpInScala.dataStructures.stream

sealed trait Stream [+A] {
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h()) // Explicit forcing of the thunk using h()
  }
}

case object Empty extends Stream[Nothing]
case class Cons [+A] (h: () => A, t: () => Stream[A]) extends Stream[A]  // A nonempty stream consists of a head and a
                                                                         // tail, which are both non-strict. Due to
                                                                         // technical limitations, these are thunks that
                                                                         // must be explicitly forced, rather than
                                                                         // by-name parameters.

object Stream {
  // A smart constructor for creating a nonempty stream
  def cons [A] (hd: => A, tl: => Stream[A]): Stream[A] = {
    // We cache the head and tail as lazy values to avoid repeated evaluation
    lazy val head = hd
    lazy val tail = tl

    Cons(() => head, () => tail)
  }

  // A smart constructor for creating an empty stream of a particular type
  def empty [A]: Stream[A] = Empty

  // A convenient variable-argument method for constructing a Stream from multiple elements
  def apply [A] (as: A*): Stream[A] = if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}

