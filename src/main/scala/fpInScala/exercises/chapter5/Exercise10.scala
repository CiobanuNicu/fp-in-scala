package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import fpInScala.dataStructures.stream.Stream._

object Exercise10 {
  // Write a function fibs that generates the infinite stream of Fibonacci numbers: 0, 1, 1, 2, 3, 5, 8, and so on.
  def fibs: Stream[Int] = {
    def go (a: Int, b: Int): Stream[Int] = { cons(a, go(b, a + b)) }
    go(0, 1)
  }
}
