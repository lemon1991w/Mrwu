package com.date.and.nimaiton.nimation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.main.functionlistsdemo.R;

/**
 * Created by ZJHL on 2016/9/27.
 */

public class ThreeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setBackgroundColor(Color.parseColor("#FF00FF"));
        setContentView(ll);
    }

    //处理后退键的情况
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){

            this.finish();  //finish当前activity
            overridePendingTransition(R.anim.animation2_in_shangxia,
                    R.anim.animation2_shangxia);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
