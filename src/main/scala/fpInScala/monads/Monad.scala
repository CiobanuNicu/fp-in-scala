package fpInScala.monads

import fpInScala.dataStructures.parallel.Nonblocking.Par
import fpInScala.testing.Gen

import scala.language.higherKinds

trait Monad[F[_]] extends Functor[F] {
  def unit [A] (a: => A): F[A]
  def flatMap [A, B] (ma: F[A]) (f: A => F[B]): F[B]

  def map [A, B] (ma: F[A]) (f: A => B): F[B] =
    flatMap(ma)(a => unit(f(a)))

  def map2 [A, B, C] (ma: F[A], fb: F[B])(f: (A, B) => C): F[C] =
    flatMap(ma)(a => map(fb)(b => f(a, b)))
}

object Monad {
  val genMonad = new Monad[Gen] {
    def unit [A] (a: => A): Gen[A] = Gen.unit(a)
    def flatMap [A,B] (ma: Gen[A]) (f: A => Gen[B]): Gen[B] = ma flatMap f
  }

  val parMonad = new Monad[Par] {
    def unit [A] (a: => A): Par[A] = Par.unit(a)
    def flatMap [A, B] (ma: Par[A]) (f: (A) => Par[B]): Par[B] = Par.flatMap(ma)(f)
  }

  // TODO - Implement Monad[Parser] when Parser is redone
  // val parserMonad = ???
}
