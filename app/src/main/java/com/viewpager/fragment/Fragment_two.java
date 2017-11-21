package com.viewpager.fragment;

import com.main.functionlistsdemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_two extends Fragment{
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	 View view  = inflater.inflate(R.layout.activity_vp_fragment_lv, null);
    	return view;
    }
}
