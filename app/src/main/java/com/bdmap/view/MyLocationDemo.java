package com.bdmap.view;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import android.os.Bundle;

/**
 * 定位
 * @author admin
 *
 */

public class MyLocationDemo extends BaseActivity{

	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		location();
	}

	private void location() {
		mLocationClient = new LocationClient(getApplicationContext());
		myListener = new MyLocation();


		LocationClientOption  option  = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all"); //返回的定位结果包含地址
		option.setCoorType("bd091");//返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);//禁止启用缓存定位
		option.setPoiNumber(5);//最多返回poi个数
		option.setPoiDistance(1000);//poi查询距离
		option.setPoiExtraInfo(true); // 是否需要poi的电话和地址等信息

		mLocationClient.setLocOption(option);

		mLocationClient.registerLocationListener(myListener);
	}

	@Override
	protected void onResume() {
		mLocationClient.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		mLocationClient.stop();
		super.onPause();
	}

	private class MyLocation implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;

			double latitude = location.getLatitude();
			double longitude2 = location.getLongitude();


			MyLocationOverlay overlay = new MyLocationOverlay(mapView);

			LocationData data = new LocationData();
			data.latitude = latitude;
			data.longitude = longitude2;
			overlay.setData(data);
			mapView.getOverlays().add(overlay);
			mapView.refresh();

			//移动地图操作
			mapController.animateTo(new GeoPoint((int)(longitude*1E6),(int)(latitude*1E6)));
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {

		}

	}

}
