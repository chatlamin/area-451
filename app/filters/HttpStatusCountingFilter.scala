package filters

import javax.inject.Inject

import akka.stream.Materializer
import metrics.{Instrumented, MetricsManager}
import nl.grons.metrics.scala.Counter

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.mvc._

import scala.collection.mutable

class HttpStatusCountingFilter @Inject() (implicit val mat: Materializer, override val metricsManager: MetricsManager) extends Filter with Instrumented {

  private val counters: mutable.Map[String, Counter] = scala.collection.mutable.Map[String, Counter]()

  def apply(next: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    val resultFuture = next(request)

    resultFuture.transform(
      result => {
        val counter = counters.getOrElseUpdate(s"${result.header.status}", metrics.counter(s"${result.header.status}"))
        counter.inc()
        result
      },
      exception => {
        val counter = counters.getOrElseUpdate("500", metrics.counter("500"))
        counter.inc()
        exception
      }
    )
  }

}
