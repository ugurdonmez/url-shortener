package services

import javax.inject.{Inject, Singleton}
import scala.util.Random

/**
 * Create random seven length key
 */
@Singleton
class KeyService {

  /**
   * Generate random string
   * @return string
   */
  def createKey(): Option[String] = {
    Some(Random.alphanumeric.take(7).mkString)
  }
}
