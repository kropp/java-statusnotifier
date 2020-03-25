package org.freedesktop;


import org.freedesktop.dbus.DBusInterface;
import org.freedesktop.dbus.DBusInterfaceName;
import org.freedesktop.dbus.DBusSignal;
import org.freedesktop.dbus.exceptions.DBusException;


@DBusInterfaceName(value = "org.freedesktop.IStatusNotifierItem")
public interface IStatusNotifierItem extends DBusInterface {
  void ContextMenu(int x, int y);
  void Activate(int x, int y);
  void SecondaryActivate(int x, int y);
  void Scroll(int delta, String orientation);

  enum Category {
    ApplicationStatus,
    Communications,
    SystemServices,
    Hardware
  }

  class NewTitle extends DBusSignal {
    public NewTitle(String path) throws DBusException {
      super(path);
    }
  }

  class NewIcon extends DBusSignal {
    public NewIcon(String path) throws DBusException {
      super(path);
    }
  }

  class NewAttentionIcon extends DBusSignal {
    public NewAttentionIcon(String path) throws DBusException {
      super(path);
    }
  }

  class NewOverlayIcon extends DBusSignal {
    public NewOverlayIcon(String path) throws DBusException {
      super(path);
    }
  }

  class NewToolTip extends DBusSignal {
    public NewToolTip(String path) throws DBusException {
      super(path);
    }
  }

  class NewStatus extends DBusSignal {
    public final String status;

    public NewStatus(String path, String status) throws DBusException {
      super(path, status);
      this.status = status;
    }
  }
}
