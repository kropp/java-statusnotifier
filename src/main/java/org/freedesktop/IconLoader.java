package org.freedesktop;

import java.io.IOException;
import java.io.InputStream;

public class IconLoader {
  public static Pixmap loadPixmap(int width, int height, InputStream stream) {
    try {
      byte[] result = new byte[width * height * 4];
      if (stream.read(result, 0, result.length) == result.length) {
        return new Pixmap(width, height, result);
      }
    } catch (IOException ignored) {
    }
    return new Pixmap(0, 0, new byte[0]);
  }
}
