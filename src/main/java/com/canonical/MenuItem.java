package com.canonical;


import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

import java.util.ArrayList;
import java.util.List;

public class MenuItem extends Struct {
  @Position(0)
  public final UInt32 id;
  @Position(1)
  public final List<Variant<?>> a;
  @Position(2)
  public final List<Variant<?>> b;

  public MenuItem(int id) {
    this.id = new UInt32(id);
    a = new ArrayList<>();
    b = new ArrayList<>();
  }
}
