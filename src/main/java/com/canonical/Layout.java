package com.canonical;


import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.Variant;

import java.util.List;
import java.util.Map;

public final class Layout extends Struct {
  @Position(0)
  public final int a;
  @Position(1)
  public final Map<String, Variant<?>> b;
  @Position(2)
  public final List<Variant<?>> c;

  public Layout(int a, Map<String, Variant<?>> b, List<Variant<?>> c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
}