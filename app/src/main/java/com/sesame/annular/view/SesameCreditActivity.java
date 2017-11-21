package com.sesame.annular.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.base.BaseActivity;
import com.main.functionlistsdemo.R;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL on 2017/3/9.
 * 芝麻信用的环形view
 */

public class SesameCreditActivity extends AppCompatActivity{

    private final int[] mColors = new int[] {
            0xFFFF80AB,
            0xFFFF4081,
            0xFFFF5177,
            0xFFFF7997
    };

    @InjectView(R.id.layout)
    RelativeLayout layout; //整体布局
    @InjectView(R.id.btn)
    ImageView ibPlay;    //点击播放芝麻信用度按钮
    @InjectView(R.id.sesame_view)
    SesameAnnularView sesameView;  //芝麻环形view

    private Random random = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_sesame_creadit);
//        setupActionBar("芝麻信用",true,null,R.drawable.icon_return,false,null,R.drawable.icon_return);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        layout.setBackgroundColor(mColors[0]);
        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = random.nextInt(950);
                sesameView.setSesameValues(i);
                startColorChangeAnim();
            }
        });
    }

    private void startColorChangeAnim(){
        ObjectAnimator animator = ObjectAnimator.ofInt(layout,"backgroundColor",mColors);
        animator.setDuration(3000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }
}
