package com.viewpager.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.custom.XListView;
import com.custom.XListView.IXListViewListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.main.functionlistsdemo.R;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 第一个Fragment
 * @author admin
 *
 */

public class Fragment_one extends Fragment implements IXListViewListener{

	//	private PullToRefreshListView listView;
//	private ListView listView;
	private XListView listView;
	List<FragmentDataBean> list;
	private Handler mHanlder;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = null;
		mHanlder = new Handler();
		if (view == null) {
			list = new ArrayList<FragmentDataBean>();
			if (list.size() == 0) {
				view = inflater.inflate(R.layout.activity_vp_fragment_lv, container,false);
				listView =(XListView) view.findViewById(R.id.list_view);
				listView.setXListViewListener(this);
				listView.setPullLoadEnable(false);
				container.setTag(view);
			}else{
			}
		}
		FragmentDataBean dataBean1 = new FragmentDataBean(R.drawable.fragment1,"宝宝，儿童节快乐！","2016.6.1");
		FragmentDataBean dataBean2 = new FragmentDataBean(R.drawable.fragment2,"你是我的女猪脚","Every moment");
		FragmentDataBean dataBean3 = new FragmentDataBean(R.drawable.fragment3,"三人行","2016...");
		FragmentDataBean dataBean4 = new FragmentDataBean(R.drawable.fragment4,"最美不是下雨天 是与你躲过雨的屋檐","2020.2.2");
		FragmentDataBean dataBean5 = new FragmentDataBean(R.drawable.fragment6,"我听见海浪的生意 站在城市的最中央","2016.7.7(农历)");
//    	  FragmentDataBean dataBean6 = new FragmentDataBean(R.drawable.fragment5,"森林狼-快准狠","2016.8.8");
//    	  FragmentDataBean dataBean6 = new FragmentDataBean(R.drawable.fragment6,"我听见海浪的生意 站在城市的最中央","2016.7.7(农历)");
		list.add(dataBean1);
		list.add(dataBean2);
		list.add(dataBean3);
		list.add(dataBean4);
		list.add(dataBean5);
//    	  list.add(dataBean6);

		FragmentOneAdapter adapter = new FragmentOneAdapter(getActivity(), list);
		adapter.notifyDataSetChanged();
		listView.setAdapter(adapter);
		initXListViewTime();
		return view;
	}

	private void initXListViewTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String updataTime = sdf.format(new Date(System.currentTimeMillis()));
		listView.setRefreshTime(updataTime);
	}

	@Override
	public void onRefresh() {
		mHanlder.postDelayed(new Runnable() {

			@Override
			public void run() {
				//可以下拉刷新，一秒后关闭
				listView.setPullLoadEnable(true);
				listView.stopRefresh();
				initXListViewTime();
			}
		}, 1000);
	}

	@Override
	public void onLoadMore() {
		mHanlder.postDelayed(new Runnable() {

			@Override
			public void run() {
				//可以下拉刷新，一秒后关闭
				listView.setPullLoadEnable(false);
			}
		}, 1000);
	}
}
