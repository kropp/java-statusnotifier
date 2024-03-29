package org.freedesktop;

import com.canonical.DBusMenu;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.List;

public class StatusNotifierItem {
  public StatusNotifierItem(String serviceName, String title, IStatusNotifierItem.Category category, List<Pixmap> pixmaps, String iconName, String iconThemePath, List<MenuItem> menuItems) throws DBusException {
    try {
      statusNotifierItem = new StatusNotifierItemImpl(serviceName, title, category, pixmaps, iconName, iconThemePath);
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

    private final String text;
    private final StatusNotifierItemMenuItemListener listener;
  }

  private StatusNotifierItemImpl statusNotifierItem;
}
