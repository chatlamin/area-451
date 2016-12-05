package controllers

import javax.inject._

import play.api.Configuration
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

@Singleton
class Application @Inject() (configuration: Configuration) extends Controller {

  case class DomainFormModel(domain: String)
  case class UrlFormModel(url: String)

  val domainForm = Form(
    mapping(
      "domain" -> nonEmptyText
    )(DomainFormModel.apply)(DomainFormModel.unapply)
  )

  val urlForm = Form(
    mapping(
      "url" -> nonEmptyText
    )(UrlFormModel.apply)(UrlFormModel.unapply)
  )

  def index = Action { implicit request =>
    val styles = configuration.underlying.getString("app.styles")
    val d = domainForm.bindFromRequest()
    val u = urlForm.bindFromRequest()
    (d.hasErrors, u.hasErrors) match {
      case (false, _) =>
        UnavailableForLegalReasons(views.html.withDomain(d.value.get.domain, styles))
      case (_, false) =>
        UnavailableForLegalReasons(views.html.withUrl(u.value.get.url, styles))
      case _ =>
        UnavailableForLegalReasons(views.html.default(styles))
    }
  }

  private val UnavailableForLegalReasons = new Status(451)

}
