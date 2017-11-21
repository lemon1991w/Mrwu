package com.bdmap.view;

import java.util.List;

import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.Symbol.Color;
import com.baidu.mapapi.map.Geometry;
import com.baidu.mapapi.map.Symbol;
import com.baidu.mapapi.map.TextItem;
import com.baidu.mapapi.map.TextOverlay;

import android.graphics.Typeface;
import android.os.Bundle;

/*
 * 文字绘制
 * */
public class TextOverlayDemo extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawText();
    }

    private void drawText() {
        List<Overlay> overlays = mapView.getOverlays();

        TextOverlay overlay = new TextOverlay(mapView);
        setData(overlay);
        overlays.add(overlay);
        mapView.refresh();
    }

    private void setData(TextOverlay overlay) {
        TextItem item = new TextItem();
        item.align = TextItem.ALIGN_CENTER;
        item.fontColor = getColor();
        item.fontSize = 40;
        item.pt = hypos;
        item.text = "中南海保镖";
        item.typeface = Typeface.DEFAULT_BOLD;
        overlay.addText(item);
    }

    private Color getColor() {
        Geometry geometry = new Geometry();
        geometry.setCircle(hypos, 1000);  //圆形+半径

        Symbol symbol = new Symbol(); //颜色样式

        Symbol.Color color = symbol.new Color();
        color.red= 255;
        color.blue = 0;
        color.green=0;
        color.alpha = 150;

        return color;
    }
}
