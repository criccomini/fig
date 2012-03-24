package fig;

import java.io.InputStream;

public interface ConfigDeserializer {
  Config getConfig(InputStream in);
}
