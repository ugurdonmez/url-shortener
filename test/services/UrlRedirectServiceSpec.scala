package services

import org.scalatest._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import org.mockito.Mockito._
import persistence.RedisDataStore


class UrlRedirectServiceSpec extends PlaySpec with MockitoSugar {

  "Url Redirect Service " must {
    val mockRedisStore = mock[RedisDataStore]
    val urlRedirectService = new UrlRedirectService(mockRedisStore)

    "find correct url from short url " in {
      when(mockRedisStore.get("ABCDEF")).thenReturn(Some("http://ea.com"))
      urlRedirectService.getRedirectUrl("ABCDEF") mustBe Some("http://ea.com")
    }

    "return None if the short key is invalid" in {
      when(mockRedisStore.get("ABCDEF")).thenReturn(Option.empty)
      urlRedirectService.getRedirectUrl("ABCDEF") mustBe None
    }
  }
}
