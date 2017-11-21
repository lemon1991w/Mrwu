package com.bdmap.view;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.main.functionlistsdemo.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class HelloWorld extends Activity {

	private BMapManager manager;
	private MapView mapView;
	private MapController mapController;

	private int latitude = (int)(40.051*1E6); //纬度
	private int longitude = (int)(116.303*1E6);//经度

	//设置地图的中心到航宇大厦(大厦的经纬度)
	private GeoPoint hypos = new GeoPoint(latitude, longitude);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//地图引擎初始化，发送key到百度服务器
		initMapManagerData();
		setContentView(R.layout.common_mapview);
		initView();
	}

	private void initView() {
		mapView = (MapView) findViewById(R.id.mapview);
		//在mapView里添加一组按钮
		//addView
		mapView.setBuiltInZoomControls(true);

		//控制地图缩放，旋转，移动
		mapController = mapView.getController();
		float zoomLevel = 13;//缩放级别[3-19]
		mapController.setZoom(zoomLevel);

		mapController.setCenter(hypos);
		MKMapViewListener listener = new MKMapViewListener() {

			@Override
			public void onMapMoveFinish() {

			}

			@Override
			public void onMapAnimationFinish() {

			}

			@Override
			public void onGetCurrentMap(Bitmap arg0) {

			}

			@Override
			public void onClickMapPoi(MapPoi arg0) {

			}
		};
	}

	private void initMapManagerData() {
		manager = new BMapManager(this);
		manager.init(Constants.KEY, new MKGeneralListener() {

			@Override
			public void onGetPermissionState(int iError) {
				if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
					Toast.makeText(HelloWorld.this, "网络错误！", 0).show();
				}
			}

			@Override
			public void onGetNetworkState(int iError) {
				if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
					Toast.makeText(HelloWorld.this, "权限错误！", 0).show();
				}
			}
		});
	}

	//有一部分方法需要与activity的生命周期绑定在一起，否则与mapView使用会出现问题
	@Override
	protected void onResume() {
		mapView.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		mapView.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		mapView.destroy();
		super.onDestroy();
	}

	//按键事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_1:
				//地图移动
				GeoPoint yxPos = new GeoPoint(40065796, 116349868);
				mapController.animateTo(yxPos);
				break;
			case KeyEvent.KEYCODE_2:
				//放大
				mapController.zoomIn();
				break;
			case KeyEvent.KEYCODE_3:
				//缩小
				mapController.zoomOut();
				break;
			case KeyEvent.KEYCODE_4:
				//旋转
				int Rotation = mapView.getMapRotation();
				Rotation+=20;
				mapController.setRotation(Rotation);
				break;
			case KeyEvent.KEYCODE_5:
				//俯角改变
				int looking = mapView.getMapOverlooking();
				looking -=5;
				mapController.setOverlooking(looking);
				break;
			default:
				break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
