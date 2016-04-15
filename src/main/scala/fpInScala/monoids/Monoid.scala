package fpInScala.monoids

trait Monoid [A] {
  def op (a1: A, a2: A): A
  def zero: A
}

object Monoid {
  // Operations

  // We can write a general function concatenate that folds a list with a monoid:
  def concatenate [A] (as: List[A], m: Monoid[A]): A = as.foldLeft(m.zero)(m.op)

  // But what if our list has an element type that doesn't have a Monoid instance?
  // Well, we can always map over the list to turn it into a type that does:
  def foldMap [A, B] (as: List[A], m: Monoid[B]) (f: A => B): B = as.foldLeft(m.zero)((b, a) => m.op(b, f(a)))

  def foldMapV [A,B] (v: IndexedSeq[A], m: Monoid[B]) (f: A => B): B = {
    if (v.length > 1) {
      val (l, r) = v.splitAt(v.length / 2)
      m.op(foldMapV(l, m)(f), foldMapV(r, m)(f))
    } else if (v.length == 1) {
      f(v(0))
    } else {
      m.zero
    }
  }

  import fpInScala.dataStructures.parallel.Nonblocking._
  import fpInScala.dataStructures.parallel.Nonblocking.Par._

  def par [A] (m: Monoid[A]): Monoid[Par[A]] = new Monoid[Par[A]] {
    def op (a1: Par[A], a2: Par[A]): Par[A] = Par.map2(a1, a2)(m.op)
    val zero: Par[A] = Par.unit(m.zero)
  }

  def parFoldMap [A, B] (v: IndexedSeq[A], m: Monoid[B]) (f: A => B): Par[B] = Par.parMap(v)(f).flatMap {
    bs => foldMapV(bs, par(m))(b => Par.lazyUnit(b))
  }

  def foldLeft [A, B] (as: List[A]) (z: B) (f: (B, A) => B): B = foldMap(as, dual(endoMonoid[B]))(a => b => f(b, a))(z)

  def foldRight [A, B] (as: List[A]) (z: B) (f: (A, B) => B): B = foldMap(as, endoMonoid[B])(f.curried)(z)

  // Instances

  val stringMonoid = new Monoid[String] {
    def op (a1: String, a2: String): String = a1 + a2
    val zero: String = ""
  }

  val intAddition: Monoid[Int] = new Monoid[Int] {
    def op (a1: Int, a2: Int): Int = a1 + a2
    val zero: Int = 0
  }

  val intMultiplication: Monoid[Int] = new Monoid[Int] {
    def op (a1: Int, a2: Int): Int = a1 * a2
    val zero: Int = 1
  }

  val booleanOr: Monoid[Boolean] = new Monoid[Boolean] {
    def op (a1: Boolean, a2: Boolean): Boolean = a1 || a2
    val zero: Boolean = false
  }

  val booleanAnd: Monoid[Boolean] = new Monoid[Boolean] {
    def op (a1: Boolean, a2: Boolean): Boolean = a1 && a2
    val zero: Boolean = true
  }

  def listMonoid [A] = new Monoid[List[A]] {
    def op (a1: List[A], a2: List[A]): List[A] = a1 ++ a2
    val zero: List[A] = Nil
  }

  def dual [A] (m: Monoid[A]): Monoid[A] = new Monoid[A] {
    def op (a1: A, a2: A): A = m.op(a2, a1)
    val zero: A = m.zero
  }

  def optionMonoid [A]: Monoid[Option[A]] = new Monoid[Option[A]] {
    def op (a1: Option[A], a2: Option[A]): Option[A] = a1 orElse a2
    val zero: Option[A] = None
  }

  def firstOptionMonoid [A]: Monoid[Option[A]] = optionMonoid
  def lastOptionMonoid [A]: Monoid[Option[A]] = dual(firstOptionMonoid)

  def endoMonoid [A]: Monoid[A => A] = new Monoid[A => A] {
    def op (a1: A => A, a2: A => A): (A) => A = a1 compose a2
    val zero: A => A = identity
  }

  def productMonoid [A, B] (A: Monoid[A], B: Monoid[B]): Monoid[(A, B)] = new Monoid[(A, B)] {
    def op (a1: (A, B), a2: (A, B)): (A, B) = (A.op(a1._1, a2._1), B.op(a1._2, a2._2))

    val zero: (A, B) = (A.zero, B.zero)
  }

  def mapMergeMonoid [K, V] (V: Monoid[V]): Monoid[Map[K, V]] = new Monoid[Map[K, V]] {
    def op (a: Map[K, V], b: Map[K, V]) =
      (a.keySet ++ b.keySet).foldLeft(zero) {
        (acc, k) => acc.updated(k, V.op(a.getOrElse(k, V.zero), b.getOrElse(k, V.zero)))
      }

    def zero = Map[K, V]()
  }
}
