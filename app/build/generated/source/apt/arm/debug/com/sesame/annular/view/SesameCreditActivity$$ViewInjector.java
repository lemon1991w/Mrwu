// Generated code from Butter Knife. Do not modify!
package com.sesame.annular.view;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SesameCreditActivity$$ViewInjector {
  public static void inject(Finder finder, final com.sesame.annular.view.SesameCreditActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492989, "field 'layout'");
    target.layout = (android.widget.RelativeLayout) view;
    view = finder.findRequiredView(source, 2131492991, "field 'ibPlay'");
    target.ibPlay = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131492990, "field 'sesameView'");
    target.sesameView = (com.sesame.annular.view.SesameAnnularView) view;
  }

  public static void reset(com.sesame.annular.view.SesameCreditActivity target) {
    target.layout = null;
    target.ibPlay = null;
    target.sesameView = null;
  }
}
