package fpInScala.exercises

object Exercise2Point1 {
  // Write a recursive function to get the nth Fibonacci number (http://mng.bz/C29s).
  // The first two Fibonacci numbers are 0 and 1. The nth number is always the sum of the previous two —-
  // the sequence begins 0, 1, 1, 2, 3, 5. Your definition should use a local tail-recursive function.
  def fib (n: Int): Int = {
    @annotation.tailrec
    def go (a: Int, b: Int, n: Int): Int = if (n > 0) go (b, a + b, n - 1) else a
    go(0, 1, n)
  }
}
