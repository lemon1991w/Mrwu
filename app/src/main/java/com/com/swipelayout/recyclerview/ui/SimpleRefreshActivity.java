package com.com.swipelayout.recyclerview.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.com.swipelayout.recyclerview.adapter.TestBaseAdapter;
import com.com.swipelayout.recyclerview.api.Constant;
import com.com.swipelayout.recyclerview.bean.TestData;
import com.diycoder.library.decoration.GridSpacingItemDecoration;
import com.diycoder.library.listener.RecyclerTouchListener;
import com.diycoder.library.listener.ScrollListener;
import com.main.functionlistsdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by ZJHL on 2016/9/30.
 */

public class SimpleRefreshActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout mRefreshLayout;
    RecyclerView mRecyclerView;

    private Activity mContext;
    private GridLayoutManager mLayoutManager;
    private TestBaseAdapter mAdapter;
    private List<TestData> data = new ArrayList<>();

    private int currnpage = 0;
    private RecyclerTouchListener recyclerTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_refresh_layout);
        ButterKnife.inject(this);
        mContext = this;

        initData();
        initView();
    }

    private void initData() {

        for (int i = 0; i<15; i++){
            TestData testData = new TestData();
            testData.icon = Constant.imags[i];
            testData.nick = Constant.nick[i];
            testData.msg = Constant.msg[i];
            testData.height = (int) (200 + Math.random() * 400);
            data.add(testData);
        }
    }

    private void initView() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.RefreshLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);

        mRefreshLayout.setOnRefreshListener(this);
        mLayoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1,12,false));

        mAdapter = new TestBaseAdapter(this);
        mAdapter.setDataList(data);
        mAdapter.setHasMoreData(true);

        mRecyclerView.setAdapter(mAdapter);
        //加载更多
        mRecyclerView.addOnScrollListener(new ScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore() {
                loadMore();
                currnpage++;
                Toast.makeText(mContext, "加载更多 第:" + currnpage +"页", Toast.LENGTH_SHORT).show();
            }
        });

        //添加item触摸监听
        recyclerTouchListener = new RecyclerTouchListener(mContext,mRecyclerView);
        recyclerTouchListener.setIndependentViews(R.id.rowButton).setViewsToFade(R.id.rowButton).setClickable(new RecyclerTouchListener.OnRowClickListener() {
            @Override
            public void onRowClicked(int position) {
                Toast.makeText(mContext,"item: "+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onIndependentViewClicked(int independentViewID, int position) {

            }
        })
                .setSwipeOptionViews(R.id.start,R.id.thumb,R.id.favorite)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
            @Override
            public void onSwipeOptionClicked(int viewID, int position) {
                  String message = "";
                if (viewID == R.id.start){
                    message += "收藏";
                }else if (viewID == R.id.thumb){
                    message += "点赞";
                }else if (viewID == R.id.favorite){
                    message += "喜欢";
                }
                Toast.makeText(mContext,message +" "+ position,Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.addOnItemTouchListener(recyclerTouchListener);
    }

   private  void loadMore(){
       //模拟加载更多数据
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               if (currnpage == 2){
                   mAdapter.setHasMoreDataAndFooter(false,true);
                   return;
               }
               initData();
               mAdapter.setDataList(data);
               ScrollListener.setLoadMore(!ScrollListener.loadMore);
           }
       },1000);
   }

    @Override
    public void onRefresh() {
        //避免刷新过快
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 mRefreshLayout.setRefreshing(false);
             }
         },1000);
    }
}
