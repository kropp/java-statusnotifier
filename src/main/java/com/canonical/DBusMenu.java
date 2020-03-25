package com.canonical;

import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.UInt32;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBusMenu implements IDBusMenu {
  private final DBusConnection connection;
  private final String MENU_OBJECTPATH = "/MenuBar";

  public DBusMenu(String serviceName) throws DBusException {
    connection = DBusConnection.getConnection(DBusConnection.SESSION);
    connection.requestBusName(serviceName);
    connection.exportObject(MENU_OBJECTPATH, this);
  }

  @Override
  public Pair<UInt32,Layout> GetLayout(int parentId, int recursionDepth, List<String> propertyNames) {
    HashMap<String, Variant<?>> props = new HashMap<>();
    props.put("children-display", new Variant<>("submenu"));
    ArrayList<Variant<?>> layout = new ArrayList<>();
    return new Pair<>(new UInt32(2), new Layout(
        0, props, layout
    ));
  }

  @Override
  public List<UpdatedProperties> GetGroupProperties(List<Integer> ids, List<String> propertyNames) {
    List<UpdatedProperties> menuItems = new ArrayList<>();
    return menuItems;
  }

  @Override
  public Variant<?> GetProperty(int id, String name) {
    return null;
  }

  @Override
  public void Event(int id, String eventId, Variant<?> data, UInt32 timestamp) {
  }

  @Override
  public List<Integer> EventGroup(List<Event> events) {
    return null;
  }

  @Override
  public boolean AboutToShow(int id) {
    return false;
  }

  @Override
  public boolean isRemote() {
    return false;
  }

  @Override
  public Pair<List<Integer>, List<Integer>> AboutToShowGroup(List<Integer> ids) {
    return null;
  }
}
