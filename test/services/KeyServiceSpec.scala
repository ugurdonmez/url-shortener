package services

import org.scalatestplus.play.PlaySpec

class KeyServiceSpec extends PlaySpec {

  var keyService = new KeyService()

  "Key Service" must {
    "create random keys " in {
      keyService.createKey("ea.com").get.length mustBe 7
    }
  }

}
