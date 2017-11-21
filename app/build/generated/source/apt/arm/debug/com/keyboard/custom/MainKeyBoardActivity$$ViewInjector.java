// Generated code from Butter Knife. Do not modify!
package com.keyboard.custom;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainKeyBoardActivity$$ViewInjector {
  public static void inject(Finder finder, final com.keyboard.custom.MainKeyBoardActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493036, "field 'btnCustomKeyboard'");
    target.btnCustomKeyboard = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493037, "field 'btnPayKeyboard'");
    target.btnPayKeyboard = (android.widget.Button) view;
  }

  public static void reset(com.keyboard.custom.MainKeyBoardActivity target) {
    target.btnCustomKeyboard = null;
    target.btnPayKeyboard = null;
  }
}
