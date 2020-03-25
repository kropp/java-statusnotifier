package org.freedesktop;

import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;

@DBusInterfaceName("org.kde.StatusNotifierWatcher")
public interface IStatusNotifierWatcher extends DBusInterface {
  void RegisterStatusNotifierItem(String service);
}
