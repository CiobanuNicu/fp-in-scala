package fpInScala.parsing.implementations

import fpInScala.parsing.Parsers
import scala.language.implicitConversions
import scala.util.matching.Regex

class BookParser [+A]

object BookParser extends Parsers[BookParser] {
  type Parser[+A] = Location => Result[A]

  trait Result[+A]
  case class Success[+A] (get: A, charsConsumed: Int) extends Result[A]
  case class Failure (get: ParseError) extends Result[Nothing]

  def run[A] (p: BookParser[A])(input: String): Either[BookParser.ParseError, A] = ???

  // Runs a parser, then uses its result to select a second parser to run in sequence
  def flatMap[A, B] (p: BookParser[A])(f: (A) => BookParser[B]): BookParser[B] = ???

  // Chooses between two parsers, first attempting p1, and then p2 if p1 fails
  def or[A] (s1: BookParser[A], s2: => BookParser[A]): BookParser[A] = ???

  // Recognizes and returns a single String
  implicit def string (s: String): BookParser[String] = ???

  def errorMessage (e: BookParser.ParseError): String = ???

  def scope[A] (msg: String)(p: BookParser[A]): BookParser[A] = ???

  def errorLocation (e: BookParser.ParseError): BookParser.Location = ???

  // Recognizes a regular expression s
  implicit def regex (r: Regex): BookParser[String] = ???

  // Returns the portion of input inspected by p if successful
  def slice[A] (p: BookParser[A]): BookParser[String] = ???

  def label[A] (msg: String)(p: BookParser[A]): BookParser[A] = ???

  def attempt[A] (p: BookParser[A]): BookParser[A] = ???
}
