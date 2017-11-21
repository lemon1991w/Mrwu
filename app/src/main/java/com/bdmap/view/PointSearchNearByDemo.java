package com.bdmap.view;

import java.util.ArrayList;

import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;

import android.os.Bundle;
import android.widget.Toast;

/***
 * 在圆形区域内搜索
 * @author
 *
 */
public class PointSearchNearByDemo extends BaseActivity {

	private MKSearch search;
	private MKSearchListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		search();
	}

	private void search() {
		search = new MKSearch();  //类似button
		listener = new myMKSerch();
		search.init(manager, listener);    //设置监听

		//圆形区域的搜索
		search.poiSearchNearBy("加油站", hypos, 2000);
	}

	private class myMKSerch extends myMKListenerAdapter{
		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
			//数据处理
			if (result != null&& iError ==0) {
				//处理overlay
				PoiOverlay overlay = new PoiOverlay(PointSearchNearByDemo.this, mapView);
				ArrayList<MKPoiInfo> arrayList = result.getAllPoi();
				overlay.setData(arrayList);
				mapView.getOverlays().add(overlay);
				mapView.refresh();

			}else{
				Toast.makeText(PointSearchNearByDemo.this, "结果返回有误！", 0).show();
			}
			super.onGetPoiResult(result, type, iError);

		}
	}

}
