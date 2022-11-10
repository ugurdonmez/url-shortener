package persistence

import io.circe._
import scala.concurrent.Future

trait DataStore {
  def save(key: String, obj: String): Boolean

  def get(key: String): Option[String]
}
