package fpInScala.monads

case class Id [A] (value: A) {
  def map [B] (f: A => B): Id[B] = Id(f(value))
}
