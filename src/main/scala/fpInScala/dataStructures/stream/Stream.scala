package fpInScala.dataStructures.stream

sealed trait Stream [+A] {
  import Stream._

  def headOption: Option[A] = foldRight (None: Option[A]) ((a, b) => Some(a))

  def toList: List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toList
  }

  def take (n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1  => cons(h(), t().take(n - 1))
    case Cons(h, _) if n == 1 => cons(h(), empty)
    case _ => empty
  }

  def takeViaUnfold (n: Int): Stream[A] = unfold ((this, n)) {
    case (Cons(h, t), x) if x >= 1 => Some(h(), (t(), x - 1))
    case _ => None
  }

  def takeWhile (p: A => Boolean): Stream[A] = foldRight (empty[A]) {
    (h, t) => if (p(h)) cons(h, t) else empty
  }

  def takeWhileViaUnfold (p: A => Boolean): Stream[A] = unfold (this) {
    case Cons(h, t) if p(h()) => Some(h(), t())
    case _ => None
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

  def map [B] (f: A => B): Stream[B] = foldRight (empty[B]) ((a, b) => cons(f(a), b))

  def mapViaUnfold [B] (f: A => B): Stream[B] = unfold (this) {
    case Cons(h, t) => Some(f(h()), t())
    case Empty => None
  }

  def filter (f: A => Boolean): Stream[A] = foldRight (empty[A]) ((a, b) => if (f(a)) cons(a, b) else b)

  def append [B >: A] (s: => Stream[B]): Stream[B] = foldRight (s) ((a, b) => cons(a, b))

  def flatMap [B] (f: A => Stream[B]): Stream[B] = foldRight (empty[B]) ((a, b) => f(a).append(b))

  def find (p: A => Boolean): Option[A] = filter(p).headOption

  def zipWith [B, C] (s: Stream[B]) (f: (A, B) => C): Stream[C] = unfold ((this, s)) {
    case (Cons(h1, t1), Cons(h2, t2)) => Some(f(h1(), h2()), (t1(), t2()))
    case (Empty, _) => None
    case (_, Empty) => None
  }

  def zipAll [B] (s: Stream[B]): Stream[(Option[A], Option[B])] = unfold ((this, s)) {
    case (Cons(h1, t1), Cons(h2, t2)) => Some((Some(h1()), Some(h2())), (t1(), t2()))
    case (Cons(h1, t1), Empty) => Some((Some(h1()), None), (t1(), empty))
    case (Empty, Cons(h2, t2)) => Some((None, Some(h2())), (empty, t2()))
    case (Empty, Empty) => None
  }

  def startsWith [B >: A] (s: Stream[B]): Boolean =
    zipAll(s) takeWhile (_._2.isDefined) forAll {
      case (a, b) => a == b
    }

  def tails: Stream[Stream[A]] = unfold (this) {
    case Empty => None
    case s => Some(s, s drop 1)
  } append Stream(empty)
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

  def constant [A] (a: A): Stream[A] = unfold (a) (_ => Some(a, a))

  def from (n: Int): Stream[Int] = unfold (n) (s => Some((s, s + 1)))

  def unfold [A, S] (z: S) (f: S => Option[(A, S)]): Stream[A] = {
    f(z) match {
      case Some((head, nextState)) => cons(head, unfold(nextState)(f))
      case None => empty
    }
  }
}

