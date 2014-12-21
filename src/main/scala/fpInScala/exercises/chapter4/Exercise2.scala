package fpInScala.exercises.chapter4

import fpInScala.dataStructures._

object Exercise2 {
  // Implement the variance function in terms of flatMap. If the mean of a sequence is m, the variance is the mean of
  // math.pow(x - m, 2) for each element x in the sequence.
  def variance (xs: Seq[Double]): Option[Double] = mean(xs) flatMap (m => mean(xs.map( x => math.pow(x - m, 2))))

  def mean (xs: Seq[Double]): Option[Double] = if (xs.length > 0) Some(xs.sum / xs.length) else None
}
