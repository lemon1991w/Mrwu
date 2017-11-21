// Generated code from Butter Knife. Do not modify!
package com.imitation.ios.dialog;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ImitationIosDialogActivity$$ViewInjector {
  public static void inject(Finder finder, final com.imitation.ios.dialog.ImitationIosDialogActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493035, "field 'btnPhoto'");
    target.btnPhoto = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131492991, "field 'btnDialog'");
    target.btnDialog = (android.widget.Button) view;
  }

  public static void reset(com.imitation.ios.dialog.ImitationIosDialogActivity target) {
    target.btnPhoto = null;
    target.btnDialog = null;
  }
}
