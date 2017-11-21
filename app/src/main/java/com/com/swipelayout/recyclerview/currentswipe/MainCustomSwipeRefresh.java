package com.com.swipelayout.recyclerview.currentswipe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.Toast;

import com.main.functionlistsdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL 巫维庭 on 2016/10/11.
 */

public class MainCustomSwipeRefresh extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.swipeRefreshLayout_custom)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @InjectView(R.id.recyclerView_custom)
    RecyclerView mRecyclerView;

    List<String> mDatas= new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;


    RefreshAdapter mRefreshAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_swipe_refresh);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initView(){
        //给下拉圈设置颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        mRefreshAdapter = new RefreshAdapter(this,mDatas);
        mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        //添加动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        LoadMore();
    }

    private void initData() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    for (int i= 1; i<6; i++){
                        mDatas.add("第"+i+"个数据");
                    }
                    mRecyclerView.setAdapter(mRefreshAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

//    private void PullRefresh() {
//
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        List<String> headDatas = new ArrayList<String>();
//
//                            for (int i = 0; i < 10; i++) {
//
//                                headDatas.add("Heard Item " + i);
//                                mRefreshAdapter.AddHeaderItem(headDatas);
//                        }
//
//                        //刷新完成
//                        mSwipeRefreshLayout.setRefreshing(false);
//                        Toast.makeText(MainCustomSwipeRefresh.this, "加载了 "+headDatas.size()+" 条目数据", Toast.LENGTH_SHORT).show();
//                    }
//
//                }, 2000);
//
//            }
//        });
//    }

    private void LoadMore() {

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem ;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的item时才加载
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==mRefreshAdapter.getItemCount()){

                    //设置正在加载更多
                    mRefreshAdapter.changeMoreStatus(mRefreshAdapter.LOADING_MORE);

                    //改为网络请求
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            List<String> footerDatas = new ArrayList<String>();
                            for (int i = 1; i< 6; i++) {

                                footerDatas.add("footer  item" + i);
                            }
                            mRefreshAdapter.AddFooterItem(footerDatas);
                            //设置回到上拉加载更多
                            mRefreshAdapter.changeMoreStatus(mRefreshAdapter.PULLUP_LOAD_MORE);
                            //没有加载更多了
                            //mRefreshAdapter.changeMoreStatus(mRefreshAdapter.NO_LOAD_MORE);
                            Toast.makeText(MainCustomSwipeRefresh.this, "上拉加载 "+footerDatas.size()+" 条目数据", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的item
                lastVisibleItem=layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onRefresh() {
        mDatas.clear();
        //模拟数据，在项目开发中，此下拉请求服务器加载数据
        initData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },1500);
            }
        });
    }
}
