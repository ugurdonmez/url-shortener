package util

import org.scalatestplus.play.PlaySpec

class UrlUtilsSpec  extends PlaySpec {

  var urlUtils = new UrlUtils()

  "URL Utils Tests" must {
    "be valid url" in {
      urlUtils.isValidUrl("http://www.ea.com") mustBe true
    }

    "be invalid url" in {
      urlUtils.isValidUrl("somestring") mustBe false
    }

    "be trim url:" in {
      urlUtils.trimUrl("url=www.google.com") mustBe "www.google.com"
      urlUtils.trimUrl("www.google.com") mustBe "www.google.com"
    }
  }
}
