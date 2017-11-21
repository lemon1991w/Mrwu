package com.bdmap.view;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.main.functionlistsdemo.R;

public class ItemizedOverlayDemo extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		drawItemized();
		initPop();
	}

	private void initPop() {

	}

	private void drawItemized() {
		mapController.setZoom(17);
		List<Overlay> overlays = mapView.getOverlays();

		ItemizedOverlay<OverlayItem> overlay = new ItemizedOverlay<OverlayItem>(getResources().getDrawable(R.drawable.ic_launcher), mapView){
			//获得item点击事情，根据对应的位置响应
			@Override
			protected boolean onTap(int index) {
				OverlayItem item = getItem(index);
				String title = item.getTitle();
				Toast.makeText(ItemizedOverlayDemo.this, "您选择的是"+title+"店...", 0).show();

				return super.onTap(index);
			}
		};
		overlays.add(overlay);

		setData(overlay);

		mapView.refresh();
	}

	private void setData(ItemizedOverlay<OverlayItem> overlay) {
		OverlayItem item = new OverlayItem(hypos, "广集天下美食", "好吃你就多吃点...");
		overlay.addItem(item);

		item = new OverlayItem(new GeoPoint(latitude + 1000, longitude), "向北", "增加纬度");
		overlay.addItem(item);

		item = new OverlayItem(new GeoPoint(latitude, longitude + 1000), "向东", "增加纬度");
		overlay.addItem(item);

		item = new OverlayItem(new GeoPoint(latitude - 1000, longitude - 1000), "向西南", "减少经纬度");
		overlay.addItem(item);


	}
}
