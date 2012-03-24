package fig.cli;

import java.net.URI;
import java.util.Map;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import fig.loaders.AutoConfigLoader;

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
    Map<String, String> config = new AutoConfigLoader().getConfig(uri);

    // Run main-class.
    String mainClass = config.get("main-class");
    ConfigRunner runner = (ConfigRunner) Class.forName(mainClass).newInstance();
    runner.run(config);
  }
}
