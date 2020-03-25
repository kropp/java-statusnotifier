package org.freedesktop;

import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.HashMap;
import java.util.Map;

public class StatusNotifierItem implements IStatusNotifierItem, DBus.Properties {
  private final String WATCHER_BUSNAME = "org.kde.StatusNotifierWatcher";
  private final String WATCHER_OBJECTPATH = "/StatusNotifierWatcher";
  private final String SNI_OBJECTPATH = "/StatusNotifierItem";

  private Map<String, Variant<?>> myProperties = new HashMap<>();

  public StatusNotifierItem(String serviceName, String title, Category category, String icon) throws DBusException {
    myProperties.put("Id", new Variant<>(1));
    myProperties.put("Title", new Variant<>(title));
    myProperties.put("Status", new Variant<>(category.toString()));
    myProperties.put("IconName", new Variant<>(icon));

    connection = DBusConnection.getConnection(DBusConnection.SESSION);
    connection.requestBusName(serviceName);
    connection.exportObject(SNI_OBJECTPATH, this);

    IStatusNotifierWatcher watcher = connection.getRemoteObject(WATCHER_BUSNAME, WATCHER_OBJECTPATH, IStatusNotifierWatcher.class);

    watcher.RegisterStatusNotifierItem(serviceName);
  }

  private DBusConnection connection;

  @Override
  public <A> A Get(String s, String s1) {
    return null;
  }

  @Override
  public <A> void Set(String s, String s1, A a) {

  }

  @Override
  public Map<String, Variant<?>> GetAll(String s) {
    return myProperties;
  }

  @Override
  public void ContextMenu(int x, int y) {

  }

  @Override
  public void Activate(int x, int y) {
  }

  @Override
  public void SecondaryActivate(int x, int y) {

  }

  @Override
  public void Scroll(int delta, String orientation) {

  }

  @Override
  public boolean isRemote() {
    return false;
  }
}
