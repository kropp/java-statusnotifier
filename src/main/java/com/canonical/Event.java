package com.canonical;

import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

public final class Event extends Struct {
  @Position(0)
  public final int a;
  @Position(1)
  public final String b;
  @Position(2)
  public final Variant<?> c;
  @Position(3)
  public final UInt32 d;

  public Event(int a, String b, Variant<?> c, UInt32 d) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }
}