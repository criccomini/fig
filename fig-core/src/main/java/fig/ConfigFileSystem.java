package fig;

import java.net.URI;

public interface ConfigFileSystem {
  Config getConfig(URI uri, ConfigDeserializer deserializer);
}
