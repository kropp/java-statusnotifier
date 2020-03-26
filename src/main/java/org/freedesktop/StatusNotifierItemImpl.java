package org.freedesktop;

import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.Variant;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StatusNotifierItemImpl implements IStatusNotifierItem, DBus.Properties {
  private final String WATCHER_BUSNAME = "org.kde.StatusNotifierWatcher";
  private final String WATCHER_OBJECTPATH = "/StatusNotifierWatcher";
  private final String SNI_OBJECTPATH = "/StatusNotifierItem";

  private Map<String, Variant<?>> myProperties = new HashMap<>();

  public StatusNotifierItemImpl(String serviceName, String title, Category category, List<Pixmap> pixmaps) throws DBusException {
    myProperties.put("Id", new Variant<>(1));
    myProperties.put("Title", new Variant<>(title));
    myProperties.put("Status", new Variant<>(category.toString()));
//    myProperties.put("IconName", new Variant<>(icon));
    Pixmap[] pixmapArray = new Pixmap[pixmaps.size()];
    pixmaps.toArray(pixmapArray);
    myProperties.put("IconPixmap", new Variant<>(pixmapArray));

    connection = DBusConnection.getConnection(DBusConnection.SESSION);
    connection.requestBusName(serviceName);
    connection.exportObject(SNI_OBJECTPATH, this);

    IStatusNotifierWatcher watcher = connection.getRemoteObject(WATCHER_BUSNAME, WATCHER_OBJECTPATH, IStatusNotifierWatcher.class);

    watcher.RegisterStatusNotifierItem(serviceName);
  }

  private DBusConnection connection;

  @Override
  public <A> A Get(String s, String s1) {
    return (A) myProperties.get(s);
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
    if (myContextMenuListener != null) {
      myContextMenuListener.onActivate(new Point(x, y));
    }
  }

  private StatusNotifierListener myContextMenuListener;
  public void addContextMenuListener(StatusNotifierListener listener) {
    myContextMenuListener = listener;
  }

  @Override
  public void Activate(int x, int y) {
    if (myActivateListener != null) {
      myActivateListener.onActivate(new Point(x, y));
    }
  }

  private StatusNotifierListener myActivateListener;
  public void addActivateListener(StatusNotifierListener listener) {
    myActivateListener = listener;
  }

  @Override
  public void SecondaryActivate(int x, int y) {
    if (mySecondaryActivateListener != null) {
      mySecondaryActivateListener.onActivate(new Point(x, y));
    }
  }

  private StatusNotifierListener mySecondaryActivateListener;
  public void addSecondaryActivateListener(StatusNotifierListener listener) {
    mySecondaryActivateListener = listener;
  }

  @Override
  public void Scroll(int delta, String orientation) {
    if (myScrollListener != null) {
      myScrollListener.onScroll(delta, orientation);
    }
  }

  private StatusNotifierScrollListener myScrollListener;
  public void addSecondaryActivateListener(StatusNotifierScrollListener listener) {
    myScrollListener = listener;
  }

  @Override
  public boolean isRemote() {
    return false;
  }
}
