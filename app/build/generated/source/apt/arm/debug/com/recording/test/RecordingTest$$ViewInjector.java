// Generated code from Butter Knife. Do not modify!
package com.recording.test;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RecordingTest$$ViewInjector {
  public static void inject(Finder finder, final com.recording.test.RecordingTest target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493052, "field 'btnRecording'");
    target.btnRecording = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493051, "field 'btnPlay'");
    target.btnPlay = (android.widget.Button) view;
  }

  public static void reset(com.recording.test.RecordingTest target) {
    target.btnRecording = null;
    target.btnPlay = null;
  }
}
