package org.gtk.appindicator;


import com.sun.jna.Pointer;
import org.freedesktop.IStatusNotifierItem;
import org.freedesktop.StatusNotifierItem;
import org.freedesktop.dbus.exceptions.DBusException;
import org.gtk.Gtk;

import java.util.List;

public class AppIndicator {
  public AppIndicator(String serviceName, IStatusNotifierItem.Category category, String iconName, String themePath, List<StatusNotifierItem.MenuItem> menuItems) throws DBusException {
    Gtk gtk = Gtk.getInstance();
    LibAppIndicator libAppIndicator = LibAppIndicator.getInstance();

    if (!gtk.gtk_init_check(0, new String[0])) {
      throw new IllegalStateException("Cannot initialize gtk");
    }

    GAppIndicator appIndicator = libAppIndicator.app_indicator_new_with_path(serviceName, iconName, AppIndicatorCategory.from(category), themePath);
    libAppIndicator.app_indicator_set_status(appIndicator, AppIndicatorStatus.ACTIVE);


    Pointer menu = gtk.gtk_menu_new();
    for (StatusNotifierItem.MenuItem menuItem : menuItems) {
      Pointer item = gtk.gtk_menu_item_new_with_label(menuItem.getText());
      gtk.g_signal_connect_data(item, "activate", (instance, data) -> menuItem.getListener().onActivated(), Pointer.NULL, Pointer.NULL, 0);
      gtk.gtk_widget_show(item);
      gtk.gtk_menu_shell_append(menu, item);
    }

    libAppIndicator.app_indicator_set_menu(appIndicator, menu);
  }
}
