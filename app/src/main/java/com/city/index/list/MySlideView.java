package com.city.index.list;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.main.functionlistsdemo.R;

/**
 * Created by on 16/7/26.
 * 城市的字母快速列表
 */
public class MySlideView extends View {


    public interface onTouchListener {
        void showTextView(String textView, boolean dismiss);
    }

    private onTouchListener listener;

    public void setListener(onTouchListener listener) {
        this.listener = listener;
    }


    private int mWidth, mHeight, mTextHeight, position;
    private Paint paint;
    private Rect mBound;
    private int backgroundColor;
    private int yDown, yMove, mTouchSlop;
    private boolean isSlide;
    private String selectTxt;

    public MySlideView(Context context) {
        this(context, null);
    }

    public MySlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        mBound = new Rect();
        backgroundColor = getResources().getColor(R.color.font_info);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(backgroundColor);
        canvas.drawRect(0, 0, (float) mWidth, mHeight, paint);
        for (int i = 0; i < CityIndexListActivity.pinyinList.size(); i++) {
            String textView = CityIndexListActivity.pinyinList.get(i);
            if (i == position - 1) {
                //按下时显示的文字
                paint.setColor(getResources().getColor(R.color.error_color));
                selectTxt = CityIndexListActivity.pinyinList.get(i);
                listener.showTextView(selectTxt, false);
            } else {
                //默认时显示的文字
                paint.setColor(getResources().getColor(R.color.white));
            }
            paint.setTextSize(40);
            //将文本放入矩形中 从start的end的范围位置
            paint.getTextBounds(textView, 0, textView.length(), mBound);

            //绘制文本 view的宽度减去矩形的宽 / 2  让文本居中显示
            canvas.drawText(textView, (mWidth - mBound.width()) / 2, mTextHeight - mBound.height(), paint);

            mTextHeight += mHeight / CityIndexListActivity.pinyinList.size();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        int y = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                yDown = y;
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = y;
                int dy = yMove - yDown;
                //如果是竖直方向滑动
                if (Math.abs(dy) > mTouchSlop) {
                    isSlide = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                backgroundColor = getResources().getColor(R.color.font_text);
                mTextHeight = mHeight / CityIndexListActivity.pinyinList.size();
                position = y / (mHeight / (CityIndexListActivity.pinyinList.size() + 1));
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isSlide) {
                    backgroundColor = getResources().getColor(R.color.font_text);
                    mTextHeight = mHeight / CityIndexListActivity.pinyinList.size();
                    position = y / (mHeight / CityIndexListActivity.pinyinList.size() + 1) + 1;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                backgroundColor = getResources().getColor(R.color.font_info);
                mTextHeight = mHeight / CityIndexListActivity.pinyinList.size();
                position = 0;
                invalidate();
                listener.showTextView(selectTxt, true);
                break;
        }
        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = widthSize * 1 / 2;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = heightSize * 1 / 2;
        }
        mWidth = width;
        mHeight = height;
        mTextHeight = mHeight / CityIndexListActivity.pinyinList.size();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility != VISIBLE) {
            mTextHeight = mHeight / CityIndexListActivity.pinyinList.size();
        }
    }
}
