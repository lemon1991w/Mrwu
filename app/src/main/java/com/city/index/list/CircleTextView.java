package com.city.index.list;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by on 16/12/10.
 * 触摸右边的字母列表后的提示文字
 */
public class CircleTextView extends TextView {
    private Paint mBgPaint = new Paint();

    //有些地方不能用paint的，就直接给canvas加抗锯齿
    private PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleTextView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //决定了view的大小 取更大的那个值
        int measureWidth = getMeasuredWidth();
        int measureHeight = getMeasuredHeight();
        int max = Math.max(measureWidth,measureHeight);
        setMeasuredDimension(max,max);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setDrawFilter(pfd);
        mBgPaint.setColor(Color.parseColor("#2F2F4F"));
        //cx cy radius paint
        // 宽和高都是/2  让绘制的圆形显示在中间 半径也是中心位置
        canvas.drawCircle(getWidth()/2, getHeight()/2 , Math.max(getWidth() / 2 , getHeight()) / 2, mBgPaint);
        super.draw(canvas);

    }
}
