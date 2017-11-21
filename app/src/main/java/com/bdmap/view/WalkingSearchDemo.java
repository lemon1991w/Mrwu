package com.bdmap.view;

import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKRoutePlan;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import android.os.Bundle;
import android.widget.Toast;

/**
 * 步行的路线
 * @author admin
 *
 */

public class WalkingSearchDemo extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DrivingSearch();
	}

	private void DrivingSearch() {
		search = new MKSearch();
		listener = new mySearch();
		search.init(manager, listener);

		MKPlanNode start = new MKPlanNode();
		start.pt = hypos;

		MKPlanNode end = new MKPlanNode();
		end.pt= new GeoPoint(40065796, 116349868);

		search.walkingSearch("北京", start, "北京", end);

	}

	private class mySearch extends myMKListenerAdapter{
		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult result,
											int iError) {

			if (result != null && iError == 0) {
				if (result.getNumPlan()>0) {
					MKRoutePlan plan = result.getPlan(0);
					RouteOverlay overlay = new RouteOverlay(WalkingSearchDemo.this, mapView);
					overlay.setData(plan.getRoute(0));
					mapView.getOverlays().add(overlay);
					mapView.refresh();
				}
			}else{
				Toast.makeText(WalkingSearchDemo.this, "结果有误！", 0).show();
			}
			super.onGetWalkingRouteResult(result, iError);
		}
	}
}
