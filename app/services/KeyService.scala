package services

import javax.inject.{Inject, Singleton}
import scala.util.Random

@Singleton
class KeyService {
  def createKey(url: String): Option[String] = {
    Some(Random.alphanumeric.take(7).mkString)
  }
}
