package filters

import javax.inject.Inject

import akka.stream.Materializer
import play.api.Logger
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AccessLoggingFilter @Inject() (implicit val mat: Materializer) extends Filter {

  private val accessLogger = Logger("access")

  def apply(next: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    val resultFuture = next(request)

    resultFuture.foreach(result => {
      val message = s"request-id=${request.id} remote-address=${request.remoteAddress}" +
        s" user-agent=${request.headers.get("User-Agent").getOrElse("")}" +
        s" host=${request.host} uri=${request.uri} method=${request.method}" +
        s" status=${result.header.status}"
      accessLogger.info(message)
    })

    resultFuture
  }
}
