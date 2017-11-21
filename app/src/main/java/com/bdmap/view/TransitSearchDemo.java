package com.bdmap.view;

import com.baidu.mapapi.map.TransitOverlay;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKTransitRoutePlan;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import android.os.Bundle;
import android.widget.Toast;

/**
 * 公交换乘
 * @author admin
 *
 */

public class TransitSearchDemo extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		transitSearch();
	}

	private void transitSearch() {
		search = new MKSearch();
		listener = new MySearch();
		search.init(manager, listener);

		MKPlanNode start = new MKPlanNode();
		start.pt = hypos;

		MKPlanNode end = new MKPlanNode();
		end.pt = new GeoPoint(40065796, 116349868);
		search.setTransitPolicy(MKSearch.EBUS_WALK_FIRST);//少步行

		search.transitSearch("北京", start, end);
	}

	private class MySearch extends myMKListenerAdapter{

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult result,
											int iError) {
			//数据处理
			if (result != null && iError == 0) {
				if (result.getNumPlan() > 0 ) {

					MKTransitRoutePlan plan = result.getPlan(0);
					TransitOverlay overlay = new TransitOverlay(TransitSearchDemo.this, mapView);
					overlay.setData(plan);
					mapView.getOverlays().add(overlay);
					mapView.refresh();
				}
			}else{
				Toast.makeText(TransitSearchDemo.this, "结果有误！", 0).show();
			}

			super.onGetTransitRouteResult(result, iError);
		}
	}
}
