package fpInScala.applicative

import fpInScala.monads.Functor
import scala.language.higherKinds

// Furthermore, we can now make Monad[F] a subtype of Applicative[F] by providing the default implementation of map2
// in terms of flatMap. This tells us that all monads are applicative functors, and we don’t need to provide
// separate Applicative instances for all our data types that are already monads.
// TODO: Combine this Monad with fpInScala.monads.Monad at some point
trait Monad [F[_]] extends Functor[F] {
  def unit [A] (a: => A): F[A]

  // A minimal implementation of Monad must implement unit and override either flatMap or join and map
  def flatMap [A, B] (fa: F[A]) (f: A => F[B]): F[B] = join(map(fa)(f))

  def join [A] (ffa: F[F[A]]): F[A] = flatMap(ffa)(fa => fa)

  def compose [A, B, C] (f: A => F[B], g: B => F[C]): A => F[C] = a => flatMap(f(a))(g)

  def map [A, B] (fa: F[A]) (f: A => B): F[B] = flatMap(fa)((a: A) => unit(f(a)))

  def map2 [A, B, C] (fa: F[A], fb: F[B]) (f: (A, B) => C): F[C] = flatMap(fa)(a => map(fb)(b => f(a, b)))
}

object Monad {
  def eitherMonad [E]: Monad[({type f[x] = Either[E, x]})#f] = new Monad[({type f[x] = scala.Either[E, x]})#f] {
    def unit [A] (a: => A): Either[E, A] = Right(a)

    override def flatMap [A, B] (fa: Either[E, A]) (f: (A) => Either[E, B]): Either[E, B] = fa match {
      case Right(a) => f(a)
      case Left(e) => Left(e)
    }
  }
}
