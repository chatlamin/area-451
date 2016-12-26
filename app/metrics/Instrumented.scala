package metrics

import com.codahale.metrics.MetricRegistry
import nl.grons.metrics.scala.{HdrMetricBuilder, InstrumentedBuilder}

trait Instrumented extends InstrumentedBuilder {

  val metricsManager: MetricsManager
  override lazy protected val metricBuilder = new HdrMetricBuilder(metricBaseName, metricRegistry, resetAtSnapshot = true)
  override val metricRegistry: MetricRegistry = metricsManager.metricRegistry

}
