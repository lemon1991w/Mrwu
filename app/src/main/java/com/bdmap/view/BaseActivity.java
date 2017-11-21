package com.bdmap.view;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.main.functionlistsdemo.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends Activity {

	protected static BMapManager manager;  //一个应用中存在一个即可
	protected MapView mapView;
	protected MapController mapController;
	
	protected int latitude = (int)(39.90735*1E6); //纬度
	protected int longitude = (int)(116.39125*1E6);//经度
	
    //设置地图的中心到航宇大厦(大厦的经纬度)
	protected GeoPoint hypos = new GeoPoint(latitude, longitude);
	
	protected MKSearch search;
	protected MKSearchListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//地图引擎初始化，发送key到百度服务器
		initMapManagerData();
		setContentView(R.layout.common_mapview);
		initView();
	}

	final void initView() {
		mapView = (MapView) findViewById(R.id.mapview);
		//在mapView里添加一组按钮
		//addView
        mapView.setBuiltInZoomControls(true);	
        
        //控制地图缩放，旋转，移动
        mapController = mapView.getController();
        float zoomLevel = 13;//缩放级别[3-19]
        mapController.setZoom(zoomLevel);
        mapController.setCenter(hypos);
        mapController.enableClick(true);
        
        MKMapViewListener listener = new MKMapViewListener() {
			
			@Override
			public void onMapMoveFinish() {
				
			}
			
			@Override
			public void onMapAnimationFinish() {
				
			}
			
			@Override
			public void onGetCurrentMap(Bitmap result) {
				  
			}
			
			@Override
			public void onClickMapPoi(MapPoi result) {
				
			}
		};		
	}

	private void initMapManagerData() {
		    if (manager == null) {
            manager = new BMapManager(this); 
            manager.init(Constants.KEY, new MKGeneralListener() {
				
				@Override
				public void onGetPermissionState(int iError) {
					if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
					Toast.makeText(BaseActivity.this, "网络错误！", 0).show();							
					}
				}
	 			
				@Override
				public void onGetNetworkState(int iError) {
					if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
					Toast.makeText(BaseActivity.this, "权限错误！", 0).show();						
					}
				}
			});
		    }
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
	 
	 protected class myMKListenerAdapter implements MKSearchListener{

		 /*
		  *     		if (result != null&& iError ==0) {
				
			}else{
				Toast.makeText(PointSearchNearByDemo.this, "结果返回有误！", 0).show();
			}
		  * */
		 
		@Override
		public void onGetAddrResult(MKAddrInfo result, int iError) {
			
		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult result, int iError) {
			
		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result, int iError) {
			
		}

		@Override
		public void onGetPoiDetailSearchResult(int result, int iError) {
			
		}

		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
			
		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult result, int iError) {
			
		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult result, int iError) {
			
		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult result, int iError) {
			
		}
		 
	 }
}
