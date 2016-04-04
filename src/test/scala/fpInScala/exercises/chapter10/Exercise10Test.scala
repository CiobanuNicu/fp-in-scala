package fpInScala.exercises.chapter10

import fpInScala.monoids.{Part, Stub, WC}
import org.scalacheck.{Arbitrary, Gen}

class Exercise10Test extends Exercise4Test {

  property("associativity for WC Monoid") = associativity(WC.wcMonoid)

  property("identity for WC Monoid") = identity(WC.wcMonoid)

  implicit def arbWC: Arbitrary[WC] = Arbitrary {
    val genStub: Gen[Stub] = {
      Gen.alphaStr.map(Stub)
    }

    def genPart (sz: Int): Gen[Part] = for {
      lStub <- Gen.alphaStr
      rStub <- Gen.alphaStr
    } yield Part(lStub, sz, rStub)

    Gen.sized(sz => Gen.frequency(
      (1, genStub),
      (3, genPart(sz))
    ))
  }
}
