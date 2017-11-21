// Generated code from Butter Knife. Do not modify!
package com.date.and.nimaiton;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainListActivity$$ViewInjector {
  public static void inject(Finder finder, final com.date.and.nimaiton.MainListActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493076, "field 'btnSelectTime'");
    target.btnSelectTime = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493077, "field 'btnSelectAnimation'");
    target.btnSelectAnimation = (android.widget.Button) view;
  }

  public static void reset(com.date.and.nimaiton.MainListActivity target) {
    target.btnSelectTime = null;
    target.btnSelectAnimation = null;
  }
}
