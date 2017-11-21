package com.bdmap.view;

import java.util.ArrayList;

import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.mapapi.search.MKRoutePlan;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKWpNode;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import android.os.Bundle;
import android.widget.Toast;
/**
 * 驾车
 * @author
 *
 */

public class DrivingSearchDemo extends BaseActivity{
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

//		    search.drivingSearch("北京", start, "北京", end);

		MKWpNode wpNode = new MKWpNode();
		wpNode.city="北京";
		wpNode.name="龙泽地铁";

		ArrayList<MKWpNode> list = new ArrayList<MKWpNode>();
		list.add(wpNode);

		search.drivingSearch("北京", start, "北京", end, list);
	}

	private class mySearch extends myMKListenerAdapter{
		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result,
											int iError) {

			if (result != null && iError == 0) {

				if (result.getNumPlan()>0) {

					MKRoutePlan plan = result.getPlan(0);

					//路线
					RouteOverlay overlay = new RouteOverlay(DrivingSearchDemo.this, mapView);

					MKRoute data = plan.getRoute(0);
					overlay.setData(data);
					mapView.getOverlays().add(overlay);
					mapView.refresh();
				}
			}else{
				Toast.makeText(DrivingSearchDemo.this, "结果有误！", 0).show();
			}

			super.onGetDrivingRouteResult(result, iError);
		}
	}
}
