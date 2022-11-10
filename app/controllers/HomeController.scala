package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api._
import play.api.mvc._
import services.{UrlRedirectService, UrlShortService}
import play.api.libs.concurrent.CustomExecutionContext

import scala.concurrent.{ExecutionContext, Future}
import com.google.inject.ImplementedBy
import persistence.RedisDataStore
import util.UrlUtils

@ImplementedBy(classOf[MyExecutionContextImpl])
trait MyExecutionContext extends ExecutionContext

class MyExecutionContextImpl @Inject() (system: ActorSystem)
  extends CustomExecutionContext(system, "my.executor")
    with MyExecutionContext


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
                                val controllerComponents: ControllerComponents,
                                urlShortService: UrlShortService,
                                urlRedirectService: UrlRedirectService,
                                myExecutionContext: MyExecutionContext
                              ) extends BaseController {

  var urlUtil = new UrlUtils()

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.main())
  }

  def lookup(key: String) = Action.async {
    Future {
      val shortUrl = urlRedirectService.getRedirectUrl(key)

      shortUrl match {
        case Some(url) => {
          Redirect(url)
        }
        case None => {
          NotFound("key is not found")
        }
      }

    }(myExecutionContext)
  }

  def create(url: String) = Action.async {
    val trimUrl = urlUtil.trimUrl(url)

    Future {
      if (!urlUtil.isValidUrl(trimUrl)) {
        BadRequest("URL is not valid")
      } else {
        val shortUrl = urlShortService.shortenUrl(trimUrl)
        Ok(shortUrl.get)
      }
    }(myExecutionContext)
  }
}
