package com.show.more;

import com.main.functionlistsdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ClickShowMore extends Activity implements OnClickListener{
	
	private LinearLayout ll_my_data;
	private ImageView iv_my_sd;
	private ListView listView;
	
	private boolean isOpenData = true;
	
	private MyShowMoreAdapter adapter;
	
	private String data[] = new String[]{"白天不懂夜的黑","2","3"};
	
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	requestWindowFeature(Window.FEATURE_NO_TITLE);
        	setContentView(R.layout.activity_show_more);
        	initData();
        }

		private void initData() {
			ll_my_data = (LinearLayout) findViewById(R.id.ll_my_data);
			iv_my_sd = (ImageView) findViewById(R.id.iv_my_sd);
			listView = (ListView) findViewById(R.id.lv_my_sd);
			ll_my_data.setOnClickListener(this);
			
			adapter = new MyShowMoreAdapter();
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					startActivity(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS));
				}
			});
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ll_my_data:
//				listView.setVisibility(isOpenData ? View.GONE : View.VISIBLE);
//				iv_my_sd.setBackgroundResource(!isOpenData ? R.drawable.sd_top
//						: R.drawable.sd_down);
				if (isOpenData == true) {
					listView.setVisibility(View.GONE);
					iv_my_sd.setBackgroundResource(R.drawable.sd_down);
				}else{
					listView.setVisibility(View.VISIBLE);
					iv_my_sd.setBackgroundResource(R.drawable.sd_top);
				}
				
				if (isOpenData) {
					isOpenData = false;
				} else {
					
					isOpenData = true;
				}
				break;
			default:
				break;
			}
		}
		
		private class MyShowMoreAdapter extends BaseAdapter{
			
     /**
      * 如果需要抽取adapter的话就可以用下面(被注释)这种方式			
      */
//			Context context;
//			TextView tv_des;
//			LayoutInflater inflater;
//			String[] data;
//			
//			public MyShowMoreAdapter(Context context,String[] data){
//				this.context = context;
//				this.data = data;
//				inflater = LayoutInflater.from(context);
//			}
			
			@Override
			public int getCount() {
				return 3;
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
				
				View view = View.inflate(ClickShowMore.this, R.layout.activity_show_more_adapter, null);
				TextView tv_des = (TextView) view.findViewById(R.id.tv_des);
				
//				if (convertView == null) {
//					convertView = inflater.inflate(R.layout.activity_show_more_adapter,null);
//					ImageView iv_photo = (ImageView) convertView.findViewById(R.id.iv_photo);
//					tv_des = (TextView) convertView.findViewById(R.id.tv_des);
//					
//				}
//				
//				tv_des.setText(""+data[position]);				
				return view;
			}
			
		}
}
