package fpInScala.exercises.chapter6

import fpInScala.exercises.chapter6.Exercise11._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, ShouldMatchers}

class Exercise11Test extends FlatSpec with ShouldMatchers with TableDrivenPropertyChecks {

  // if the input Machine has 10 coins and 5 candies,
  // and a total of 4 candies are successfully bought, the output should be (14, 1).

  "A simulated candy machine" should "become unlocked if it was locked and has candy, when a coin is inserted" in {
    val m = Machine(locked = true, candies = 1, coins = 0)
    val sim = Exercise11.simulateMachine(List(Coin))

    val (_, nextMachine) = sim.run(m)
    nextMachine.locked should be (false)
    nextMachine.coins should be (1)
  }

  it should "become locked and dispense candy when it was unlocked and the knob is turned" in {
    val m = Machine(locked = false, candies = 1, coins = 1)
    val sim = Exercise11.simulateMachine(List(Turn))

    val (_, nextMachine) = sim.run(m)
    nextMachine.locked should be (true)
    nextMachine.candies should be (0)
  }

  it should "do nothing when it is locked and the knob is turned" in {
    val candiesAndCoins = Table(
      ("candies", "coins"),
      (        0,       0),
      (        1,       0),
      (        2,       1),
      (        3,       2),
      (        4,       3),
      (        5,       4),
      (        6,       5),
      (        7,       6),
      (        8,       7),
      (        9,       8),
      (       10,       9),
      (       11,      10)
    )

    forAll (candiesAndCoins) { (candies: Int, coins: Int) =>
      val m = Machine(locked = true, candies = candies, coins = coins)
      val sim = Exercise11.simulateMachine(List(Turn))

      val (_, nextMachine) = sim.run(m)
      nextMachine.locked should be (true)
      nextMachine.candies should be (m.candies)
      nextMachine.coins should be (m.coins)
    }
  }

  it should "do nothing when it is unlocked and a coin is inserted" in {
    val candiesAndCoins = Table(
      ("candies", "coins"),
      (        1,       2),
      (        2,       3),
      (        3,       4),
      (        4,       5),
      (        5,       6),
      (        6,       7),
      (        7,       8),
      (        8,       9),
      (        9,      10),
      (       10,      11),
      (       11,      12),
      (       12,      13)
    )

    forAll (candiesAndCoins) { (candies: Int, coins: Int) =>
      val m = Machine(locked = false, candies = candies, coins = coins)
      val sim = Exercise11.simulateMachine(List(Coin))

      val (_, nextMachine) = sim.run(m)
      nextMachine.locked should be (false)
      nextMachine.candies should be (candies)
      nextMachine.coins should be (coins)
    }
  }

  it should "ignore all inputs when it is out of candy" in {
    val candiesAndCoins = Table(
      ("locked", "candies", "coins"),
      (    true,         0,       2),
      (   false,         0,       3),
      (    true,         0,       4),
      (    true,         0,       5),
      (    true,         0,       6),
      (   false,         0,       7),
      (   false,         0,       8),
      (   false,         0,       9),
      (    true,         0,      10),
      (    true,         0,      11),
      (   false,         0,      12),
      (   false,         0,      13)
    )

    forAll (candiesAndCoins) { (locked: Boolean, candies: Int, coins: Int) =>
      val m = Machine(locked = locked, candies = candies, coins = coins)
      val sim = Exercise11.simulateMachine(List(Coin))

      val (_, nextMachine) = sim.run(m)
      nextMachine.locked should be (locked)
      nextMachine.candies should be (candies)
      nextMachine.coins should be (coins)
    }
  }
}
