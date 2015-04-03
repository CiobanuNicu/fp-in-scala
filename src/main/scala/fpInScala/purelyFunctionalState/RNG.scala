package fpInScala.purelyFunctionalState

// Here's one possible interface to a random number generator:

trait RNG {
  def nextInt: (Int, RNG)
}
