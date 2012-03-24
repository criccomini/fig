package fig.fs;

import java.net.URI;
import java.util.Map;

import org.I0Itec.zkclient.ZkClient;

import fig.Config;
import fig.ConfigDeserializer;
import fig.ConfigFileSystem;
import fig.config.MapConfig;

public class ZookeeperFileSystem implements ConfigFileSystem {
  @Override
  public Config getConfig(URI uri, ConfigDeserializer deserializer) {
    String hostPortPath = uri.getHost() + ":" + uri.getPort() + uri.getPath();
    ZkClient zk = new ZkClient(hostPortPath, 5000, 5000, new ZookeeperConfigDeserializer(deserializer));
    return new MapConfig(zk.<Map<String, String>> readData("/"));
  }
}
