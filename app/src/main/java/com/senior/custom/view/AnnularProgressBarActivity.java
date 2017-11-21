package com.senior.custom.view;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.main.functionlistsdemo.R;
import com.utils.ToastUtil;

import java.util.Random;

/**
 * Created by wusourece on 2017/11/16.
 */

public class AnnularProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annular_progress_bar);
        init();
    }

    int min = 60;
    int max = 360;

    private void init() {
        final CustomAnnularProgressBar  progressBar = (CustomAnnularProgressBar) findViewById(R.id.progressBar);
        findViewById(R.id.btn_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int s = random.nextInt(max)%(max-min+1) + min;

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(progressBar,"progressParams",0,s);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
            }
        });
    }
}
