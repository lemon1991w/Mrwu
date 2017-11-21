package com.main.functionlistsdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class ArrayAdapter<String> extends BaseAdapter {
   
   private Context context;
   private LayoutInflater flater;	
   private String[] data;
	
	public ArrayAdapter(Context context,String[] data){
		this.context = context;
		flater = LayoutInflater.from(context);
		this.data = data;
	}
	
	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView= flater.inflate(R.layout.listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.text = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.text.setText(data[position]+ "");

		notifyDataSetChanged();

		return convertView;
	}
	
	private class ViewHolder{
		private TextView text;
	}
}

