package fpInScala.parsing

import scala.language.{implicitConversions, higherKinds}

trait Parsers [ParseError, Parser[+_]] { self =>

  def run [A] (p: Parser[A]) (input: String): Either[ParseError, A]
  def char (c: Char): Parser[Char]

  def many [A] (p: Parser[A]): Parser[List[A]]
  def map [A, B] (p: Parser[A]) (f: A => B): Parser[B]

  def or [A] (s1: Parser[A], s2: Parser[A]): Parser[A]
  implicit def string (s: String): Parser[String]
  implicit def operators [A] (p: Parser[A]): ParserOps[A] = ParserOps[A](p)
  implicit def asStringParser [A] (a: A) (implicit f: A => Parser[String]): ParserOps[String] = ParserOps(f(a))

  case class ParserOps [A] (p: Parser[A]) {
    def | [B >: A] (p2: Parser[B]): Parser[B] = self.or(p, p2)
    def or [B >: A] (p2: Parser[B]): Parser[B] = self.or(p, p2)
    def many: Parser[List[A]] = self.many(p)
    def map [B] (f: A => B): Parser[B] = self.map(p)(f)
  }
}
