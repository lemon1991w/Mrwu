package com.LoveCurve;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.main.functionlistsdemo.R;

/**
 * Created by ZJHL on 2016/12/30.
 * 爱心的贝赛尔曲线
 */

public class LoveCurveActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn_start;
    private LoveLayout mLoveLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_love_curve);
         initView();
    }

    private void initView() {
        mBtn_start = (Button) findViewById(R.id.id_btn_start);
        mBtn_start.setOnClickListener(this);

        mLoveLayout = (LoveLayout) findViewById(R.id.LoveLayout);
    }

    @Override
    public void onClick(View v) {
        mLoveLayout.addLove();
    }
}
