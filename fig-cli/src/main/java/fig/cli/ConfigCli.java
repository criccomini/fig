package fig.cli;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import fig.ConfigDeserializer;
import fig.ConfigException;
import fig.ConfigFileSystem;
import fig.serializers.PropertiesDeserializer;
import fig.fs.LocalFileSystem;
import fig.loaders.AutoConfigLoader;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class ConfigCli {
  public static void main(String[] args) throws Exception {
    // Define parameters.
    OptionParser parser = new OptionParser();
    parser.accepts("config-path", "URI location to a config file (e.g. file:///some/local/path.properties).").withRequiredArg().ofType(String.class);
    OptionSet options = parser.parse(args);

    // Verify legitimate parameters.
    if (!options.has("config-path")) {
      parser.printHelpOn(System.err);
      System.exit(-1);
    }

    // Get the config.
    URI uri = new URI(options.valueOf("config-path").toString());
    Map<String, String> config = new AutoConfigLoader().load(uri);

    // Run main-class.
    String mainClass = config.get("main-class");
    ConfigRunner runner = (ConfigRunner) Class.forName(mainClass).newInstance();
    runner.run(config);
  }
}
