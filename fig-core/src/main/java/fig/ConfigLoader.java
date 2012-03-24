package fig;

import java.net.URI;
import java.util.Map;

public interface ConfigLoader {
  Map<String, String> load(URI uri);
}
