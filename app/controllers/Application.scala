package controllers

import javax.inject._

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

@Singleton
class Application @Inject() extends Controller {

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
    val d = domainForm.bindFromRequest()
    val u = urlForm.bindFromRequest()
    (d.hasErrors, u.hasErrors) match {
      case (false, _) =>
        Ok(views.html.withDomain(d.value.get.domain))
      case (_, false) =>
        Ok(views.html.withUrl(u.value.get.url))
      case _ =>
        Ok(views.html.default())
    }
  }

}
