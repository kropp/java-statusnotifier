package org.gtk.appindicator;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

@Structure.FieldOrder({"parent"})
public class GAppIndicator extends Structure implements Structure.ByReference {
  public GAppIndicator(Pointer p) {
    super();
    useMemory(p);
    read();
  }

  public GAppIndicator() {
    super();
    ensureAllocated();
  }

  public Pointer parent;
}
