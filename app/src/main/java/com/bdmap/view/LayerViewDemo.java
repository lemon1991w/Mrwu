package com.bdmap.view;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.main.functionlistsdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class LayerViewDemo extends Activity {

	private BMapManager manager;
	private MapView mapView;
	private MapController controller;
	
	/*
	 * 地图图层：修改mapView显示信息
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initManager();
		setContentView(R.layout.common_mapview);
		initView();
	}

	private void initView() {
		mapView =(MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		controller = mapView.getController();
		controller.setZoom(12);
	}

	private void initManager() {
		manager = new BMapManager(this);
		manager.init(Constants.KEY,new MKGeneralListener() {

			@Override
			public void onGetPermissionState(int iError) {

			}

			@Override
			public void onGetNetworkState(int iError) {

			}
		});
	}

	//按键事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_1:
				mapView.setTraffic(false);
				mapView.setSatellite(false);
				//底图
				break;
			case KeyEvent.KEYCODE_2:
				//实时交通
				mapView.setSatellite(false);
				mapView.setTraffic(true);
				break;
			case KeyEvent.KEYCODE_3:
				//卫星图
				mapView.setSatellite(true);
				mapView.setTraffic(false);
				break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
