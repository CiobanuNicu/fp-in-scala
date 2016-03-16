package fpInScala.parsing.implementations

import fpInScala.parsing.Parsers
import scala.language.implicitConversions
import scala.util.matching.Regex

class BookParser [+A]

object BookParser extends Parsers[BookParser] {
  type Parser[+A] = Location => Result[A]

  trait Result[+A] {
    def uncommit: Result[A] = this match {
      case Failure(e, true) => Failure(e, isCommitted = false)
      case _ => this
    }

    def mapError (f: ParseError => ParseError): Result[A] = this match {
      case Failure(e, committed) => Failure(f(e), committed)
      case _ => this
    }
  }

  case class Success[+A] (get: A, charsConsumed: Int) extends Result[A]
  case class Failure (get: ParseError, isCommitted: Boolean) extends Result[Nothing]

  def run[A] (p: BookParser[A])(input: String): Either[BookParser.ParseError, A] = ???

  // Runs a parser, then uses its result to select a second parser to run in sequence
  def flatMap[A, B] (p: BookParser[A])(f: (A) => BookParser[B]): BookParser[B] = ???

  // Chooses between two parsers, first attempting p1, and then p2 if p1 fails
  def or [A] (x: Parser[A], y: => Parser[A]): Parser[A] =
    s => x(s) match {
      case Failure(e, false) => y(s)
      case r => r
    }

  // Recognizes and returns a single String
  implicit def string (s: String): Parser[String] =
    (loc: Location) =>
      if (loc.input.startsWith(s))
        Success(s, loc.offset)
      else
        Failure(loc.toError(s"Expected: $s"), isCommitted = false)

  def errorMessage (e: BookParser.ParseError): String = ???

  def scope[A] (msg: String) (p: Parser[A]): Parser[A] =
    (loc: Location) => p(loc).mapError(_.push(loc, msg))

  def errorLocation (e: BookParser.ParseError): BookParser.Location = ???

  // Recognizes a regular expression s
  implicit def regex (r: Regex): Parser[String] =
    (loc: Location) =>
      r.findPrefixOf(loc.input) match {
        case Some(parsed) => Success(parsed, parsed.length)
        case None => Failure(loc.toError(s"Expected $r"), isCommitted = false)
      }

  // Always succeeds with the value a
  def succeed [A] (a: A): Parser[A] = l => Success(a, 0)

  // Returns the portion of input inspected by p if successful
  def slice[A] (p: Parser[A]): Parser[String] =
    (loc: Location) => p(loc) match {
      case Success(_, n) => Success(loc.input.substring(loc.offset, loc.offset + n), n)
      case f@Failure(_, _) => f
    }

  def label[A] (msg: String)(p: BookParser[A]): BookParser[A] = ???

  def attempt[A] (p: Parser[A]): Parser[A] =
    l => p(l).uncommit
}
