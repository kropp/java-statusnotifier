package org.gtk.appindicator;

import org.freedesktop.IStatusNotifierItem;

public enum AppIndicatorCategory {
  APPLICATION_STATUS,
  COMMUNICATIONS,
  SYSTEM_SERVICES,
  HARDWARE,
  OTHER;

  public static AppIndicatorCategory from(IStatusNotifierItem.Category category) {
    // they are actually the same
    return AppIndicatorCategory.values()[category.ordinal()];
  }
}
