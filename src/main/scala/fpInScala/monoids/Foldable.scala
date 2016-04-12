package fpInScala.monoids

import fpInScala.dataStructures.tree.{Tree, Leaf, Branch}

import scala.language.higherKinds

trait Foldable[F[_]] {
  import Monoid._

  def foldRight [A, B] (as: F[A]) (z: B) (f: (A, B) => B): B =
    foldMap(as)(f.curried)(endoMonoid[B])(z)

  def foldLeft [A, B] (as: F[A]) (z: B) (f: (B, A) => B): B =
    foldMap(as)(a => (b: B) => f(b, a))(dual(endoMonoid[B]))(z)

  def foldMap [A, B] (as: F[A]) (f: A => B) (mb: Monoid[B]): B =
    foldRight(as)(mb.zero)((a, b) => mb.op(f(a), b))

  def concatenate [A] (as: F[A]) (m: Monoid[A]): A =
    foldLeft(as)(m.zero)(m.op)

  def toList [A] (as: F[A]): List[A] = foldRight(as)(Nil: List[A])(_ :: _)
}

object ListFoldable extends Foldable[List] {
  override def foldRight [A, B] (as: List[A]) (z: B) (f: (A, B) => B): B =
    as.foldRight(z)(f)

  override def foldLeft [A, B] (as: List[A]) (z: B) (f: (B, A) => B): B =
    as.foldLeft(z)(f)

  override def foldMap [A, B] (as: List[A]) (f: (A) => B) (mb: Monoid[B]): B =
    as.foldLeft(mb.zero)((b, a) => mb.op(b, f(a)))
}

object IndexedSeqFoldable extends Foldable[IndexedSeq] {
  import Monoid._

  override def foldRight [A, B] (as: IndexedSeq[A]) (z: B) (f: (A, B) => B): B =
    as.foldRight(z)(f)

  override def foldLeft [A, B] (as: IndexedSeq[A]) (z: B) (f: (B, A) => B): B =
    as.foldLeft(z)(f)

  override def foldMap [A, B] (as: IndexedSeq[A]) (f: (A) => B) (mb: Monoid[B]): B =
    foldMapV(as, mb)(f)
}

object StreamFoldable extends Foldable[Stream] {
  override def foldRight [A, B] (as: Stream[A]) (z: B) (f: (A, B) => B): B =
    as.foldRight(z)(f)

  override def foldLeft [A, B] (as: Stream[A]) (z: B) (f: (B, A) => B): B =
    as.foldLeft(z)(f)
}

object TreeFoldable extends Foldable[Tree] {
  import Tree._
  override def foldMap [A, B] (as: Tree[A]) (f: (A) => B) (mb: Monoid[B]): B = fold(as)(f)(mb.op)

  override def foldLeft [A, B] (as: Tree[A]) (z: B) (f: (B, A) => B): B = as match {
    case Leaf(x) => f(z, x)
    case Branch(l, r) => foldLeft(r)(foldLeft(l)(z)(f))(f)
  }

  override def foldRight [A, B] (as: Tree[A]) (z: B) (f: (A, B) => B): B = as match {
    case Leaf(x) => f(x, z)
    case Branch(l, r) => foldRight(l)(foldRight(l)(z)(f))(f)
  }
}

object OptionFoldable extends Foldable[Option] {
  override def foldMap [A, B] (as: Option[A]) (f: (A) => B) (mb: Monoid[B]): B = as match {
    case None => mb.zero
    case Some(x) => f(x)
  }

  override def foldLeft [A, B] (as: Option[A]) (z: B) (f: (B, A) => B): B = as match {
    case None => z
    case Some(x) => f(z, x)
  }

  override def foldRight [A, B] (as: Option[A]) (z: B) (f: (A, B) => B): B = as match {
    case None => z
    case Some(x) => f(x, z)
  }
}
