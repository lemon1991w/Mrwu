package com.com.swipelayout.recyclerview.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.com.swipelayout.recyclerview.adapter.CustomItemsAdapter;
import com.main.functionlistsdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJHL on 2016/10/9.
 */

public class CustomHeaderActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private List mData;
    private CustomItemsAdapter mAdapter;

    LinearLayoutManager layoutManager;

    List<String> mDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_layout);

        initView();
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.greenyellow,R.color.red,R.color.green);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mData = new ArrayList();
        loadHeaderData();

    }

    private void loadHeaderData() {

       new Thread(){
           @Override
           public void run() {
               super.run();
               try {

                   for (int i= 0; i<20; i++){
                       mData.add("第"+i+"个数据");
                   }

                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           setAdapter();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                }
                            },2500);
                       }
                   });
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
       }.start();
    }

    private final String jumpData = "第6个数据";

    private void setAdapter(){
        if(mAdapter==null){
            mAdapter = new CustomItemsAdapter(this,mData);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new CustomItemsAdapter.onItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {

                    if (position == 6){
                        startActivity(new Intent(CustomHeaderActivity.this,SimpleRefreshActivity.class));
                        Toast.makeText(CustomHeaderActivity.this,"第: "+ position +"个",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(CustomHeaderActivity.this,"第: "+ position +"个",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
         mData.clear();
        loadHeaderData();
    }
}
