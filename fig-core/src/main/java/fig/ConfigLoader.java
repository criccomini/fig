package fig;

import java.net.URI;

public interface ConfigLoader {
  Config getConfig(URI uri);
}
