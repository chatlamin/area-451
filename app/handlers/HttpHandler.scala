package handlers

import javax.inject.Inject

import play.api.http._
import play.api.mvc.{Handler, RequestHeader}
import play.api.routing.Router

class HttpHandler @Inject() (router: Router,
                             errorHandler: HttpErrorHandler,
                             configuration: HttpConfiguration,
                             filters: HttpFilters)
  extends DefaultHttpRequestHandler(
    router,
    errorHandler,
    configuration,
    filters) {

  override def routeRequest(requestHeader: RequestHeader): Option[Handler] = {
    val normalizedRequestHeader = requestHeader.path match {
      case "" => requestHeader.copy(path = "/", uri = "/" + requestHeader.uri)
      case _ => requestHeader
    }
    super.routeRequest(normalizedRequestHeader)
  }

}
