package com.coordinatorlayout.header;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.main.functionlistsdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJHL on 2016/10/25.
 */

public class CoordinatorLayoutActivity extends AppCompatActivity {

     List<Fragment> mFragments;

    String[] mTitls = new String[]{
            "主页",
            "空间",
            "相册",
            "遇见"
    };

    ViewPager mViewPager;
    TabLayout mTabLayout;
    FloatingActionButton fab;

    ListFragment listFragment;
    int lastVisibleItem;

    RecyclerView mRecylerView;
    ItemAdapter itemAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        // 第一步，初始化ViewPager和TabLayout
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        setupViewPager();
    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        for (int i = 0;i < mTitls.length;i++){
            listFragment = ListFragment.newInstance(mTitls[i]);
            mFragments.add(listFragment);
        }

        // 第二步：为ViewPager设置适配器
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(),mFragments,mTitls);
        mViewPager.setAdapter(adapter);

        //将viewpager与Tablayout进行绑定
        mTabLayout.setupWithViewPager(mViewPager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CoordinatorLayoutActivity.this, "follow me!", Toast.LENGTH_SHORT).show();
            }
        });

//        ListFragment mlistFragment = new ListFragment();
//        mRecylerView = mlistFragment.getRecyclerView();
//        itemAdapter= mlistFragment.getItemAdapter();

//        mRecylerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState  == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == itemAdapter.getItemCount()){
//                    Log.i("===newState",""+newState);
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                //最后一个可见的item
//                lastVisibleItem=layoutManager.findLastVisibleItemPosition();
//            }
//        });
    }
}
