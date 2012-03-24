package fig.fs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.I0Itec.zkclient.serialize.ZkSerializer;

import fig.Config;
import fig.ConfigDeserializer;
import fig.ConfigException;

public class ZookeeperConfigDeserializer implements ZkSerializer {
  private final ConfigDeserializer deserializer;

  public ZookeeperConfigDeserializer(ConfigDeserializer deserializer) {
    this.deserializer = deserializer;
  }

  public byte[] serialize(Object data) {
    throw new ConfigException("Serialization not supported for ZooKeeper.");
  }

  public Object deserialize(byte[] bytes) {
    if (bytes == null)
      return null;
    else {
      try {
        InputStream in = new ByteArrayInputStream(bytes);
        Config config = deserializer.getConfig(in);
        in.close();
        return config;
      } catch (Exception e) {
        throw new ConfigException(e);
      }
    }
  }
}
