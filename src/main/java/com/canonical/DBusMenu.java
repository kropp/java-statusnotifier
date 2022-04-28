package com.canonical;

import org.freedesktop.StatusNotifierItem;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.Variant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.freedesktop.dbus.connections.impl.DBusConnection.DBusBusType.SESSION;

public class DBusMenu implements IDBusMenu {
  public static final String MENU_OBJECTPATH = "/MenuBar";
  private List<StatusNotifierItem.MenuItem> menuItems;
  private int myRevision = 2;

  public DBusMenu(String serviceName, List<StatusNotifierItem.MenuItem> menuItems) throws DBusException {
    this.menuItems = menuItems;
    DBusConnection connection = DBusConnection.getConnection(SESSION);
    connection.requestBusName(serviceName);
    connection.exportObject(MENU_OBJECTPATH, this);
  }

  @Override
  public Pair<UInt32,Layout> GetLayout(int parentId, int recursionDepth, List<String> propertyNames) {
    HashMap<String, Variant<?>> props = new HashMap<>();
    props.put("children-display", new Variant<>("submenu"));
    ArrayList<Variant<?>> layout = new ArrayList<>();
    for (int i = 0; i < menuItems.size(); i++) {
      layout.add(new Variant<>(new MenuItem(i + 2)));
    }
    return new Pair<>(new UInt32(myRevision++), new Layout(
        0, props, layout
    ));
  }

  @Override
  public List<UpdatedProperties> GetGroupProperties(List<Integer> ids, List<String> propertyNames) {
    List<UpdatedProperties> result = new ArrayList<>();

    int i = 2;
    for (StatusNotifierItem.MenuItem menuItem : menuItems) {
      HashMap<String, Variant<?>> item = new HashMap<>();
      item.put("label", new Variant<>(menuItem.getText()));
      result.add(new UpdatedProperties(i, item));
      i++;
    }

    return result;
  }

  @Override
  public Variant<?> GetProperty(int id, String name) {
    return null;
  }

  @Override
  public void Event(int id, String eventId, Variant<?> data, UInt32 timestamp) {
    int idx = id - 2;
    if (0 <= idx && idx < menuItems.size()) {
      menuItems.get(idx).getListener().onActivated();
    }
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
  public String getObjectPath() {
    return MENU_OBJECTPATH;
  }

  @Override
  public Pair<List<Integer>, List<Integer>> AboutToShowGroup(List<Integer> ids) {
    return null;
  }
}
