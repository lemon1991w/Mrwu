package com.com.swipelayout.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.com.swipelayout.recyclerview.currentswipe.MainCustomSwipeRefresh;
import com.com.swipelayout.recyclerview.ui.CustomHeaderActivity;
import com.com.swipelayout.recyclerview.ui.SimpleRefreshActivity;
import com.main.functionlistsdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL on 2016/9/30.
 */

public class MainSwipeLayoutActivity extends Activity implements View.OnClickListener {
    @InjectView(R.id.custom_header_and_footer)
    Button customHeaderAndFooter;
    @InjectView(R.id.btn_header)
    Button btnHeader;
    @InjectView(R.id.btn_custom_hader)
    Button btnCustomHader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_swipelayout);
        ButterKnife.inject(this);
        initEvent();
    }

    private void initEvent() {
        customHeaderAndFooter.setOnClickListener(this);
        btnHeader.setOnClickListener(this);
        btnCustomHader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case    R.id.custom_header_and_footer:
                startIntent(MainCustomSwipeRefresh.class);
                break;
            case    R.id.btn_header:
                startIntent(SimpleRefreshActivity.class);
                break;
            case   R.id.btn_custom_hader:
                startIntent(CustomHeaderActivity.class);
                break;
        }
    }

    public void startIntent(Class clazz){
        startActivity(new Intent(this,clazz));
    }
}
