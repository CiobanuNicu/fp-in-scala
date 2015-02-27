package fpInScala.dataStructures.stream

sealed trait Stream [+A] {
  import Stream._

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h()) // Explicit forcing of the thunk using h()
  }

  def toList: List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toList
  }

  def take (n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1  => cons(h(), t().take(n - 1))
    case Cons(h, _) if n == 1 => cons(h(), empty)
    case _ => empty
  }

  def takeWhile (p: A => Boolean): Stream[A] = foldRight (empty[A]) {
    (h, t) => if (p(h)) cons(h, t) else empty
  }

  def drop (n: Int): Stream[A] = this match {
    case Cons(_, t) if n > 1  => t().drop(n - 1)
    case Cons(_, t) if n == 1 => t()
    case _ => this
  }

  def exists (p: A => Boolean): Boolean = foldRight (false) ((a, b) => p(a) || b)

  def foldRight [B] (z: => B) (f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def forAll (p: A => Boolean): Boolean = foldRight (true) ((a, b) => p(a) && b)
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

