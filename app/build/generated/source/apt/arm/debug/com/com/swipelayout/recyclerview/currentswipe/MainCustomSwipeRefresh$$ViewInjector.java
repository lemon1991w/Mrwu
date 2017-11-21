// Generated code from Butter Knife. Do not modify!
package com.com.swipelayout.recyclerview.currentswipe;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainCustomSwipeRefresh$$ViewInjector {
  public static void inject(Finder finder, final com.com.swipelayout.recyclerview.currentswipe.MainCustomSwipeRefresh target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493025, "field 'mSwipeRefreshLayout'");
    target.mSwipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout) view;
    view = finder.findRequiredView(source, 2131493026, "field 'mRecyclerView'");
    target.mRecyclerView = (android.support.v7.widget.RecyclerView) view;
  }

  public static void reset(com.com.swipelayout.recyclerview.currentswipe.MainCustomSwipeRefresh target) {
    target.mSwipeRefreshLayout = null;
    target.mRecyclerView = null;
  }
}
