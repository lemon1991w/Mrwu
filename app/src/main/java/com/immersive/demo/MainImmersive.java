package com.immersive.demo;

import com.custom.XListView;
import com.main.functionlistsdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * 沉浸式状态栏
 * @author admin
 *
 */

public class MainImmersive extends Activity{

	private XListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_inmersive);
		View topView = findViewById(R.id.tv_title);
		listView = (XListView) findViewById(R.id.list_view);
		ImmersedStatusbarUtils.initStatusBar(this, topView);
		//自己重写状态栏，把当前的类和需要改变的状态栏样式(View)传给Utils类
	}
}
