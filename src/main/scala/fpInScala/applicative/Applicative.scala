package fpInScala.applicative

import fpInScala.monads.Functor
import scala.language.higherKinds

trait Applicative [F[_]] extends Functor[F] {
  // primitive combinators
  def map2 [A, B, C] (fa: F[A], fb: F[B]) (f: (A, B) => C): F[C]
  def unit [A] (a: => A): F[A]

  // derived combinators
  def apply [A, B] (fab: F[A => B]) (fa: F[A]): F[B] = map2(fab, fa) (_(_))
  def map [A, B] (fa: F[A]) (f: A => B): F[B] = map2(fa, unit(()))((a, _) => f(a))
  def traverse [A, B] (as: List[A]) (f: A => F[B]): F[List[B]] =
    as.foldRight(unit(List[B]()))((a, fbs) => map2(f(a), fbs)(_ :: _))

  def sequence [A] (fas: List[F[A]]): F[List[A]] = traverse(fas)(a => a)
  def replicateM [A] (n: Int, fa: F[A]): F[List[A]] = sequence(List.fill(n)(fa))
  def product [A, B] (fa: F[A], fb: F[B]): F[(A, B)] = map2(fa, fb)((_, _))

  def map3 [A, B, C, D] (fa: F[A],
                         fb: F[B],
                         fc: F[C]) (f: (A, B, C) => D): F[D] = apply(apply(apply(unit(f.curried))(fa))(fb))(fc)

  def map4 [A, B, C, D, E] (fa: F[A],
                            fb: F[B],
                            fc: F[C],
                            fd: F[D]) (f: (A, B, C, D) => E): F[E] = apply(apply(apply(apply(unit(f.curried))(fa))(fb))(fc))(fd)
}

trait ApplicativeWithUnitAndApplyAsPrimitives [F[_]] extends Functor[F] {
  def apply [A, B] (fab: F[A => B]) (fa: F[A]): F[B] = map2(fab, fa) (_(_))

  def unit [A] (a: => A): F[A]

  def map [A, B] (fa: F[A]) (f: A => B): F[B] = apply(unit(f))(fa)
  def map2 [A, B, C] (fa: F[A], fb: F[B]) (f: (A, B) => C): F[C] = apply(map(fa)(f.curried))(fb)
}

object Applicative {
  // The idea behind this Applicative is to combine corresponding elements via zipping
  val streamApplicative = new Applicative[Stream] {
    // The infinite, constant stream
    def unit [A] (a: => A): Stream[A] = Stream.continually(a)

    // Combine elements pointwise
    def map2 [A, B, C] (fa: Stream[A], fb: Stream[B]) (f: (A, B) => C): Stream[C] = fa zip fb map f.tupled

    // For streams, sequence zips the list of streams together into a stream
    // of lists of each streams next value, until any one of the streams completes
    // It has the effect of transposing the list
    override def sequence [A] (a: List[Stream[A]]): Stream[List[A]] = traverse(a)(identity)
  }
}
