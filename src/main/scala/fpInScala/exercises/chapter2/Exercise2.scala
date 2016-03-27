package fpInScala.exercises.chapter2

object Exercise2 {
  // Implement isSorted, which checks whether an Array[A] is sorted according to a given comparison function:
  def isSorted [A] (as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    @annotation.tailrec
    def go (xs: Array[A], idx: Int): Boolean = {
      ordered(xs(idx - 1), xs(idx)) && {
        val nextIdx = idx + 1
        nextIdx == xs.length || go(xs, nextIdx)
      }
    }
    as.length <= 1 || go(as, 1)
  }
}
