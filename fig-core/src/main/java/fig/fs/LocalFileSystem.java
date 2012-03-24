package fig.fs;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;

import fig.Config;
import fig.ConfigDeserializer;
import fig.ConfigException;
import fig.ConfigFileSystem;

public class LocalFileSystem implements ConfigFileSystem {
  @Override
  public Config getConfig(URI uri, ConfigDeserializer deserializer) {
    try {
      InputStream in = new FileInputStream(uri.getRawPath());
      Config config = deserializer.getConfig(in);
      in.close();
      return config;
    } catch (Exception e) {
      throw new ConfigException(e);
    }
  }
}
