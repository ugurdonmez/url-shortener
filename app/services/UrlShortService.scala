package services

import persistence.RedisDataStore
import play.api.mvc.ControllerComponents
import util.UrlUtils

import javax.inject.{Inject, Singleton}

@Singleton
class UrlShortService @Inject()(
                                 store: RedisDataStore,
                                 keyService: KeyService
                               ){

  val urlUtils = new UrlUtils()

  def shortenUrl(url: String): Option[String] = {
    store.get(url) match {
      case Some(str) => {
        Some(str)
      }
      case None => {
        val randomKey = keyService.createKey(url).get

        store.save(randomKey, url)
        store.save(url, randomKey)

        Some(randomKey)
      }
    }
  }
}
