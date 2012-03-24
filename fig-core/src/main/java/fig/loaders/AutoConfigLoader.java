package fig.loaders;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import fig.ConfigDeserializer;
import fig.ConfigException;
import fig.ConfigFileSystem;
import fig.ConfigLoader;
import fig.util.Utils;

public class AutoConfigLoader implements ConfigLoader {
  public static final Map<String, String> DEFAULT_FS;
  public static final Map<String, String> DEFAULT_DESERIALIZERS;

  static {
    Map<String, String> fsClasses = new HashMap<String, String>();
    Map<String, String> deserializerClasses = new HashMap<String, String>();
    fsClasses.put("file", "fig.fs.LocalFileSystem");
    fsClasses.put("zk", "fig.fs.ZookeeperFileSystem");
    fsClasses.put("zookeeper", "fig.fs.ZookeeperFileSystem");
    deserializerClasses.put("properties", "fig.serializers.PropertiesDeserializer");
    deserializerClasses.put("props", "fig.serializers.PropertiesDeserializer");
    deserializerClasses.put("json", "fig.serializers.JsonDeserializer");

    String injectedFs = System.getProperty("fig.fs");
    String injectedDeserializers = System.getProperty("fig.deserializers");

    if (injectedFs != null) {
      String[] pairs = injectedFs.split(",\\s*");

      for (String pair : pairs) {
        String[] kv = pair.split("\\:");
        if (kv.length == 2) {
          fsClasses.put(kv[0], kv[1]);
        }
      }
    }

    if (injectedDeserializers != null) {
      String[] pairs = injectedDeserializers.split(",\\s*");

      for (String pair : pairs) {
        String[] kv = pair.split("\\:");
        if (kv.length == 2) {
          deserializerClasses.put(kv[0], kv[1]);
        }
      }
    }

    DEFAULT_FS = Collections.unmodifiableMap(fsClasses);
    DEFAULT_DESERIALIZERS = Collections.unmodifiableMap(deserializerClasses);
  }

  private final Map<String, String> fsClasses;
  private final Map<String, String> deserializerClasses;

  public AutoConfigLoader() {
    this(DEFAULT_FS, DEFAULT_DESERIALIZERS);
  }

  public AutoConfigLoader(Map<String, String> fsClasses, Map<String, String> deserializerClasses) {
    this.fsClasses = fsClasses;
    this.deserializerClasses = deserializerClasses;
  }

  @Override
  public Map<String, String> getConfig(URI uri) {
    // Get the file system based on scheme.
    String fsClass = fsClasses.get(uri.getScheme());

    Utils.notNull("No config-fs specified, and unable to determine which file system to use.", fsClass);

    // Get the deserializer based on file extension.
    String path = uri.getRawPath();
    String extn = "";

    if (path.contains(".")) {
      extn = path.substring(path.lastIndexOf('.') + 1, path.length());
    }

    String deserializerClass = deserializerClasses.get(extn);

    Utils.notNull("No config-deserializer specified, and unable to determine which file system to use.", deserializerClass);

    // Get the config.
    try {
      ConfigFileSystem fs = (ConfigFileSystem) Class.forName(fsClass).newInstance();
      ConfigDeserializer deserializer = (ConfigDeserializer) Class.forName(deserializerClass).newInstance();
      return fs.getConfig(uri, deserializer);
    } catch (Exception e) {
      throw new ConfigException(e);
    }
  }
}
