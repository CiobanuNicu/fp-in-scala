package fpInScala.exercises.chapter5

import fpInScala.dataStructures.stream._
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise4Test extends FlatSpec with ShouldMatchers {
  "Stream.forall" should "return true for an empty stream regardless of the predicate" in {
    Stream().forAll(x => true) should be (true)
    Stream().forAll(x => false) should be (true)
  }

  it should "return true when the predicate is satisfied by all elements of the stream" in {
    Stream(2, 4, 6, 8, 10).forAll(isEven) should be (true)
  }

  it should "return false when a single element in the stream does not satisfy the predicate" in {
    Stream(3, 5, 7, 9, 10, 11, 13, 15, 17).forAll(isOdd) should be (false)
  }

  it should "not evaluate the stream further after the first element that does not satisfy the predicate" in {
    val timeBomb = Stream(
      () => 2,
      () => 4,
      () => 5,
      () => { throw new Exception("This should not have been evaluated"); 6 }
    )

    timeBomb.forAll(isEvenThunk) should be (false)
  }

  private val isEven: Int => Boolean = x => x % 2 == 0
  private val isOdd: Int => Boolean = x => x % 2 == 1
  private val isEvenThunk: (() => Int) => Boolean = x => x() % 2 == 0
}
