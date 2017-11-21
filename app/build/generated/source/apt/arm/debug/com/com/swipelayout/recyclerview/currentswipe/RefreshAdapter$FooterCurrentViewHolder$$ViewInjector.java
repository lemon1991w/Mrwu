// Generated code from Butter Knife. Do not modify!
package com.com.swipelayout.recyclerview.currentswipe;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RefreshAdapter$FooterCurrentViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.com.swipelayout.recyclerview.currentswipe.RefreshAdapter.FooterCurrentViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493170, "field 'mPbLoad'");
    target.mPbLoad = (android.widget.ProgressBar) view;
    view = finder.findRequiredView(source, 2131493171, "field 'mTvLoadText'");
    target.mTvLoadText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493169, "field 'mLoadLayout'");
    target.mLoadLayout = (android.widget.LinearLayout) view;
  }

  public static void reset(com.com.swipelayout.recyclerview.currentswipe.RefreshAdapter.FooterCurrentViewHolder target) {
    target.mPbLoad = null;
    target.mTvLoadText = null;
    target.mLoadLayout = null;
  }
}
