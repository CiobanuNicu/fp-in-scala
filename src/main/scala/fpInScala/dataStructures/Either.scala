package fpInScala.dataStructures

sealed trait Either [+E, +A] {
  def map [B] (f: A => B): Either[E, B] = flatMap(a => Right(f(a)))

  // When mapping over the right side, we must promote the left type parameter to some supertype,
  // to satisfy the +E variance annotation.
  def flatMap [EE >: E, B] (f: A => Either[EE, B]): Either[EE, B] = this match {
    case Right(a) => f(a)
    case Left(e) => Left(e)
  }

  // Similarly for orElse
  def orElse [EE >: E, B >: A] (b: => Either[EE, B]): Either[EE, B] = this match {
    case Left(_) => b
    case _ => this
  }

  def map2 [EE >: E, B, C] (b: Either[EE, B]) (f: (A, B) => C): Either[EE, C] = for { x <- this; y <- b } yield f(x, y)
}

case class Left [+E] (value: E) extends Either [E, Nothing]
case class Right [+A] (value: A) extends Either [Nothing, A]

object Either {
  def mean (xs: IndexedSeq[Double]): Either[String, Double] =
    if (xs.isEmpty) {
      Left("mean of empty list!")
    } else {
      Right(xs.sum / xs.length)
    }

  def safeDiv (x: Int, y: Int): Either[Exception, Int] = Try(x / y)

  def Try [A] (a: => A): Either[Exception, A] =
    try Right(a)
    catch { case e: Exception => Left(e) }

  def sequence [E, A] (es: List[Either[E, A]]): Either[E, List[A]] = traverse(es)(x => x)

  def traverse [E, A, B] (as: List[A]) (f: A => Either[E, B]): Either[E, List[B]] = List.foldRight(as, Right(Nil): Either[E, List[B]]) {
    (a, b) => f(a).map2(b)(Cons(_, _))
  }
}
