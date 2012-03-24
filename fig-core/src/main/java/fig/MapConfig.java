package fig;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fig.util.Utils;

public class MapConfig extends Config {

  private final Map<String,String> _map;
  public MapConfig(Map<String,String> map){
    _map = map;
  }
  
  @Override
  public String get(String s) {
    return _map.get(s);
  }

  @Override
  public Config subset(String prefix,boolean stripPrefix) {
    Utils.notNull(prefix);

    Map<String, String> out = new HashMap<String, String>();
    

    for (Entry<String,String> entry  : _map.entrySet()) {

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

  @Override
  public Set<String> getKeys() {
    return _map.keySet();
  }
  
}
