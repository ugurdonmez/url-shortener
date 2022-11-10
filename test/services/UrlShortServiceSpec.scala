package services

import org.scalatest._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import org.mockito.Mockito._

import persistence.RedisDataStore

class UrlShortServiceSpec  extends PlaySpec with MockitoSugar {

  "Url Short Service" must {
    val mockRedisStore = mock[RedisDataStore]
    val mockKeyService = mock[KeyService]

    val urlShortService = new UrlShortService(mockRedisStore, mockKeyService)

    "return same shorten url when same long url called" in {
      when(mockRedisStore.get("http://google.com")).thenReturn(Some("1234567"))

      urlShortService.shortenUrl("http://google.com") mustBe Some("1234567")
      urlShortService.shortenUrl("http://google.com") mustBe Some("1234567")
    }

    "create new short url when new long url called" in {
      when(mockRedisStore.get("http://google.com")).thenReturn(Option.empty)
      when(mockKeyService.createKey("http://google.com")).thenReturn(Some("1234567"))
      when(mockRedisStore.save("http://google.com", "1234567")).thenReturn(true)
      when(mockRedisStore.save("1234567", "http://google.com")).thenReturn(true)

      urlShortService.shortenUrl("http://google.com") mustBe Some("1234567")
    }
  }

}
