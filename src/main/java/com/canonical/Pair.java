package com.canonical;

import org.freedesktop.dbus.Tuple;
import org.freedesktop.dbus.annotations.Position;

public final class Pair<A,B> extends Tuple {
  @Position(0)
  public final A a;
  @Position(1)
  public final B b;

  public Pair(A a, B b) {
    this.a = a;
    this.b = b;
  }
}