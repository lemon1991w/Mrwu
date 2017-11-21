package com.bdmap.view;

import java.util.List;

import com.baidu.mapapi.map.Geometry;
import com.baidu.mapapi.map.Graphic;
import com.baidu.mapapi.map.GraphicsOverlay;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.Symbol;

import android.app.Activity;
import android.os.Bundle;

/*
 * 几何图形
 * 覆盖物的操作
 * 1.利用mapView获取到覆盖物的集合
 * 2.创建自己的覆盖物信息，添加到集合中
 * 3.更新mapView
 *
 * 重点：覆盖物的数据设置
 * */
public class GraphicsOverlayDemo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawGraphics();
    }

    private void drawGraphics() {
        List<Overlay> overlays = mapView.getOverlays();
        GraphicsOverlay overlay = new GraphicsOverlay(mapView);

        setData(overlay);

        overlays.add(overlay);
        mapView.refresh();
    }

    private void setData(GraphicsOverlay overlay) {
        // 设置数据，1.定义几何图形  圆形+半径
        //      2.颜色，线宽
        //      3.设置数据
        Geometry geometry = new Geometry();
        geometry.setCircle(hypos, 1000);  //圆形+半径

        Symbol symbol = new Symbol();

        Symbol.Color color = symbol.new Color();
        color.red= 255;
        color.blue = 0;
        color.green=0;
        color.alpha = 100;

        symbol.setSurface(color, 1, 0);

        overlay.setData(new Graphic(geometry, symbol));
    }
}
