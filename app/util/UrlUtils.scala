package util

import org.apache.commons.validator.routines.UrlValidator

/**
 * This util class is responsible for url operations
 */
class UrlUtils {

  /**
   * Check that the url is valid
   * @param url
   * @return
   */
  def isValidUrl(url: String): Boolean = {
    val validator = new UrlValidator(List("http", "https").toArray)
     validator.isValid(url)
  }

  /**
   * Remove parameters keyword from url
   * @param url
   * @return
   */
  def trimUrl(url: String): String = {
    url.split("url=").last
  }

}
