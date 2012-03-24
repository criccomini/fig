package fig.util;

public class Utils {
  public static void notNull(Object o) {
    if (o == null) {
      throw new NullPointerException();
    }
  }

  public static void notNull(String msg, Object o) {
    if (o == null) {
      throw new NullPointerException(msg);
    }
  }
}
