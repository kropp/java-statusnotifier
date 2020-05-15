package org.gtk;

import com.sun.jna.Pointer;

// typedef void (*Callback)(void *item, void* data);
public interface Callback extends com.sun.jna.Callback {
  void invoke(Pointer item, Pointer data);
}
