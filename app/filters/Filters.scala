package filters

import javax.inject.Inject

import com.mohiva.play.htmlcompressor.HTMLCompressorFilter
import play.api.http.DefaultHttpFilters
import play.filters.gzip.GzipFilter

class Filters @Inject() (httpStatusCountingFilter: HttpStatusCountingFilter,
                         httpResponseTimingFilter: HttpResponseTimingFilter,
                         accessLoggingFilter: AccessLoggingFilter,
                         gzipFilter: GzipFilter,
                         htmlCompressorFilter: HTMLCompressorFilter)
  extends DefaultHttpFilters(
  httpStatusCountingFilter,
  httpResponseTimingFilter,
  accessLoggingFilter,
  gzipFilter,
  htmlCompressorFilter
)
