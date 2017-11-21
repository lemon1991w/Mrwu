package com.date.and.nimaiton.nimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.main.functionlistsdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL on 2016/9/23.
 *
 * activity启动,动画切换
 */

public class AnimationActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.button2)
    Button button2;
    @InjectView(R.id.button3)
    Button button3;
    @InjectView(R.id.button4)
    Button button4;
    @InjectView(R.id.button5)
    Button button5;

    private int [] ids = new int[]{R.id.button,R.id.button2,R.id.button3,R.id.button4,R.id.button5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_layout);
        for (int i: ids){
            findViewById(i).setOnClickListener(this);
        }
        ButterKnife.inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case   R.id.button:
                startActivity(OneActivity.class);
                //第1个参数表示：将要启动的activity的动画，第二个参数表示：当前activity的结束动画
                overridePendingTransition(R.anim.animation0_in_new,R.anim.animation0_in_current);
                break;
            case   R.id.button2:
                startActivity(ThreeActivity.class);
                overridePendingTransition(R.anim.animation1_in_new,R.anim.animation1_in_current);
                break;
            case   R.id.button3:
                startActivity(OneActivity.class);
                overridePendingTransition(R.anim.animation2_in_new, R.anim.animation2_in_current);
                break;
            case   R.id.button4:
                startActivity(OneActivity.class);
                overridePendingTransition(R.anim.animation3_in_new, R.anim.animation3_in_current1);
                break;
            case   R.id.button5:
                startActivity(TwoActivity.class);
                overridePendingTransition(R.anim.animation3_in_new, R.anim.animation3_in_current1);
                break;
            default:
                break;
        }
    }

    private void startActivity(Class clazz){
        startActivity(new Intent(this,clazz));
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
