package persistance

import org.scalatestplus.play.PlaySpec
import persistence.RedisDataStore

class RedisDatastoreSpec extends PlaySpec {

  val dataStore = new RedisDataStore()

  "Redis DB " must {
    "store key value" in {
      dataStore.save("key", "value") mustBe true
    }

    "store should retrieve value" in {
      dataStore.get("key") mustBe Some("value")
    }
  }

}
