package org.gtk.appindicator;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface LibAppIndicator extends Library {
  Pointer app_indicator_new_with_path(String id, String icon_name, int/*AppIndicatorCategory*/ category, String icon_theme_path);
  void    app_indicator_set_status         (Pointer self, int/*AppIndicatorStatus*/ status);
  void    app_indicator_set_icon           (Pointer self, String icon_name);
  void    app_indicator_set_attention_icon (Pointer self, String icon_name);
  void    app_indicator_set_menu           (Pointer self, Pointer menu);

  static LibAppIndicator getInstance() {
    try {
      return Native.load("libappindicator3.so", LibAppIndicator.class);
    } catch (Exception e) {
      return Native.load("libappindicator3.so.1", LibAppIndicator.class);
    }
  }
}
