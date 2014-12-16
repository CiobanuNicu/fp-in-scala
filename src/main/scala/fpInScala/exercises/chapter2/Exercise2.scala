package fpInScala.exercises.chapter2

object Exercise2 {
  // Implement isSorted, which checks whether an Array[A] is sorted according to a given comparison function:
  def isSorted [A] (as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    as.length <= 1 || as.sliding(2).forall(subAs => ordered(subAs(0), subAs(1)))
  }
}
