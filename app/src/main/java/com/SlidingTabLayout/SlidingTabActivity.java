package com.SlidingTabLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.SlidingTabLayout.libs.SlidingTabLayout;
import com.main.functionlistsdemo.R;
import java.util.ArrayList;

/**
 * Created by ZJHL on 2016/11/4.
 */

public class SlidingTabActivity extends AppCompatActivity {

    ViewPager viewPager;
    SlidingTabLayout mSlidingTabLayout;

    private String[] title = new String[]{"谦虚","诚信","勤奋","孝顺"};
    //创建 颜色数组 用来做viewpager的背景
    int[] colors = {0xFF123456, 0xFF654321, 0xFF336699, 0xFF123456};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tab);

        //创建adapter
        MyAdapter adapter = new MyAdapter();
        viewPager.setAdapter(adapter);
        //SlidingTabLayout绑定viewpager

        //设置slidingTabLayout的相关参数
        mSlidingTabLayout.setSelectedIndicatorColors(Color.parseColor("#ff0000"));
        mSlidingTabLayout.setViewPager(viewPager);
    }

    /**
     * 构造pagerAdapter数据
     */
    class MyAdapter extends PagerAdapter{

        ArrayList<LinearLayout> lists = new ArrayList<>();

        /**
         * 设置viewpager的背景颜色数据
         */
        public MyAdapter(){
            for (int i = 0; i< 4; i++){
                LinearLayout ll = new LinearLayout(SlidingTabActivity.this);
                ll.setBackgroundColor(colors[i]);
                ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                lists.add(ll);
            }
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(lists.get(position));
            return lists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        /**
         * 将标题数组设置到title上
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
