package com.city.index.list;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

import com.main.functionlistsdemo.R;

/**
 * Created by  on 2016/12/27.
 * item间的索引条
 */

public class StickyDecoration extends RecyclerView.ItemDecoration {


    private TextPaint textPaint;
    private Paint paint;
    private int topHeight;

    public StickyDecoration(Context context) {
        Resources res = context.getResources();

        /**
         * 初始化索引条
         */
        paint = new Paint();
        paint.setColor(res.getColor(R.color.red_226));
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(60);
        textPaint.setColor(Color.WHITE);
        topHeight = res.getDimensionPixelSize(R.dimen.top);

    }

    /**
     * 获取条目的position  给不同拼音的城市首字母 设置对应view的top间距 来展示索引条
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //获取当前屏幕可见孩子view的position
        int position = parent.getChildAdapterPosition(view);

        /**
         *   如果首字母的组里面 当前position是0的话 返回true 设置itemView之间的间距
         *   如果不是0的话 比较相近的两个position位置上的首字母是否一样 一样的话返回false 设置间距为0 其实就是不设置view之间的间距
         *       反之   返回true 设置view的top 间距为topHeight 让它有间距可以显示索引条
         */
        if (isFirstInGroup(position)) {
            outRect.top = topHeight;
        } else {
            outRect.top = 0;
        }

    }

    private boolean isFirstInGroup(int position) {
        boolean isFirst;
        if (position == 0) {
            isFirst = true;
        } else {
            //获取当前拼音字母集合中的位置的首字母去比较 上一个汉字的首字母 如果是一样的话返回false 不是一样就返回true
            if (CityIndexListActivity.cityList.get(position).getFirstPinYin().equals(CityIndexListActivity.cityList.get(position - 1).getFirstPinYin())) {
                isFirst = false;
            } else {
                isFirst = true;
            }
        }
        return isFirst;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight(); //当前的宽减去padding

        //获得child的总数
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            //遍历得到当前的view
            View view = parent.getChildAt(i);
            //得到当前view的位置
            int position = parent.getChildAdapterPosition(view);
            //拿到当前位置的拼音首字母
            String textLine = CityIndexListActivity.cityList.get(position).getFirstPinYin();
            //如果当前position等于0
            if (isFirstInGroup(position)) {
                //通过当前view.getTop() - topHeight 拿到要绘制矩形 索引条的顶部
                float top = view.getTop()-topHeight;
                // 通过view.getTop() 就拿到了矩形的bottom  就是要从top画到bottom  bottom就是两个控件的边界
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);//绘制红色矩形
                c.drawText(textLine, left + 30, bottom - 30, textPaint);//绘制文本
            }
        }
    }

    /**
     * 此方法和onDraw类同，不过触发时机不同，onDraw是在绘制childView之前触发，而它是完成后触发，
     * 用于绘制滚动时的红色矩形
     * 停留在x y 起始 0的位置
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
        c.drawRect(left, 0, parent.getWidth(), topHeight, paint);//绘制停留的红色矩形
        //获取
        String text = CityIndexListActivity.cityList.get(position).getFirstPinYin();
        c.drawText(text, 30, topHeight - 30, textPaint);//绘制文本
    }

}

