package fpInScala.exercises

object Exercise2Point5 {
  // Let's look at a final example, function composition, which feeds the output of one function to the input of another
  // function. Again, the implementation of this function is fully determined by its type signature.
  // Implement the higher-order function that composes two functions.
  def compose [A, B, C] (f: B => C, g: A => B): A => C = a => f(g(a))
}
