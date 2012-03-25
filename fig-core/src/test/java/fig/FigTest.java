package fig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import fig.Config;
import fig.config.MapConfig;
import fig.fs.LocalFileSystem;

public class FigTest {
  @Test
  public void testFig() throws ParseException {
    Map<String, String> map = new HashMap<String, String>();
    map.put("name", "fig");
    map.put("year", "2012");
    map.put("list", "a,b");
    map.put("sub.k1", "newton");
    map.put("sub.k2", "1000");
    map.put("fs", "fig.fs.LocalFileSystem");
    map.put("dt", "Tue Nov 04 21:53:43 EST 2003");

    Config conf = new MapConfig(map);

    TestCase.assertEquals("fig", conf.get("name"));
    TestCase.assertEquals("2012", conf.get("year"));
    TestCase.assertEquals(2012, conf.getInt("year"));
    TestCase.assertEquals((short) 2012, conf.getShort("year"));
    TestCase.assertEquals((long) 2012, conf.getLong("year"));
    TestCase.assertEquals("a,b", conf.get("list"));

    // test class
    TestCase.assertEquals(LocalFileSystem.class, conf.getClass("fs"));
    TestCase.assertEquals(LocalFileSystem.class, conf.getNewInstance("fs").getClass());

    // test dates
    TestCase.assertEquals(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse("Tue Nov 04 21:53:43 EST 2003"), conf.getDate("dt", "EEE MMM dd HH:mm:ss zzz yyyy"));

    // test list
    List<String> list = conf.getList("list");
    TestCase.assertEquals(2, list.size());
    TestCase.assertEquals("a", list.get(0));
    TestCase.assertEquals("b", list.get(1));

    // test subset
    Config subConf = conf.subset("sub.", true);
    Set<String> subKeys = subConf.keySet();

    TestCase.assertEquals(2, subKeys.size());

    TestCase.assertTrue(subKeys.contains("k1"));
    TestCase.assertTrue(subKeys.contains("k2"));

    TestCase.assertEquals("newton", subConf.get("k1"));
    TestCase.assertEquals(1000, subConf.getInt("k2"));

    TestCase.assertEquals("???", conf.get("unknown", "???"));
    TestCase.assertEquals(5, conf.getInt("five", 5));
  }
}
