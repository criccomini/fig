# Fig

Fig is a simple configuartion library for Java.

## Why?

*   No static methods
*   Pluggable
*   No types
*   Stupid simple
*   Immutability

## Using Fig

Fig is really easy to use. I promise.

Let's use Fig with a Java property file.

```
hello=world
```

Now, in Java, you can get your properties.

```java
Config config = new AutoConfigLoader().getConfig(new URI("file:///tmp/config.properties"));
System.out.println("hello " + config.get("hello"));
```

## Command Line Execution

Fig also provides an easy way to execute programs from the CLI using config (instead of CLI arguments).

Let's create another Java property file.

```
main-class=fig.example.MyConfigPrinter
foo=bar
```
The main-class allows us to execute the MyConfigPrinter.

```
./bin/run-config.sh --config-path=file:///tmp/config.properties
```

It really is that simple!

## Example Project

Fig has an example project falled "fig-example", which demonstrates how to use Fig with the run-config.sh script.

Follow these instructions to try things out.

```
git clone git://github.com/criccomini/fig.git
cd fig
mvn clean package
cd fig-example/target
unzip fig-example-bin.zip
cd fig-example
./bin/run-config.sh --config-path=file://`pwd`/config/example.properties
```

This will execute a little Java class that prints the configuration in example.properties.

## Extensibility

Fig comes with a lot of FileSystems and ConfigDeserializers out of the box, but you can always extend Fig to use your own config system by using the following Java properties.

```
-Dfig.fs=http:fig.fs.HttpFileSystem,hdfs:fig.fs.HadoopFileSystem
-Dfig.deserializers=yaml:fig.serializers.YamlDeserializer
```

Now, AutoConfigLoader will support URIs with an HTTP or HDFS scheme (http://.. or hdfs://..) and YAML config files (*.yaml). 

```
new AutoConfigLoader().getConfig(new URI("http://localhost/my-config.yaml"));
```

How great is that?

## Rules of the Game

Since configuration is often a core and pervasive part of an API, Fig makes the following guarantees.

1. Fig uses semantic versioning (http://semver.org/), and will never make backwards incompatible changes without bumping the major version (*.0.0).
2. Fig's core library (fig-core) will have any dependencies.

