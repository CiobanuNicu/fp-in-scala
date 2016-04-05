package fpInScala.monoids

sealed trait WC
case class Stub (chars: String) extends WC
case class Part (lStub: String, words: Int, rStub: String) extends WC

object WC {
  val wcMonoid: Monoid[WC] = new Monoid[WC] {
    def op (a1: WC, a2: WC): WC = (a1, a2) match {
      case (Stub(x), Stub(y)) => Stub(x + y)
      case (Stub(x), Part(l, w, r)) => Part(x + l, w, r)
      case (Part(l, w, r), Stub(y)) => Part(l, w, r + y)
      case (Part(l1, w1, r1), Part(l2, w2, r2)) => Part(l1, w1 + w2 + (if ((r1 + l2).isEmpty) 0 else 1), r2)
    }

    val zero: WC = Stub("")
  }

  def wordCount (s: String): Int = {
    def toWC (c: Char): WC = {
      if (c.isWhitespace) {
        Part("", 0, "")
      } else {
        Stub(c.toString)
      }
    }

    def count (st: String): Int = s.length min 1

    Monoid.foldMapV[Char, WC](s.toIndexedSeq, wcMonoid)(toWC) match {
      case Stub(word) => count(word)
      case Part(l, counted, r) => count(l) + counted + count(r)
    }
  }
}
