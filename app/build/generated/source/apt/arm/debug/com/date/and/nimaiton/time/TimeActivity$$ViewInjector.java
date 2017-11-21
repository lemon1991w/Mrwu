// Generated code from Butter Knife. Do not modify!
package com.date.and.nimaiton.time;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class TimeActivity$$ViewInjector {
  public static void inject(Finder finder, final com.date.and.nimaiton.time.TimeActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493079, "field 'selectTime'");
    target.selectTime = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493080, "field 'select_date'");
    target.select_date = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493078, "field 'tvTime'");
    target.tvTime = (android.widget.TextView) view;
  }

  public static void reset(com.date.and.nimaiton.time.TimeActivity target) {
    target.selectTime = null;
    target.select_date = null;
    target.tvTime = null;
  }
}
