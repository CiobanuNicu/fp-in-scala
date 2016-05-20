package fpInScala.applicative

import fpInScala.monads.Functor
import scala.language.higherKinds

trait Applicative [F[_]] extends Functor[F] {
  // primitive combinators
  def map2 [A, B, C] (fa: F[A], fb: F[B]) (f: (A, B) => C): F[C]
  def unit [A] (a: => A): F[A]

  // derived combinators
  def map [A, B] (fa: F[A]) (f: A => B): F[B] = map2(fa, unit(()))((a, _) => f(a))
  def traverse [A, B] (as: List[A]) (f: A => F[B]): F[List[B]] =
    as.foldRight(unit(List[B]()))((a, fbs) => map2(f(a), fbs)(_ :: _))

  def sequence [A] (fas: List[F[A]]): F[List[A]] = traverse(fas)(a => a)
  def replicateM [A] (n: Int, fa: F[A]): F[List[A]] = sequence(List.fill(n)(fa))
  def product [A, B] (fa: F[A], fb: F[B]): F[(A, B)] = map2(fa, fb)((_, _))
}

trait ApplicativeWithUnitAndApplyAsPrimitives [F[_]] extends Functor[F] {
  def apply [A, B] (fab: F[A => B]) (fa: F[A]): F[B] = map2(fab, fa) (_(_))

  def unit [A] (a: => A): F[A]

  def map [A, B] (fa: F[A]) (f: A => B): F[B]
  def map2 [A, B, C] (fa: F[A], fb: F[B]) (f: (A, B) => C): F[C]
}