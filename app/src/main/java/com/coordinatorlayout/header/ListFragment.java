package com.coordinatorlayout.header;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.main.functionlistsdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJHL on 2016/10/25.
 */

public class ListFragment extends BaseFragment {
    RecyclerView mRecyclerView;
    private static final String KEY="key";
    private String title="测试";

    List<String> mDatas=new ArrayList<>();
    private ItemAdapter mAdapter;

    public static ListFragment newInstance(String title){
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        Bundle arguments = getArguments();
        if(arguments!=null){
            title=arguments.getString(KEY);
        }
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);

        for(int i=0;i<30;i++){
            String s = String.format("我是第"+i+"个" + title);
            mDatas.add(s);
        }
        mAdapter = new ItemAdapter(mContext, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void fetchData() {
    }
}
