package com.senior.custom.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.main.functionlistsdemo.R;

/**
 * Created by wusourece on 2017/11/7.
 *
 *    paint 图像的处理
 */

public class CustomViewTwo extends View {

    Paint paint;
    Paint paint2;
    Paint paint3;

    public CustomViewTwo(Context context) {
        super(context);
        init();
    }

        public CustomViewTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
            init();
    }

    public CustomViewTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){

        //用着色器进行圆形绘制
        paint = new Paint();
        Shader shader = new LinearGradient(100,100,500,500, Color.parseColor("#ffff00"),Color.parseColor("#ff0000"),Shader.TileMode.CLAMP);
        paint.setShader(shader);

        paint2 = new Paint();
        Shader shader2 = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint2.setShader(shader2);

        paint3 = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mmm);
        Shader shader3 = new BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        paint3.setShader(shader3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(100,100,80,paint);
        canvas.drawCircle(300, 300, 200, paint2);
        canvas.drawCircle(100,100,80,paint3);



    }
}
