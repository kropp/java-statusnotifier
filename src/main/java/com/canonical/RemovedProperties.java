package com.canonical;

import java.util.List;

import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;

public final class RemovedProperties extends Struct {
  @Position(0)
  public final int a;
  @Position(1)
  public final List<String> b;

  public RemovedProperties(int a, List<String> b) {
    this.a = a;
    this.b = b;
  }
}