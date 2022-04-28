package com.canonical;

import java.util.Map;

import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.types.Variant;

public final class UpdatedProperties extends Struct {
  @Position(0)
  public final int id;
  @Position(1)
  public final Map<String, Variant<?>> properties;

  public UpdatedProperties(int id, Map<String, Variant<?>> properties) {
    this.id = id;
    this.properties = properties;
  }
}