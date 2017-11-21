package com.senior.custom.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.main.functionlistsdemo.R;
import com.utils.Utils;

/**
 * Created by wusourece on 2017/11/10.
 *   自定义view 三
 */

public class CustomViewThree extends View {

    Paint paint2;
    Bitmap bitmap;


    public CustomViewThree(Context context) {
        super(context);
        init();
    }
    public CustomViewThree(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CustomViewThree(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        paint2 = new Paint();
        paint2.setColor(Color.parseColor("#ff0000"));
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mmm);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        canvas.clipRect(200,120,750,670); //需要裁剪的范围 长 从left到right 从top到bottom的长度
//        canvas.translate(200,200);
        canvas.rotate(15,50,50);
        canvas.drawBitmap(bitmap,200,100,paint2);
        canvas.restore();

    }

}
