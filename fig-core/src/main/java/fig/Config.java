package fig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fig.config.MapConfig;
import fig.util.Utils;

public abstract class Config implements Map<String, String> {
  public Config subset(String prefix, boolean stripPrefix) {
    Utils.notNull(prefix);

    Map<String, String> out = new HashMap<String, String>();

    for (Entry<String, String> entry : entrySet()) {
      String k = entry.getKey();
      if (k != null && k.startsWith(prefix)) {
        if (stripPrefix) {
          k = k.substring(prefix.length());
        }

        out.put(k, entry.getValue());
      }
    }

    return new MapConfig(out);
  }

  public String get(String k, String defaultString) {
    if (!containsKey(k)) {
      return defaultString;
    }
    return get(k);
  }

  public boolean getBoolean(String k, boolean defaultValue) {
    if (containsKey(k))
      return "true".equalsIgnoreCase(get(k));
    else
      return defaultValue;
  }

  public boolean getBoolean(String k) {
    if (containsKey(k))
      return "true".equalsIgnoreCase(get(k));
    else
      throw new ConfigException("Missing key " + k + ".");
  }

  public long getShort(String k, short defaultValue) {
    if (containsKey(k))
      return Short.parseShort(get(k));
    else
      return defaultValue;
  }

  public long getShort(String k) {
    if (containsKey(k))
      return Short.parseShort(get(k));
    else
      throw new ConfigException("Missing key " + k + ".");
  }

  public long getLong(String k, long defaultValue) {
    if (containsKey(k))
      return Long.parseLong(get(k));
    else
      return defaultValue;
  }

  public long getLong(String k) {
    if (containsKey(k))
      return Long.parseLong(get(k));
    else
      throw new ConfigException("Missing key " + k + ".");
  }

  public int getInt(String k, int defaultValue) {
    if (containsKey(k))
      return Integer.parseInt(get(k));
    else
      return defaultValue;
  }

  public int getInt(String k) {
    if (containsKey(k))
      return Integer.parseInt(get(k));
    else
      throw new ConfigException("Missing key " + k + ".");
  }

  public double getDouble(String k, double defaultValue) {
    if (containsKey(k))
      return Double.parseDouble(get(k));
    else
      return defaultValue;
  }

  public double getDouble(String k) {
    if (containsKey(k))
      return Double.parseDouble(get(k));
    else
      throw new ConfigException("Missing key " + k + ".");
  }

  public List<String> getList(String k, List<String> defaultValue) {
    if (!containsKey(k))
      return defaultValue;

    String value = get(k);
    String[] pieces = value.split("\\s*,\\s*");
    return Arrays.asList(pieces);
  }

  public List<String> getList(String k) {
    if (!containsKey(k))
      throw new ConfigException("Missing key " + k + ".");
    return getList(k, null);
  }

  @SuppressWarnings("unchecked")
  public <T> Class<T> getClass(String k) {
    if (containsKey(k)) {
      try {
        return (Class<T>) Class.forName(get(k));
      } catch (Exception e) {
        throw new ConfigException("Unable to find class.", e);
      }
    } else {
      throw new ConfigException("Missing key " + k + ".");
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T getNewInstance(String k) {
    try {
      return (T) getClass(k).newInstance();
    } catch (Exception e) {
      throw new ConfigException("Unable to instantiate class.", e);
    }
  }

  public void clear() {
    throw new ConfigException("Config is immutable.");
  }

  public String put(String key, String value) {
    throw new ConfigException("Config is immutable.");
  }

  public void putAll(Map<? extends String, ? extends String> m) {
    throw new ConfigException("Config is immutable.");
  }

  public String remove(Object s) {
    throw new ConfigException("Config is immutable.");
  }
}
