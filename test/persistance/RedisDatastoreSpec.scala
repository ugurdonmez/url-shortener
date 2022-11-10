package persistance

import org.scalatest._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import org.mockito.Mockito._
import com.redis._
import persistence.RedisDataStore
import play.api.Configuration

class RedisDatastoreSpec extends PlaySpec with MockitoSugar {

  var mockConfig = mock[Configuration]
  when(mockConfig.get[String]("redis.host")).thenReturn("host")
  when(mockConfig.get[String]("redis.host")).thenReturn("port")

  var mockRedis = mock[RedisClient]
  when(mockRedis.set("key", "value")).thenReturn(true)
  when(mockRedis.get("key")).thenReturn(Some("value"))

  val dataStore = new RedisDataStore(mockConfig)

  "Redis DB " must {
    "store key value" in {
      dataStore.save("key", "value") mustBe true
    }

    "store should retrieve value" in {
      dataStore.get("key") mustBe Some("value")
    }
  }

}
