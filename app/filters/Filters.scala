package filters

import javax.inject.Inject

import com.mohiva.play.htmlcompressor.HTMLCompressorFilter
import play.api.http.DefaultHttpFilters
import play.filters.gzip.GzipFilter

class Filters @Inject() (gzipFilter: GzipFilter, htmlCompressorFilter: HTMLCompressorFilter) extends DefaultHttpFilters(
  gzipFilter, htmlCompressorFilter
)
