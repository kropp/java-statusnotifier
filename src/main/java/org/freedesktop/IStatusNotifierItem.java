package org.freedesktop;


import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;


@DBusInterfaceName(value = "org.kde.StatusNotifierItem")
public interface IStatusNotifierItem extends DBusInterface {
  /**
   * Asks the status notifier item to show a context menu, this is typically a consequence of user input,
   * such as mouse right click over the graphical representation of the item.
   *
   * the x and y parameters are in screen coordinates and is to be considered an hint to the item about where to show the context menu.
   *
   * @param x x screen coordinate to show the context menu
   * @param y y screen coordinate to show the context menu
   */
  void ContextMenu(int x, int y);

  /**
   * Asks the status notifier item for activation, this is typically a consequence of user input,
   * such as mouse left click over the graphical representation of the item.
   * The application will perform any task is considered appropriate as an activation request.
   *
   * the x and y parameters are in screen coordinates and is to be considered an hint to the item where to show eventual windows (if any).
   *
   * @param x x screen coordinate to show eventual windows (if any)
   * @param y y screen coordinate to show eventual windows (if any)
   */
  void Activate(int x, int y);

  /**
   * Is to be considered a secondary and less important form of activation compared to Activate.
   * This is typically a consequence of user input, such as mouse middle click over the graphical representation of the item.
   * The application will perform any task is considered appropriate as an activation request.
   *
   * the x and y parameters are in screen coordinates and is to be considered an hint to the item where to show eventual windows (if any).
   *
   * @param x x screen coordinate to show eventual windows (if any)
   * @param y y screen coordinate to show eventual windows (if any)
   */
  void SecondaryActivate(int x, int y);

  /**
   * The user asked for a scroll action. This is caused from input such as mouse wheel over the graphical representation of the item.
   *
   * @param delta amount of scroll
   * @param orientation horizontal or vertical orientation of the scroll request and its legal values are horizontal and vertical
   */
  void Scroll(int delta, String orientation);

  /**
   * Describes the category of this item.
   */
  enum Category {
    /**
     * The item describes the status of a generic application, for instance the current state of a media player.
     * In the case where the category of the item can not be known, such as when the item is being proxied
     * from another incompatible or emulated system, ApplicationStatus can be used a sensible default fallback.
     */
    ApplicationStatus,
    /**
     * The item describes the status of communication oriented applications, like an instant messenger or an email client.
     */
    Communications,
    /**
     * The item describes services of the system not seen as a stand alone application by the user,
     * such as an indicator for the activity of a disk indexing service.
     */
    SystemServices,
    /**
     * The item describes the state and control of a particular hardware,
     * such as an indicator of the battery charge or sound card volume control.
     */
    Hardware
  }

  /**
   * The item has a new title: the graphical representation should read it again immediately.
   */
  class NewTitle extends DBusSignal {
    public NewTitle(String path) throws DBusException {
      super(path);
    }
  }

  /**
   * The item has a new icon: the graphical representation should read it again immediately.
   */
  class NewIcon extends DBusSignal {
    public NewIcon(String path) throws DBusException {
      super(path);
    }
  }

  /**
   * The item has a new attention icon: the graphical representation should read it again immediately.
   */
  class NewAttentionIcon extends DBusSignal {
    public NewAttentionIcon(String path) throws DBusException {
      super(path);
    }
  }

  /**
   * The item has a new overlay icon: the graphical representation should read it again immediately.
   */
  class NewOverlayIcon extends DBusSignal {
    public NewOverlayIcon(String path) throws DBusException {
      super(path);
    }
  }

  /**
   * The item has a new tooltip: the graphical representation should read it again immediately.
   */
  class NewToolTip extends DBusSignal {
    public NewToolTip(String path) throws DBusException {
      super(path);
    }
  }

  /**
   * The item has a new status, that is passed as an argument of the signal.
   */
  class NewStatus extends DBusSignal {
    public final String status;

    public NewStatus(String path, String status) throws DBusException {
      super(path, status);
      this.status = status;
    }
  }
}
