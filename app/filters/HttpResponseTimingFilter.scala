package filters

import javax.inject.Inject

import akka.stream.Materializer
import metrics.{Instrumented, MetricsManager}
import nl.grons.metrics.scala.Timer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.mvc._

class HttpResponseTimingFilter @Inject() (implicit val mat: Materializer, override val metricsManager: MetricsManager)
  extends Filter with Instrumented {

  def apply(next: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    timer.timeFuture {
      next(request)
    }
  }

  private val timer: Timer = metrics.timer("time")

}
