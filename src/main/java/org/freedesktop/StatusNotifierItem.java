package org.freedesktop;

import com.canonical.DBusMenu;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.List;

public class StatusNotifierItem {
  public StatusNotifierItem(String serviceName, String title, IStatusNotifierItem.Category category, List<Pixmap> pixmaps, List<MenuItem> menuItems) throws DBusException {
    try {
      statusNotifierItem = new StatusNotifierItemImpl(serviceName, title, category, pixmaps);
      new DBusMenu(serviceName, menuItems);
    } catch (DBusException e) {
      statusNotifierItem = null;
      throw e;
    }
  }

  public void onActivate(StatusNotifierListener listener) {
    if (statusNotifierItem != null) {
      statusNotifierItem.addActivateListener(listener);
    }
  }

  public static class MenuItem {
    public MenuItem(String text, StatusNotifierItemMenuItemListener listener) {
      this.text = text;
      this.listener = listener;
    }

    public String getText() {
      return text;
    }

    public StatusNotifierItemMenuItemListener getListener() {
      return listener;
    }

    private String text;
    private StatusNotifierItemMenuItemListener listener;
  }

  private StatusNotifierItemImpl statusNotifierItem;
}
