package com.viewpager.fragment;

import java.util.List;

import com.main.functionlistsdemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentOneAdapter extends BaseAdapter{

	private Context context;
	private List<FragmentDataBean> list;
	LayoutInflater inflater;

	public FragmentOneAdapter(Context context,List<FragmentDataBean> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView ==null ) {
			convertView = inflater.inflate(R.layout.fragment_vp_one, null);
			viewHolder = new ViewHolder();
			viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
			viewHolder.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
			viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		//用传过来的list 来设置动态数据显示
		viewHolder.iv.setBackgroundResource(list.get(position).getIv());
		viewHolder.tv_des.setText(list.get(position).getDes().toString());
		viewHolder.tv_date.setText(list.get(position).getDate().toString());

		return convertView;
	}

	public class ViewHolder{
		private TextView tv_des,tv_date;
		private ImageView iv;

	}

}
