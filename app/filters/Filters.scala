package filters

import javax.inject.Inject

import com.mohiva.play.htmlcompressor.HTMLCompressorFilter
import play.api.http.DefaultHttpFilters
import play.filters.gzip.GzipFilter

class Filters @Inject() (accessLoggingFilter: AccessLoggingFilter, gzipFilter: GzipFilter, htmlCompressorFilter: HTMLCompressorFilter) extends DefaultHttpFilters(
  accessLoggingFilter, gzipFilter, htmlCompressorFilter
)
