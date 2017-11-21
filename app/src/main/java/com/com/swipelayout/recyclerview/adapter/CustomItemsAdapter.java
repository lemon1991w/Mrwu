package com.com.swipelayout.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.main.functionlistsdemo.R;

import java.util.List;

/**
 * Created by ZJHL on 2016/10/9.
 */

public class CustomItemsAdapter extends RecyclerView.Adapter<CustomItemsAdapter.ItemHolder> implements View.OnClickListener {

    private List<String> mData;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    private onItemClickListener mOnItemClickListener = null;

    public CustomItemsAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public CustomItemsAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.item_list,parent,false);
        ItemHolder itemHolder = new ItemHolder(view);
        //给view注册一个监听事件
        view.setOnClickListener(this);
          return itemHolder;
    }

    public void onBindViewHolder(ItemHolder holder, int position) {
          String string = mData.get(position);
         ItemHolder itemHolder =  holder;
        itemHolder.item.setText(string);
        //把当前position通过tag设置进去
        itemHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
         if (mOnItemClickListener != null){
             mOnItemClickListener.onItemClick(v, (int) v.getTag());
         }
    }

    //给调用者暴露一个方法
    public void setOnItemClickListener(onItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface onItemClickListener{
         void onItemClick(View v,int position);
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        TextView item;

        public ItemHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item);
        }
    }
}
