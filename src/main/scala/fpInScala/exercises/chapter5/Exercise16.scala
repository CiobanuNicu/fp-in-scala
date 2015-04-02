package fpInScala.exercises.chapter5

object Exercise16 {
  // Hard: Generalize tails to the function scanRight, which is like a foldRight that returns a stream of the
  // intermediate results. For example:

  // scala> Stream(1,2,3).scanRight(0)(_ + _).toList
  // res0: List[Int] = List(6,5,3,0)

  // This example should be equivalent to the expression List(1+2+3+0, 2+3+0, 3+0, 0). Your function should reuse
  // intermediate results so that traversing a Stream with n elements always takes time linear in n. Can it be
  // implemented using unfold? How, or why not? Could it be implemented using another function we've written?
}
