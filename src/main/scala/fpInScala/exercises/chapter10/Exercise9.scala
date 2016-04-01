package fpInScala.exercises.chapter10

import fpInScala.monoids.Monoid

object Exercise9 {
  def ordered (ints: IndexedSeq[Int]): Boolean = {
    val orderedMonoid = new Monoid[(Option[Int], Boolean)] {
      def op (a1: (Option[Int], Boolean), a2: (Option[Int], Boolean)): (Option[Int], Boolean) = (a1, a2) match {
        case ((None, b1), (o2, b2)) => (o2, b1 && b2)
        case ((o1, b1), (None, b2)) => (o1, b1 && b2)
        case ((Some(i1), b1), (Some(i2), b2)) => (Some(i2), b1 && b2 && (i1 <= i2))
      }

      val zero: (Option[Int], Boolean) = (None, true)
    }

    Monoid.foldMap[Int, (Option[Int], Boolean)](ints.toList, orderedMonoid)(x => (Some(x), true))._2
  }
}
