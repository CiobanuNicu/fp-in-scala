package fpInScala.exercises.chapter10

import fpInScala.monoids.Monoid

object Exercise9 {
  def ordered (ints: IndexedSeq[Int]): Boolean = {
    val orderedMonoid = new Monoid[Option[(Int, Int, Boolean)]] {
      def op (o1: Option[(Int, Int, Boolean)], o2: Option[(Int, Int, Boolean)]) =
        (o1, o2) match {
          case (Some((x1, y1, p)), Some((x2, y2, q))) => Some((x1 min x2, y1 max y2, p && q && y1 <= x2))
          case (x, None) => x
          case (None, x) => x
        }

      val zero = None
    }

    Monoid.foldMapV(ints, orderedMonoid)(i => Some((i, i, true))).forall(_._3)
  }
}
