package fig.scala.conversions

import java.net.URI
import fig.loaders.AutoLoader;

object ConfigConversions {
  implicit def uri2config(uri: URI) = new AutoLoader().getConfig(uri)
}