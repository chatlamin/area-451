package metrics

import java.net.{InetAddress, InetSocketAddress}
import java.util.concurrent.TimeUnit
import javax.inject.{Inject, Singleton}

import com.codahale.metrics.{MetricFilter, MetricRegistry}
import com.codahale.metrics.graphite.{GraphiteReporter, GraphiteUDP}
import play.api.Configuration
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

@Singleton
class MetricsManager @Inject()(configuration: Configuration, lifecycle: ApplicationLifecycle) {

  val metricRegistry: MetricRegistry = new MetricRegistry()
  val graphiteHost: String = configuration.underlying.getString("graphite.host")
  val graphitePort: Int = configuration.underlying.getInt("graphite.port")
  val graphitePrefix: String = configuration.underlying.getString("graphite.prefix")
  val graphite: GraphiteUDP = new GraphiteUDP(new InetSocketAddress(graphiteHost, graphitePort))
  val graphiteReporter: GraphiteReporter = GraphiteReporter.forRegistry(metricRegistry)
    .prefixedWith(s"$graphitePrefix.${InetAddress.getLocalHost.getHostName}")
    .convertRatesTo(TimeUnit.SECONDS)
    .convertDurationsTo(TimeUnit.MILLISECONDS)
    .filter(MetricFilter.ALL)
    .build(graphite)
  graphiteReporter.start(1, TimeUnit.MINUTES)

  lifecycle.addStopHook { () =>
    Future.successful(graphiteReporter.close())
  }

}
