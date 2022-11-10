package services

import persistence.RedisDataStore
import play.api.mvc.ControllerComponents
import util.UrlUtils
import javax.inject.{Inject, Singleton}


/**
 * Shorten the original url
 * @param store
 * @param keyService
 */
@Singleton
class UrlShortService @Inject()(
                                 store: RedisDataStore,
                                 keyService: KeyService
                               ){

  val urlUtils = new UrlUtils()

  /**
   * If the url already shorten return the key
   * Otherwise create new random key and store it
   * @param url
   * @return
   */
  def shortenUrl(url: String): Option[String] = {
    store.get(url) match {
      case Some(str) => {
        Some(str)
      }
      case None => {
        val randomKey = keyService.createKey().get

        store.save(randomKey, url)
        store.save(url, randomKey)

        Some(randomKey)
      }
    }
  }
}
