package com.zxing.code;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;

import me.dm7.barcodescanner.core.ViewFinderView;


/**
 * 自定义扫描匡的样式
 * Created by pengfeili on 16/8/14.
 */
public class CustomViewFinderView extends ViewFinderView {
    /**
     * 扫描框下面文字描述
     */
    public final Paint mScannerDescPaint = new Paint();

    public CustomViewFinderView(Context context) {
        super(context);
        init();
    }

    public CustomViewFinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initTextDescPaint();
        setSquareViewFinder(true);
        int blueColor = Color.parseColor("#00FF00");
        int color = Color.parseColor("#00FF00");
        setBorderColor(blueColor);
        setLaserColor(color);
    }

    /**
     * 初始化textDescPaint
     */
    private void initTextDescPaint() {
        mScannerDescPaint.setColor(Color.WHITE);
        mScannerDescPaint.setAntiAlias(true);
        float textPixelSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                42, getResources().getDisplayMetrics());
        mScannerDescPaint.setTextSize(textPixelSize);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawScannerDesc(canvas);
    }

    @Override
    public synchronized void updateFramingRect() {
        super.updateFramingRect();
        Rect framingRect = super.getFramingRect();
        if (framingRect != null) {
            int height = framingRect.height();
            int top = 278;
            framingRect.top = top;
            framingRect.bottom = top + height;
        }
    }

    /**
     * 绘制 扫描匡的描述文字
     *
     * @param canvas
     */
    private void drawScannerDesc(Canvas canvas) {
        Rect framingRect = getFramingRect();

        float tradeMarkTop;
        if (framingRect != null) {
            tradeMarkTop = framingRect.bottom + mScannerDescPaint.getTextSize() + 50;
        } else {
            tradeMarkTop = 50;
        }
        mScannerDescPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("将二维码放入框内，即可自动扫描", canvas.getWidth() / 2, tradeMarkTop, mScannerDescPaint);
    }
}
