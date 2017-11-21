package com.com.swipelayout.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.com.swipelayout.recyclerview.bean.TestData;
import com.diycoder.library.adapter.BaseAdapter;
import com.diycoder.library.widget.RoundImageView;
import com.main.functionlistsdemo.R;

import java.util.Date;
import java.util.Random;

/**
 * Created by ZJHL on 2016/10/8.
 */

public class TestBaseAdapter  extends BaseAdapter<TestData,TestBaseAdapter.ItemViewHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TestBaseAdapter(Context context) {
        super(context);
        this.mContext = context;
       mLayoutInflater =  LayoutInflater.from(context);
    }

    // 填充布局，创建viewHolder
    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder itemViewHolder = new ItemViewHolder(mLayoutInflater.inflate(R.layout.recycleview_item1,parent,false));
        return itemViewHolder;
    }

    //填充布局
    @Override
    public void onBindItemViewHolder(ItemViewHolder holder, int position) {
           TestData data = getItemData(position);
        if (data != null){
            String url = data.icon;
            //使用glide加载用户头像
            Glide.with(mContext).load(url).centerCrop().into(holder.userIcon);
            holder.mainText.setText(data.nick);
            holder.subText.setText(data.msg);
            Date date = new Date();
            int i = new Random().nextInt(2);
            long hours = date.getHours() + i;
            int minutes = date.getMinutes() + i;

            holder.rowButton.setText(hours + ":" + minutes);
        }
    }

    //初始化viewholder
    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        private final TextView mainText;
        private final TextView subText;
        private final RoundImageView userIcon;
        private final TextView rowButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mainText = (TextView) itemView.findViewById(R.id.mainText);
            subText = (TextView) itemView.findViewById(R.id.subText);
            userIcon = (RoundImageView) itemView.findViewById(R.id.userIcon);
            rowButton = (TextView) itemView.findViewById(R.id.rowButton);
        }
    }
}

