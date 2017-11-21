package com.banner.change;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.banner.change.bannerlibrary.BannerPagerAdapter;
import com.main.functionlistsdemo.R;

import java.util.List;

/**
 * Created by ZJHL on 2016/10/18.
 */

public class BannerAdapter extends BannerPagerAdapter {

    private Context mContext;
    private List<Integer> data;

    public BannerAdapter(Context context, List data) {
        super(context, data);
        this.mContext = context;
        this.data = data;
    }

    @Override
    public View setView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.banner_item, null);
        ImageView iv = (ImageView) v.findViewById(R.id.iv_item_banner);
        iv.setImageResource(data.get(position));
        return v;
    }
}