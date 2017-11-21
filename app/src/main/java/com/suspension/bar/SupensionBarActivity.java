package com.suspension.bar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.functionlistsdemo.MainActivity;
import com.main.functionlistsdemo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ZJHL on 2017/2/9.
 * 悬浮条的recycleView

 */

public class SupensionBarActivity extends Activity {

    private RecyclerView mFeedList;  //recycleView
    private RelativeLayout mSuspensionBar;  // bar 条控件
    private TextView mSuspensionTv;  //  名字
    private ImageView mSuspensionIv;  //  头像
    private int mCurrentPosition = 0;  //  当前位置

    private int mSuspensionHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supension_bar);

        mSuspensionBar = (RelativeLayout) findViewById(R.id.suspension_bar);
        mSuspensionIv = (ImageView) findViewById(R.id.iv_avatar);
        mSuspensionTv = (TextView) findViewById(R.id.tv_nickname);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                if (item.getItemId() == R.id.item_jump){
////                    Intent intent = new Intent(MainActivity.this, MultiActivity.class);
////                    startActivity(intent);
//                }
//                return false;
//            }
//        }); 小青3 大伯母1 小城3 爸爸2


        final LinearLayoutManager ll = new LinearLayoutManager(this);
        FeedAdapter adapter = new FeedAdapter();

        mFeedList = (RecyclerView) findViewById(R.id.feed_list);
        mFeedList.setLayoutManager(ll);
        mFeedList.setAdapter(adapter);
        mFeedList.setHasFixedSize(true);

        mFeedList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight =  mSuspensionBar.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //寻找子view
                View view = ll.findViewByPosition(mCurrentPosition+1);
                if (view != null){
                    if (view.getTop() <= mSuspensionHeight){
                        //如果当前view小于bar的高度的话，就隐藏，反之则显示在Y 0的位置
                        mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
                    }else {
                        mSuspensionBar.setY(0);
                    }
                }

                //  判断当前第一个显示的position
                if (mCurrentPosition != ll.findFirstVisibleItemPosition()){
                    //获取当前第一个显示的position 并进行赋值
                    mCurrentPosition = ll.findFirstVisibleItemPosition();
                    System.out.println("===== mCurrentPosition"+mCurrentPosition);
                     //让bar停留在第一个的位置
                    mSuspensionBar.setY(0);
                     // 再去加载bar上对应的头像内容
                    updateSuspensionBar();
                }
            }
        });
    }

    /**
     * 更新条的显示
     */
    private void updateSuspensionBar() {
        Picasso.with(SupensionBarActivity.this)
                .load(getAvatarResId(mCurrentPosition))
                .centerInside()
                .fit()
                .into(mSuspensionIv);
        if (mCurrentPosition == 0){
            mSuspensionTv.setText("Mr wu ");
        }else {
            mSuspensionTv.setText("Mr wu " + mCurrentPosition);
        }
    }

    public int getAvatarResId(int position){

        switch (position % 4){
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
        }
        return 0;
    }
}
//
