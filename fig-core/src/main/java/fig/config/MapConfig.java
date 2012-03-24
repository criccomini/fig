package fig.config;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fig.Config;
import fig.util.Utils;

public class MapConfig extends Config {
  private final Map<String, String> map;

  public MapConfig(Map<String, String> map) {
    this.map = Collections.unmodifiableMap(map);
  }

  public String get(Object k) {
    Utils.notNull(k);
    return map.get(k);
  }

  public boolean containsKey(Object k) {
    Utils.notNull(k);
    return map.containsKey(k);
  }

  public Set<Entry<String, String>> entrySet() {
    return map.entrySet();
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public Set<String> keySet() {
    return map.keySet();
  }

  public int size() {
    return map.size();
  }

  public Collection<String> values() {
    return map.values();
  }

  public boolean containsValue(Object v) {
    return map.containsKey(v);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((map == null) ? 0 : map.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MapConfig other = (MapConfig) obj;
    if (map == null) {
      if (other.map != null)
        return false;
    } else if (!map.equals(other.map))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return map.toString();
  }
}
