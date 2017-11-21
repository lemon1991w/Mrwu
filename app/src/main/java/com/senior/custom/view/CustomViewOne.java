package com.senior.custom.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.main.functionlistsdemo.R;

/**
 * Created by wusourece on 2017/11/6.
 * 自定义一
 */

@SuppressLint("AppCompatCustomView")
public class CustomViewOne extends View{

    Paint paint;
    Paint rectPaint;
    Paint pointPaint;
    Paint linePaint;

    Paint greenPaint;
    Paint redPaint;
    Paint yellowPaint;

    Paint textPaint;

    public CustomViewOne(Context context) {
        super(context);
        init();
    }

    public CustomViewOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomViewOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE); //圆的画笔
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.red));

        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.STROKE); //正方形的画笔
        rectPaint.setStrokeWidth(5);
        rectPaint.setAntiAlias(true);

        pointPaint = new Paint();
        pointPaint.setStrokeWidth(150);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        pointPaint.setColor(getResources().getColor(R.color.red));
        pointPaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setStrokeWidth(10);
        linePaint.setColor(getResources().getColor(R.color.red));

        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#b87333"));
        textPaint.setTextSize(40);

        greenPaint = new Paint();  //扇形
        greenPaint.setStyle(Paint.Style.FILL);
        greenPaint.setColor(Color.parseColor("#32cd32"));

        redPaint = new Paint();
        redPaint.setStyle(Paint.Style.FILL);
        redPaint.setColor(Color.parseColor("#ff0000"));

        yellowPaint = new Paint();
        yellowPaint.setStyle(Paint.Style.FILL);
        yellowPaint.setColor(Color.parseColor("#ffff00"));

    }

    int x = 90;
    int y = 90;
    int radius = 80;

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i<2;i++){
            canvas.drawCircle(x,y,radius,paint);
            x+=45;
            y+=60;
            radius+=10;
        }
        canvas.drawRect(250,50,550,350,rectPaint);  //从left开始到right 从top 到bottom
        canvas.drawPoint(700,150,pointPaint);   //从x到y 点显示的位置
        canvas.drawLine(100,400,600,600,linePaint);  //线的开始和结束位置
        canvas.drawText("我们不一样",700,550,textPaint);

        //useCenter 是否要连接到中心点 不连接的话是直的 连接的话是尖的  startAngle 开始位置 sweepAngle 弧形需要绘制的角度
//        canvas.drawArc(100,600,400,900,-180,120,true,arcPaint);
        //useCenter 是否要连接到中心点 不连接的话是直的 连接的话是尖的  startAngle 开始位置 从右边的中间开始 左边为负 右边为正 sweepAngle 弧形需要绘制角度的多少
        // 饼状图
        canvas.drawArc(200,600,800,1200,10,160,true,yellowPaint);
        canvas.drawArc(200,600,800,1200,170,90,true,redPaint);
        canvas.drawArc(200,600,800,1200,290,70,true,greenPaint);


//        canvas.drawColor(Color.parseColor("#88880000"));  //界面遮罩
    }
}
