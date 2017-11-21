package com.date.and.nimaiton.nimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.main.functionlistsdemo.R;

/**
 * Created by Administrator on 2016/8/19.
 */
public class OneActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout=new LinearLayout(this);
        layout.setBackgroundResource(android.R.color.holo_green_dark);
        setContentView(layout);

    }

    //处理后退键的情况
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){

            this.finish();  //finish当前activity
            overridePendingTransition(R.anim.animation_out_old_right,
                    R.anim.animation_out_current_right);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
