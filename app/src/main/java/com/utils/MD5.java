package com.utils;

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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ZJHL on 2016/11/1.
 */

public class MD5 {

    /**
     * md5
     */
    public static String md5(String str) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        //转成十六进制字符串
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}

//package com.senior.custom.view;
//
//        import android.animation.ObjectAnimator;
//        import android.content.Context;
//        import android.graphics.Bitmap;
//        import android.graphics.BitmapFactory;
//        import android.graphics.Canvas;
//        import android.graphics.Color;
//        import android.graphics.Paint;
//        import android.graphics.RectF;
//        import android.support.annotation.Nullable;
//        import android.util.AttributeSet;
//        import android.view.View;
//
//        import com.main.functionlistsdemo.R;
//        import com.utils.Utils;
//
///**
// * Created by wusourece on 2017/11/10.
// *   自定义view 三
// */
//
//public class CustomViewThree extends View {
//
//    Paint paint2;
//    Bitmap bitmap;
//
//    final float radius = Utils.dpToPixel(80);  //画环形进度条
//    RectF arcRectF = new RectF();
//    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//    // TODO 为 progress 添加 getter 和 setter 方法（setter 方法记得加 invalidate()）
//    float progress = 0;
//
//    {
//        paint.setTextSize(Utils.dpToPixel(40));
//        paint.setTextAlign(Paint.Align.CENTER);
//    }
//
//    public CustomViewThree(Context context) {
//        super(context);
//        init();
//    }
//    public CustomViewThree(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//    public CustomViewThree(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    public void init(){
//        paint2 = new Paint();
//        paint2.setColor(Color.parseColor("#ff0000"));
//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mmm);
//
//    }
//
//    public void setValues(float values){
//        this.progress = values;
//        invalidate();
//    }
//
//    public float getValues(){
//        return progress;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.save();
////        canvas.clipRect(200,120,750,670); //需要裁剪的范围 长 从left到right 从top到bottom的长度
////        canvas.translate(200,200);
////        canvas.rotate(15,50,50);
////        canvas.drawBitmap(bitmap,200,100,paint);
////        canvas.restore();
//
//
//        float centerX = getWidth() / 2;
//        float centerY = getHeight() / 2;
//
//        paint.setColor(Color.parseColor("#E91E63"));
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStrokeWidth(Utils.dpToPixel(15));
//        arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
//        canvas.drawArc(arcRectF, 90, progress * 3, false, paint);
//
//        paint.setColor(Color.WHITE);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawText((int) progress + "%", centerX, centerY - (paint.ascent() + paint.descent()) / 2, paint);
//
//    }
//
//}

