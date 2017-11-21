package com.senior.custom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.utils.Utils;

/**
 * Created by wusourece on 2017/11/16.
 */

public class CustomAnnularProgressBar extends View {

    RectF rectF = new RectF();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float progress = 0;

    float radius = Utils.dpToPixel(80);

    Paint textPaint = new Paint();

    public void setProgressParams(float progress) {
        this.progress = progress;
        invalidate();  //接收到参数后要通知onDraw重绘界面
    }

    public float getProgressParams() {
        return progress;
    }


    public CustomAnnularProgressBar(Context context) {
        super(context);
    }

    public CustomAnnularProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAnnularProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2; //得到屏幕的高宽
        float centerY = getHeight() / 2;

        paint.setStyle(Paint.Style.STROKE); //设置环形paint的属性
        paint.setStrokeCap(Paint.Cap.ROUND); //设置开始的地方是圆头
        paint.setStrokeWidth(Utils.dpToPixel(15));
        paint.setColor(Color.parseColor("#ff0000"));

        //设置环形的显示的位置 在屏幕的居中位置
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(rectF, -90, progress+0f, false, paint);

        textPaint.setTextSize(Utils.dpToPixel(40));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);
        int newProgress = (int) (progress/360*100);
        canvas.drawText(newProgress + "%", centerX, centerY, textPaint);  //得到当前进度的百分比
    }
}
