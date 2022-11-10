package persistence

import io.circe._
import io.circe.parser._
import io.circe.syntax._
import com.redis._
import play.api.Configuration

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class RedisDataStore @Inject() (config: Configuration)  extends DataStore {

  val redisHost = config.get[String]("redis.host")
  val redisPort = config.get[Int]("redis.port")

  val redis = new RedisClient(redisHost, redisPort)

  def save(key: String, value: String): Boolean = {
    redis.set(key, value)
  }

  def get(key: String): Option[String] = {
    redis.get(key)
  }
}
