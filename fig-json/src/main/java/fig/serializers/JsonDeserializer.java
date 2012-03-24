package fig.serializers;

import java.io.InputStream;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import fig.Config;
import fig.ConfigDeserializer;
import fig.ConfigException;
import fig.config.MapConfig;

public class JsonDeserializer implements ConfigDeserializer {
  private final ObjectMapper jsonMapper = new ObjectMapper();

  @SuppressWarnings("unchecked")
  @Override
  public Config getConfig(InputStream in) {
    try {
      byte[] b = new byte[1024];
      int read = 0;
      StringBuffer sb = new StringBuffer();

      while ((read = in.read(b)) >= 0) {
        sb.append(new String(b, 0, read, "UTF-8"));
      }

      return new MapConfig(jsonMapper.readValue(sb.toString(), Map.class));
    } catch (Exception e) {
      throw new ConfigException("Unable to get config from JSON.", e);
    }
  }
}
