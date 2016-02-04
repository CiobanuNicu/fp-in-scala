package fpInScala.testing

case class SGen [A] (forSize: Int => Gen[A]) {
  def apply (n: Int): Gen[A] = forSize(n)

  def flatMap [B] (f: A => Gen[B]): SGen[B] =
    SGen(forSize andThen (_ flatMap f))
}

object SGen {
  def listOf [A] (g: Gen[A]): SGen[List[A]] = SGen {
    i => g.listOfN(Gen.unit(i))
  }

  def listOf1 [A] (g: Gen[A]): SGen[List[A]] = SGen {
    i => g.listOfN(Gen.unit(i max 1))
  }
}
