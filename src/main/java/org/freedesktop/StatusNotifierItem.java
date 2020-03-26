package org.freedesktop;

import com.canonical.DBusMenu;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.List;

public class StatusNotifierItem {
  public StatusNotifierItem(String serviceName, String title, IStatusNotifierItem.Category category, String icon, List<MenuItem> menuItems) {
    try {
      statusNotifierItem = new StatusNotifierItemImpl(serviceName, title, category, icon);
      new DBusMenu(serviceName, menuItems);
    } catch (DBusException e) {
      statusNotifierItem = null;
    }
  }

  public void onActivate(StatusNotifierListener listener) {
    statusNotifierItem.addActivateListener(listener);
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
