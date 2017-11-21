package com.screen.shot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.base.BaseActivity;
import com.main.functionlistsdemo.R;

/**
 * Created by wusourece on 2017/7/18.
 *  长图截取
 */

public class ScreenShotActivity extends BaseActivity {

    Button mScreenShot;
    ScrollView scrollView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);
         scrollView = (ScrollView) findViewById(R.id.scrollView);
        mScreenShot = (Button) findViewById(R.id.btn_screen_shot);

        initView();
    }

    private void initView() {
        mScreenShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenUtils.getBitmapByView(scrollView);
            }
        });
    }
}
