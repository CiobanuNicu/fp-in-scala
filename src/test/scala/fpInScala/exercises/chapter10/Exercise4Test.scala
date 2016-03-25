package fpInScala.exercises.chapter10

import fpInScala.monoids.Monoid
import org.scalacheck.{Prop, Arbitrary, Properties}
import org.scalacheck.Prop.forAll

class Exercise4Test extends Properties("Monoid") {

  def associativity [A : Arbitrary] (m: Monoid[A]): Prop = forAll { (x: A, y: A, z: A) =>
    m.op(m.op(x, y), z) == m.op(x, m.op(y, z))
  }

  // Is there a less repetitive / boilerplate-y way to do this???
  property("associativity for stringMonoid") = associativity(Monoid.stringMonoid)
  property("associativity for int addition monoid") = associativity(Monoid.intAddition)
  property("associativity for int multiplication monoid") = associativity(Monoid.intMultiplication)
  property("associativity for boolean or") = associativity(Monoid.booleanOr)
  property("associativity for boolean and") = associativity(Monoid.booleanAnd)

  def identity [A : Arbitrary] (m: Monoid[A]): Prop = forAll { (a: A) =>
    m.op(a, m.zero) == a && m.op(m.zero, a) == a
  }

  // Is there a less repetitive / boilerplate-y way to do this???
  property("identity for stringMonoid") = identity(Monoid.stringMonoid)
  property("identity for int addition monoid") = identity(Monoid.intAddition)
  property("identity for int multiplication monoid") = identity(Monoid.intMultiplication)
  property("identity for boolean or") = identity(Monoid.booleanOr)
  property("identity for boolean and") = identity(Monoid.booleanAnd)
}
