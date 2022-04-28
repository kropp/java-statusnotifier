package org.freedesktop;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;

@DBusInterfaceName("org.kde.StatusNotifierWatcher")
public interface IStatusNotifierWatcher extends DBusInterface {
  /**
   * Register a StatusNotifierItem into the StatusNotifierWatcher, in the form of its full name on the session bus,
   * for instance org.freedesktop.StatusNotifierItem-4077-1. A StatusNotifierItem instance must be registered
   * to the watcher in order to be noticed from both the watcher and the StatusNotifierHost instances.
   * If the registered StatusNotifierItem goes away from the session bus, the StatusNotifierWatcher
   * should automatically notice it and remove it from the list of registered services.
   * @param service service name
   */
  void RegisterStatusNotifierItem(String service);
}
