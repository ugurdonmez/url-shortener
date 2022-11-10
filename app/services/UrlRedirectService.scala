package services


import persistence.RedisDataStore

import javax.inject.{Inject, Singleton}

@Singleton
class UrlRedirectService @Inject()(
                                    dataStore: RedisDataStore,
                                  ){
  def getRedirectUrl(key: String): Option[String] = {
    dataStore.get(key)
  }
}
