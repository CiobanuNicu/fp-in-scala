package fpInScala.exercises.chapter4

import fpInScala.dataStructures._

object Exercise3 {
  // Write a generic function map2 that combines two Option values using a binary function. If either Option value is
  // None, then the return value is too. Here is its signature:

  // We'll defer to the implementation on Option:
  def map2 [A, B, C] (a: Option[A], b: Option[B]) (f: (A, B) => C): Option[C] = Option.map2(a, b)(f)

  // You could also do it with two flatMaps and an explicit call to Some at the end:
  def alternateMap2A [A, B, C] (a: Option[A], b: Option[B]) (f: (A, B) => C): Option[C] = a flatMap(x => b.flatMap(y => Some(f(x, y))))

  // You could also do it with a for expression, which de-sugars into flatMap, map, withFilter, foreach as needed:
  def alternateMap2B [A, B, C] (a: Option[A], b: Option[B]) (f: (A, B) => C): Option[C] = for { x <- a; y <- b } yield f(x, y)
}
