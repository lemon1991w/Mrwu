package com.com.swipelayout.recyclerview.currentswipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.main.functionlistsdemo.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL on 2016/10/11.
 */

public class RefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mDatas;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_HEADER = 2;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE     = 1;
    //没有加载更多 隐藏控件
    public static final int NO_LOAD_MORE     = 2;
    //上拉加载更多状态 默认为0
    private int mLoadMoreStatus = 0;

    private int mHeaderCount = 1;//头部View个数

    private static final int MFOOTERVIEW = 3;
    private static final int MFOOTERVIEW2 = 4;

    public enum ITEM_TYPE{
        HeaderCount;
    }

    public RefreshAdapter(Context context, List<String> datas){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
           View itemView = mLayoutInflater.inflate(R.layout.item_refresh_recylerview,parent,false);
            return new ItemCurrentViewHolder(itemView);
        }else if (viewType == TYPE_FOOTER){
            View itemView = mLayoutInflater.inflate(R.layout.load_more_footview_layout,parent,false);
            return new FooterCurrentViewHolder(itemView);
        }else if (viewType == TYPE_HEADER){
            View itemView = mLayoutInflater.inflate(R.layout.item_test_header,parent,false);
            return  new HeaderViewHodler(itemView);
        }else if (viewType ==  MFOOTERVIEW){
            View itemView = mLayoutInflater.inflate(R.layout.item_test_footer,parent,false);
            return new FooterViewHodler(itemView);
        }else if (viewType == MFOOTERVIEW2){
            View itemView = mLayoutInflater.inflate(R.layout.item_test_footer2,parent,false);
            return new FooterViewHolder2(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemCurrentViewHolder) {

            ItemCurrentViewHolder itemViewHolder = (ItemCurrentViewHolder) holder;
            //需要减掉头View 即position - 1
            String  str  = mDatas.get(position - 1);
            itemViewHolder.mTvContent.setText(str);

        } else if (holder instanceof FooterCurrentViewHolder) {

            FooterCurrentViewHolder footerViewHolder = (FooterCurrentViewHolder) holder;

            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.mTvLoadText.setText("上拉加载更多中...");
                    break;
                case LOADING_MORE:
                    footerViewHolder.mTvLoadText.setText("加载更多中...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footerViewHolder.mLoadLayout.setVisibility(View.GONE);
                    break;

            }
        }else if (holder instanceof HeaderViewHodler){
            Glide.with(mContext).load(Constant.url).centerCrop().into(((HeaderViewHodler) holder).ivHeader);
            Glide.with(mContext).load(Constant.url2).centerCrop().into(((HeaderViewHodler) holder).iv1);
            Glide.with(mContext).load(Constant.url3).centerCrop().into(((HeaderViewHodler) holder).iv2);
            Glide.with(mContext).load(Constant.url6).centerCrop().into(((HeaderViewHodler) holder).iv5);
            Glide.with(mContext).load(Constant.url7).centerCrop().into(((HeaderViewHodler) holder).iv6);
            Glide.with(mContext).load(Constant.url8).centerCrop().into(((HeaderViewHodler) holder).iv7);
            Glide.with(mContext).load(Constant.url9).centerCrop().into(((HeaderViewHodler) holder).iv8);

        }
    }

    @Override
    public int getItemCount() {
        //RecyclerView的count设置为数据总条数+ 1（footerView上拉刷新）+1 (headerView) + 1(脚布局)
        return mDatas.size()+ 1 + 1 + 1 + 1;
    }

    @Override
    public int getItemViewType(int position) {

        //有一点疑惑 postion +1 = 上拉刷新位置的控件 ； postion + 2 = 上拉刷新上面的那个控件 ；
        // postion + 3 = 上拉刷新上面的上面的控件，如果要加入类型，以此类推
        if (mHeaderCount != 0 && mHeaderCount > position){
            //头部view
             return TYPE_HEADER;
        }else if (position + 1 == getItemCount()){
             return TYPE_FOOTER;
        }else if (position + 2 == getItemCount()){
             return MFOOTERVIEW;
        }else if (position + 3 == getItemCount()){
             return MFOOTERVIEW2;
        }else{
             return  TYPE_ITEM;
        }
    }

    public class HeaderViewHodler extends RecyclerView.ViewHolder{
        ImageView ivHeader;
        ImageView iv1;
        ImageView iv2;
        ImageView iv5;
        ImageView iv6;
        ImageView iv7;
        ImageView iv8;

        public HeaderViewHodler(View itemView) {
            super(itemView);
            ivHeader = (ImageView) itemView.findViewById(R.id.tv_item_text);
            iv1 = (ImageView) itemView.findViewById(R.id.iv1);
            iv2 = (ImageView) itemView.findViewById(R.id.iv2);
            iv5 = (ImageView) itemView.findViewById(R.id.iv5);
            iv6 = (ImageView) itemView.findViewById(R.id.iv6);
            iv7 = (ImageView) itemView.findViewById(R.id.iv7);
            iv8 = (ImageView) itemView.findViewById(R.id.iv8);
            click(itemView);
        }

         public void click(final View itemView){
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(mContext,"我是头布局" ,Toast.LENGTH_SHORT).show();
                 }
             });
         }
    }

    public class ItemCurrentViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.tvContent)
        TextView mTvContent;

        public ItemCurrentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
            initListener(itemView);
        }

        private void initListener(View itemView){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "poistion " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class FooterCurrentViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.pbLoad)
        ProgressBar mPbLoad;
        @InjectView(R.id.tvLoadText)
        TextView     mTvLoadText;
        @InjectView(R.id.loadLayout)
        LinearLayout mLoadLayout;
        public FooterCurrentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }

    public class FooterViewHodler extends RecyclerView.ViewHolder{

        LinearLayout tvFooterView;

        public FooterViewHodler(View itemView) {
            super(itemView);
              tvFooterView = (LinearLayout) itemView.findViewById(R.id.tv_footerview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "我是脚布局 2", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class FooterViewHolder2 extends RecyclerView.ViewHolder{

        public RelativeLayout rlFooterView;

        public FooterViewHolder2(View itemView) {
            super(itemView);
            rlFooterView = (RelativeLayout) itemView.findViewById(R.id.rl_footeview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "我是脚布局 1", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void AddHeaderItem(List<String> items) {
        mDatas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<String> items) {
        mDatas.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 更新加载更多状态
     * @param status
     */
    public void changeMoreStatus(int status){
        mLoadMoreStatus=status;
        notifyDataSetChanged();
    }

}
