package org.gtk.appindicator;

import com.sun.jna.DefaultTypeMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.EnumConverter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface LibAppIndicator extends Library {
  GAppIndicator app_indicator_new_with_path      (String id, String icon_name, AppIndicatorCategory category, String icon_theme_path);
  void          app_indicator_set_status         (GAppIndicator self, AppIndicatorStatus status);
  void          app_indicator_set_icon           (GAppIndicator self, String icon_name);
  void          app_indicator_set_attention_icon (GAppIndicator self, String icon_name);
  void          app_indicator_set_menu           (GAppIndicator self, Pointer menu);

  Map<String, Object> OPTIONS = Collections.unmodifiableMap(new HashMap<String, Object>() {
    {
      put(Library.OPTION_TYPE_MAPPER, new DefaultTypeMapper() {
        {
          addTypeConverter(AppIndicatorCategory.class, new EnumConverter<>(AppIndicatorCategory.class));
          addTypeConverter(AppIndicatorStatus.class, new EnumConverter<>(AppIndicatorStatus.class));
        }
      });
    }
  });


  static LibAppIndicator getInstance() {
    try {
      return Native.load("libappindicator3.so", LibAppIndicator.class, OPTIONS);
    } catch (UnsatisfiedLinkError e) {
      return Native.load("libappindicator3.so.1", LibAppIndicator.class, OPTIONS);
    }
  }
}
