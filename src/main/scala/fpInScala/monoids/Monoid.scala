package fpInScala.monoids

trait Monoid [A] {
  def op (a1: A, a2: A): A
  def zero: A
}

object Monoid {
  val stringMonoid = new Monoid[String] {
    def op (a1: String, a2: String): String = a1 + a2
    val zero: String = ""
  }

  def listMonoid [A] = new Monoid[List[A]] {
    def op (a1: List[A], a2: List[A]): List[A] = a1 ++ a2
    val zero: List[A] = Nil
  }
}
