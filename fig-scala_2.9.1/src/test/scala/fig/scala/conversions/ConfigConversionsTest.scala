package fig.scala.conversions

import ConfigConversions._
import java.net.URI
import fig.Config
import org.junit.Test
import org.junit.Assert.assertEquals

class Uri2ConfigTest {
  @Test
  def testUri2Config() {
    println(System.getProperties)
    val projectRoot = System.getProperty("projectRoot")
    val config: Config = new URI("file://" + projectRoot + "/fig-example/src/main/config/example.properties")
    assertEquals("bar", config.get("foo"))
  }
}