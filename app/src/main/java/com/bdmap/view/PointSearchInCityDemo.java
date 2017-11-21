package com.bdmap.view;

import java.util.ArrayList;

import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 全局搜索(翻页)
 * @author admin
 *
 */

public class PointSearchInCityDemo extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		search();
	}

	private void search() {
		search = new MKSearch();
		listener = new myMkSearch();
		search.init(manager, listener);

		search.poiSearchInCity("北京", "加油站");
	}

	private class myMkSearch extends myMKListenerAdapter{
		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
			if (result != null && iError == 0) {

				PoiOverlay overlay = new PoiOverlay(PointSearchInCityDemo.this, mapView);
				ArrayList<MKPoiInfo> allPoi = result.getAllPoi(); //一页
				overlay.setData(allPoi);
				mapView.getOverlays().add(overlay);
				mapView.refresh();
			}else{
				Toast.makeText(PointSearchInCityDemo.this, "结果有误！", 0).show();
			}
			super.onGetPoiResult(result, type, iError);
		}
	}
}
