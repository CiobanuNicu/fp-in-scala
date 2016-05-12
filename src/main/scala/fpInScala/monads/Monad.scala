package fpInScala.monads

import fpInScala.dataStructures.parallel.Nonblocking.Par
import fpInScala.dataStructures.state.State
import fpInScala.testing.Gen

import scala.language.{higherKinds, reflectiveCalls}

trait Monad[F[_]] extends Functor[F] {
  def unit [A] (a: => A): F[A]
  def flatMap [A, B] (ma: F[A]) (f: A => F[B]): F[B]

  def map [A, B] (ma: F[A]) (f: A => B): F[B] =
    flatMap(ma)(a => unit(f(a)))

  def map2 [A, B, C] (ma: F[A], fb: F[B])(f: (A, B) => C): F[C] =
    flatMap(ma)(a => map(fb)(b => f(a, b)))

  def sequence [A] (lma: List[F[A]]): F[List[A]] = lma.foldRight (unit(List[A]())) ((a, b) => map2(a, b)(_ :: _))

  def traverse [A, B] (la: List[A]) (f: A => F[B]): F[List[B]] = la.foldRight (unit(List[B]())) ((a, b) => map2(f(a), b)(_ :: _))

  def replicateM [A] (n: Int, ma: F[A]): F[List[A]] = sequence(List.fill(n)(ma))

  def product [A, B] (ma: F[A], mb: F[B]): F[(A, B)] = map2(ma, mb) ((_, _))

  def filterM [A] (ms: List[A]) (f: A => F[Boolean]): F[List[A]] = ms match {
    case Nil => unit(Nil)
    case h :: t => flatMap(f(h))(b =>
      if (b) map(filterM(t)(f))(h :: _)
      else filterM(t)(f)
    )
  }

  def compose [A, B, C] (f: A => F[B], g: B => F[C]): A => F[C] = (a: A) => flatMap(f(a))(g)

  def flatMapInTermsOfCompose [A, B] (ma: F[A]) (f: A => F[B]): F[B] = compose((_:Unit) => ma, f)(())

  def join [A] (mma: F[F[A]]): F[A] = flatMap(mma)(ma => ma)

  def flatMapInTermsOfJoinAndMap [A, B] (ma: F[A]) (f: A => F[B]): F[B] = join(map(ma)(f))

  def composeInTermsOfJoinAndMap [A, B, C] (f: A => F[B], g: B => F[C]): A => F[C] = a => join(map(f(a))(g))
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

  val optionMonad = new Monad[Option] {
    def unit [A] (a: => A): Option[A] = Option(a)
    def flatMap [A, B] (ma: Option[A]) (f: (A) => Option[B]): Option[B] = ma flatMap f
  }

  val streamMonad = new Monad[Stream] {
    def unit [A] (a: => A): Stream[A] = Stream(a)
    def flatMap[A, B] (ma: Stream[A]) (f: (A) => Stream[B]): Stream[B] = ma flatMap f
  }

  val listMonad = new Monad[List] {
    def unit [A] (a: => A): List[A] = List(a)
    def flatMap[A, B] (ma: List[A])(f: (A) => List[B]): List[B] = ma flatMap f
  }

  def stateMonad [S] = new Monad[({type F[X] = State[S, X]})#F] {
    def unit [A] (a: => A): State[S, A] = State.unit(a)
    def flatMap [A, B] (ma: State[S, A]) (f: (A) => State[S, B]): State[S, B] = ma flatMap f
  }

  val identityMonad = new Monad[Id] {
    def unit [A] (a: => A): Id[A] = Id(a)
    def flatMap [A, B] (ma: Id[A]) (f: (A) => Id[B]): Id[B] = ma flatMap f
  }
}
