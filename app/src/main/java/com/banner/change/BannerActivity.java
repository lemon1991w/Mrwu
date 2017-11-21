package com.banner.change;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.banner.change.bannerlibrary.Banner;
import com.banner.change.bannerlibrary.BannerPagerAdapter;
import com.main.functionlistsdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJHL on 巫维庭 2016/10/18.
 */

public class BannerActivity extends AppCompatActivity {

    Banner banner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        List<Integer> data = new ArrayList<>();
        //要创建多少view就往data集合中添加，添加完成后会通过banner.setAdapter传递给帧布局进行循环绘制
        data.add(R.drawable.fragment3);
        data.add(R.drawable.fragment2);
        data.add(R.drawable.fragment4);
        data.add(R.drawable.fragment5);
        data.add(R.drawable.fragment6);

        BannerAdapter adapter = new BannerAdapter(this,data);
        
        banner = (Banner) findViewById(R.id.banner);
        /**
         * 在设置了小圆点之后才能设置适配器，得先有数据才能进行适配
         * 最后需要调用开始轮播
         *  在onPause()/onDestroy()方法中来停viewpager的循环播放 减少内存消耗 onResume时又自动开启轮播
         */
            banner.setDot(R.drawable.no_selected_dot,R.drawable.selected_dot).
                setDotGravity(Banner.CENTER).
                setAdapter(adapter).
                setOnItemClickListener(new BannerPagerAdapter.onItemClickListener() {
                    @Override
                    public void onClick(int position) {
                    if (position == 0){
                        Toast.makeText(BannerActivity.this, "路漫漫其修远兮，吾将上下而求索", Toast.LENGTH_SHORT).show();
                    }else if (position == 1){
                        Toast.makeText(BannerActivity.this, "稻花香里说丰年，听取蛙声一片", Toast.LENGTH_SHORT).show();
                    }else if (position == 2){
                        Toast.makeText(BannerActivity.this, "竹外桃花三两枝，春江水暖鸭先知", Toast.LENGTH_SHORT).show();
                    }else if (position == 3){
                        Toast.makeText(BannerActivity.this, "月落乌啼算漫天，江枫渔火对愁眠", Toast.LENGTH_SHORT).show();
                    }else if (position == 4){
                        Toast.makeText(BannerActivity.this, "远上寒山石径斜，白云深处有人家", Toast.LENGTH_SHORT).show();
                      }
                    }
                }).
                startAutoPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.stopAutoPlay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }
}
