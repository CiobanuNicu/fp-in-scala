package fpInScala.parsing

import scala.language.higherKinds

trait JSON

object JSON {
  case object JNull extends JSON
  case class JNumber (get: Double) extends JSON
  case class JString (get: String) extends JSON
  case class JBool (get: Boolean) extends JSON
  case class JArray (get: IndexedSeq[JSON]) extends JSON
  case class JObject (get: Map[String, JSON]) extends JSON

  // Stub out the method that would contain the solution . . . I guess we can use Throwable for the ParseError type?
  def jsonParser [Parser[+_]] (P: Parsers[Throwable, Parser]): Parser[JSON] = ???
}
