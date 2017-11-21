package com.senior.custom.view;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.main.functionlistsdemo.R;
import com.utils.ToastUtil;

/**
 * Created by wusourece on 2017/11/10.
 * 自定义view 三
 */

public class CustomViewThreeActivity extends AppCompatActivity {

    private int flag = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_three);
        initView();
    }

    private void initView() {
        final ImageView imageView = (ImageView) findViewById(R.id.iv_mmm);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(CustomViewThreeActivity.this, "imageView");
            }
        });
        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag) {  //让imageView进行平移 每次移动后加1 执行不同的平移操作 等于总数后重置标记 重新开始
                    case 0:
                        ObjectAnimator objectAnimatorRight = ObjectAnimator.ofFloat(imageView, "translationX", 500);
                        objectAnimatorRight.setDuration(1200);
                        objectAnimatorRight.start();
                        break;
                    case 1:
                        ObjectAnimator objectAnimatorLeft = ObjectAnimator.ofFloat(imageView, "translationX", 0);
                        objectAnimatorLeft.setDuration(1200);
                        objectAnimatorLeft.start();
                        break;
                    case 2:
                        ObjectAnimator objectAnimatorBottom = ObjectAnimator.ofFloat(imageView, "translationY",500);
                        objectAnimatorBottom.setDuration(1200);
                        objectAnimatorBottom.start();
                        break;
                    case 3:
                        ObjectAnimator objectAnimatorTop = ObjectAnimator.ofFloat(imageView, "translationY",0);
                        objectAnimatorTop.setDuration(1200);
                        objectAnimatorTop.start();
                        break;
                }
                flag++;
                if (flag == 4){
                    flag =0;
                }
            }
        });
    }
}
