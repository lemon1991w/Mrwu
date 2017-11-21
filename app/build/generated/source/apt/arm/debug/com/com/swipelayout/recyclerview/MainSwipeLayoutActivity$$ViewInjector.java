// Generated code from Butter Knife. Do not modify!
package com.com.swipelayout.recyclerview;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainSwipeLayoutActivity$$ViewInjector {
  public static void inject(Finder finder, final com.com.swipelayout.recyclerview.MainSwipeLayoutActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493073, "field 'customHeaderAndFooter'");
    target.customHeaderAndFooter = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493074, "field 'btnHeader'");
    target.btnHeader = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493075, "field 'btnCustomHader'");
    target.btnCustomHader = (android.widget.Button) view;
  }

  public static void reset(com.com.swipelayout.recyclerview.MainSwipeLayoutActivity target) {
    target.customHeaderAndFooter = null;
    target.btnHeader = null;
    target.btnCustomHader = null;
  }
}
