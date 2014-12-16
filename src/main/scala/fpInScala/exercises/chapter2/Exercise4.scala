package fpInScala.exercises.chapter2

object Exercise4 {
  // Implement uncurry, which reverses the transformation of curry. Note that since => associates to the right,
  // A => (B => C) can be written as A => B => C.
  def uncurry [A, B, C] (f: A => B => C): (A, B) => C = (a, b) => f(a)(b)
}
