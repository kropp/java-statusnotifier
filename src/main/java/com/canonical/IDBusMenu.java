package com.canonical;

import org.freedesktop.dbus.*;
import org.freedesktop.dbus.exceptions.DBusException;

import java.util.List;

/**
 * A DBus interface to expose menus on DBus.
 * 		Menu items are represented with a unique numeric id and a dictionary of
 * 		properties.
 * 		To reduce the amount of DBus traffic, a property should only be returned
 * 		if its value is not the default value.
 * 		Available properties are:
 * 		<table>
 * 		<tr>
 * 			<th>Name</th>
 * 			<th>Type</th>
 * 			<th>Description</th>
 * 			<th>Default Value</th>
 * 		</tr>
 * 		<tr>
 * 			<td>type</td>
 * 			<td>String</td>
 * 			<td>Can be one of:
 * 			- "standard": an item which can be clicked to trigger an action or
 * 			  show another menu
 * 			- "separator": a separator
 * 			Vendor specific types can be added by prefixing them with
 * 			"x-<vendor>-".
 * 			</td>
 * 			<td>"standard"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>label</td>
 * 			<td>string</td>
 * 			<td>Text of the item, except that:
 * 			-# two consecutive underscore characters "__" are displayed as a
 * 			single underscore,
 * 			-# any remaining underscore characters are not displayed at all,
 * 			-# the first of those remaining underscore characters (unless it is
 * 			the last character in the string) indicates that the following
 * 			character is the access key.
 * 			</td>
 * 			<td>""</td>
 * 		</tr>
 * 		<tr>
 * 			<td>enabled</td>
 * 			<td>boolean</td>
 * 			<td>Whether the item can be activated or not.</td>
 * 			<td>true</td>
 * 		</tr>
 * 		<tr>
 * 			<td>visible</td>
 * 			<td>boolean</td>
 * 			<td>True if the item is visible in the menu.</td>
 * 			<td>true</td>
 * 		</tr>
 * 		<tr>
 * 			<td>icon-name</td>
 * 			<td>string</td>
 * 			<td>Icon name of the item, following the freedesktop.org icon spec.</td>
 * 			<td>""</td>
 * 		</tr>
 * 		<tr>
 * 			<td>icon-data</td>
 * 			<td>binary</td>
 * 			<td>PNG data of the icon.</td>
 * 			<td>Empty</td>
 * 		</tr>
 * 		<tr>
 * 			<td>shortcut</td>
 * 			<td>array of arrays of strings</td>
 * 			<td>The shortcut of the item. Each array represents the key press
 * 			in the list of keypresses. Each list of strings contains a list of
 * 			modifiers and then the key that is used. The modifier strings
 * 			allowed are: "Control", "Alt", "Shift" and "Super".
 * 			- A simple shortcut like Ctrl+S is represented as:
 * 			  [["Control", "S"]]
 * 			- A complex shortcut like Ctrl+Q, Alt+X is represented as:
 * 			  [["Control", "Q"], ["Alt", "X"]]</td>
 * 			<td>Empty</td>
 * 		</tr>
 * 		<tr>
 * 			<td>toggle-type</td>
 * 			<td>string</td>
 * 			<td>
 * 			If the item can be toggled, this property should be set to:
 * 			- "checkmark": Item is an independent togglable item
 * 			- "radio": Item is part of a group where only one item can be
 * 			  toggled at a time
 * 			- "": Item cannot be toggled
 * 			</td>
 * 			<td>""</td>
 * 		</tr>
 * 		<tr>
 * 			<td>toggle-state</td>
 * 			<td>int</td>
 * 			<td>
 * 			Describe the current state of a "togglable" item. Can be one of:
 * 			- 0 = off
 * 			- 1 = on
 * 			- anything else = indeterminate
 * 			Note:
 * 			The implementation does not itself handle ensuring that only one
 * 			item in a radio group is set to "on", or that a group does not have
 * 			"on" and "indeterminate" items simultaneously; maintaining this
 * 			policy is up to the toolkit wrappers.
 * 			</td>
 * 			<td>-1</td>
 * 		</tr>
 * 		<tr>
 * 			<td>children-display</td>
 * 			<td>string</td>
 * 			<td>
 * 			If the menu item has children this property should be set to
 * 			"submenu".
 * 			</td>
 * 			<td>""</td>
 * 		</tr>
 * 		</table>
 * 		Vendor specific properties can be added by prefixing them with
 * 		"x-<vendor>-".
 */
@DBusInterfaceName("com.canonical.dbusmenu")
public interface IDBusMenu extends DBusInterface {
  class ItemsPropertiesUpdated extends DBusSignal {
    public final List<UpdatedProperties> updatedProps;
    public final List<RemovedProperties> removedProps;

    public ItemsPropertiesUpdated(String path, List<UpdatedProperties> updatedProps, List<RemovedProperties> removedProps) throws DBusException {
      super(path, updatedProps, removedProps);
      this.updatedProps = updatedProps;
      this.removedProps = removedProps;
    }
  }

  class LayoutUpdated extends DBusSignal {
    public final UInt32 revision;
    public final int parent;

    public LayoutUpdated(String path, UInt32 revision, int parent) throws DBusException {
      super(path, revision, parent);

      this.revision = revision;
      this.parent = parent;
    }
  }

  class ItemActivationRequested extends DBusSignal {
    public final int id;
    public final UInt32 timestamp;

    public ItemActivationRequested(String path, int id, UInt32 timestamp) throws DBusException {
      super(path, id, timestamp);
      this.id = id;
      this.timestamp = timestamp;
    }
  }

  /**
   * Provides the layout and properties that are attached to the entries
   * that are in the layout.  It only gives the items that are children
   * of the item that is specified in {@code parentId}.  It will return all of the
   * properties or specific ones depending of the value in {@code propertyNames}.
   *
   * The format is recursive, where the second {@code v} is in the same format
   * as the original {@code a(ia{sv}av)}.  Its content depends on the value
   * of {@code recursionDepth}.
   *
   * @param parentId The ID of the parent node for the layout.  For grabbing the layout from the root node use zero.
   * @param recursionDepth The amount of levels of recursion to use.  This affects the content of the second variant array.
   * 				  - -1: deliver all the items under the {@code parentId}.
   * 				  - 0: no recursion, the array will be empty.
   * 				  - n: array will contains items up to 'n' level depth.
   * @param propertyNames The list of item properties we are interested in.  If there are no entries in the list all of the properties will be sent.
   * @return Pair of:
   *         * The revision number of the layout.  For matching with layoutUpdated signals.
   *         * The layout, as a recursive structure.
   */
  Pair<UInt32,Layout> GetLayout(int parentId, int recursionDepth, List<String> propertyNames);

  /**
   * Returns the list of items which are children of {@code parentId}.
   * @param ids A list of ids that we should be finding the properties on.  If the list is empty, all menu items should be sent.
   * @param propertyNames The list of item properties we are interested in.  If there are no entries in the list all of the properties will be sent.
   * @return An array of property values. An item in this area is represented as a struct following this format:
   *         <ul>
   *           <li>{@code id} unsigned the item id</li>
   *           <li>{@code properties} map(string => variant) the requested item properties</li>
   *         </ul>
   */
  List<UpdatedProperties> GetGroupProperties(List<Integer> ids, List<String> propertyNames);

  Variant<?> GetProperty(int id, String name);

  void Event(int id, String eventId, Variant<?> data, UInt32 timestamp);

  List<Integer> EventGroup(List<Event> events);

  boolean AboutToShow(int id);

  Pair<List<Integer>, List<Integer>> AboutToShowGroup(List<Integer> ids);
}
