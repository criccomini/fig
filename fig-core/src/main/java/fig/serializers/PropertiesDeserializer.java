package fig.serializers;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import fig.Config;
import fig.ConfigDeserializer;
import fig.ConfigException;
import fig.config.MapConfig;

public class PropertiesDeserializer implements ConfigDeserializer {
  public Config getConfig(InputStream in) {
    Properties props = new Properties();
    HashMap<String, String> out = new HashMap<String, String>();

    try {
      props.load(in);
    } catch (Exception e) {
      throw new ConfigException("Unable to load properties config.", e);
    }

    for (Entry<Object, Object> entry : props.entrySet()) {
      Object v = entry.getValue();
      out.put(entry.getKey().toString(), (v == null) ? null : v.toString());
    }

    return new MapConfig(out);
  }
}
