// Generated code from Butter Knife. Do not modify!
package com.camera;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CameraActivity$$ViewInjector {
  public static void inject(Finder finder, final com.camera.CameraActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493001, "field 'photoAlbum'");
    target.photoAlbum = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493000, "field 'takePhoto'");
    target.takePhoto = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493002, "field 'picture'");
    target.picture = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131493003, "field 'call'");
    target.call = (android.widget.Button) view;
  }

  public static void reset(com.camera.CameraActivity target) {
    target.photoAlbum = null;
    target.takePhoto = null;
    target.picture = null;
    target.call = null;
  }
}
