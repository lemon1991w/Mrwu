package com.viewpager.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class VpAdapter extends FragmentPagerAdapter{
	private List<Fragment> list;
	@SuppressWarnings("unused")
	private  FragmentManager fm;
	ArrayList<String> lists=new ArrayList<String>();
	

	public List<Fragment> getList() {
		return list;
	}

	public void setList(List<Fragment> list) {
		this.list = list;
	}
	

	public VpAdapter(FragmentManager fm) {
		super(fm);
		this.fm=fm;
		
	}
	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		 lists.add(makeFramentName(container.getId(),(int)getItemId(position)));
		return super.instantiateItem(container, position);
	}
	public static  String makeFramentName(int view , int index){
        return "android:switcher:"+view+":"+index;
    }

}
