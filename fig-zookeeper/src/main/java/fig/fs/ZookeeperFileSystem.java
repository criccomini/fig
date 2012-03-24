package fig.fs;

import java.net.URI;
import java.util.Map;

import org.I0Itec.zkclient.ZkClient;

import fig.ConfigDeserializer;
import fig.ConfigFileSystem;

public class ZookeeperFileSystem implements ConfigFileSystem {
  @Override
  public Map<String, String> getConfig(URI uri, ConfigDeserializer deserializer) {
    String hostPortPath = uri.getHost() + ":" + uri.getPort() + uri.getPath();
    ZkClient zk = new ZkClient(hostPortPath, 5000, 5000, new ZookeeperConfigDeserializer(deserializer));
    return zk.<Map<String, String>> readData("/");
  }
}
