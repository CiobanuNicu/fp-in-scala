package fpInScala.dataStructures

sealed trait Option [+A] {
  def map [B] (f: A => B): Option[B] = this match {
    case Some(x) => Some(f(x))
    case None => None
  }

  def flatMap [B] (f: A => Option[B]): Option[B] = map(f) getOrElse None

  def getOrElse [B >: A] (default: => B): B = this match {
    case Some(x) => x
    case None => default
  }
}
case class Some [+A] (get: A) extends Option[A]
case object None extends Option[Nothing]
