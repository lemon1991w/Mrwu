package com.date.and.nimaiton.time;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.date.and.nimaiton.utils.DataCallBack;
import com.date.and.nimaiton.utils.DataTimeHelp;
import com.main.functionlistsdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL on 2016/9/23.
 */

public class TimeActivity extends Activity implements View.OnClickListener, DataCallBack {

    @InjectView(R.id.select_time)
    Button selectTime;
    @InjectView(R.id.select_date)
    Button select_date;
    @InjectView(R.id.tv_time)
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_layout);

        ButterKnife.inject(this);
        initEvent();
    }

    private void initEvent() {
        selectTime.setOnClickListener(this);
        select_date.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(String message){
        tvTime.setText(message);
        Log.e("===== message ",message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.select_time:
                  DataTimeHelp.showTime(this,this);
                break;
            case R.id.select_date:
                DataTimeHelp.showDate(this,this);
                break;
        }
    }

    @Override
    public void getData(String date) {
        //如果用textview展示数据，需要用String.formt格式下字符串
            tvTime.setText(String.format(date));
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
