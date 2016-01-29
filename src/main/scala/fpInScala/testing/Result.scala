package fpInScala.testing

import fpInScala.testing.Prop.{SuccessCount, FailedCase}

sealed trait Result {
  def isFalsified: Boolean
}

case object Passed extends Result {
  override def isFalsified = false
}

case class Falsified (failure: FailedCase, successes: SuccessCount) extends Result {
  override def isFalsified = true
}
