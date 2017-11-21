package com.date.and.nimaiton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.base.BaseActivity;
import com.date.and.nimaiton.nimation.AnimationActivity;
import com.date.and.nimaiton.time.TimeActivity;
import com.main.functionlistsdemo.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL on 2016/9/23.
 */

public class MainListActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.btn_select_time)
    Button btnSelectTime;
    @InjectView(R.id.btn_select_animation)
    Button btnSelectAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_and_animation);
        setupActionBar("Mr wu", false, null, R.drawable.arrow_back, false, null, R.drawable.ic_launcher);
        ButterKnife.inject(this);
        initEvent();
    }

    private void initEvent() {
    btnSelectTime.setOnClickListener(this);
    btnSelectAnimation.setOnClickListener(this);
}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btn_select_time:
                EventBus.getDefault().post("2");
                startActivity(TimeActivity.class);
                overridePendingTransition(R.anim.animation2_in_new,R.anim.animation2_in_current);
                break;
            case R.id.btn_select_animation:
                startActivity(AnimationActivity.class);
                overridePendingTransition(R.anim.animation3_in_new, R.anim.animation3_in_current1);
                break;
        }
    }

    public void startActivity(Class clazz){
        Intent i = new Intent(this,clazz);
        startActivity(i);
    }

}
