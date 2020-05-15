package org.gtk;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface Gtk extends Library {
  boolean gtk_init_check(int argc, String[] argv);
  void    gtk_main();

  Pointer gtk_menu_new();
  Pointer gtk_menu_item_new_with_label(String label);
  void    gtk_menu_shell_append(Pointer menu, Pointer item);
  void    gtk_widget_show(Pointer window);

  void    g_signal_connect_data(Pointer instance, String signal, Callback callback, Pointer data, Pointer destroy_data, int flags);

  static Gtk getInstance() {
    return Native.load("libgtk-3.so.0", Gtk.class);
  }
}
