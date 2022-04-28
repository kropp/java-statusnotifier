package org.freedesktop;

import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;

public class Pixmap extends Struct {
  @Position(0)
  public final int width;
  @Position(1)
  public final int height;
  @Position(2)
  public final byte[] data;

  public Pixmap(int width, int height, byte[] data) {
    this.width = width;
    this.height = height;
    this.data = data;
  }
}
