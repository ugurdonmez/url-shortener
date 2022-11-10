package services

import persistence.RedisDataStore
import javax.inject.{Inject, Singleton}

/**
 * Retrieve original url from short key
 * @param dataStore
 */
@Singleton
class UrlRedirectService @Inject()(
                                    dataStore: RedisDataStore,
                                  ){
  def getRedirectUrl(key: String): Option[String] = {
    dataStore.get(key)
  }
}
