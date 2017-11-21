package com.net.request.more;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.mutils.utils.Log;
import com.main.functionlistsdemo.R;
import com.net.request.more.AsynNetUtils.Callback;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 * 
 * @author admin
 *
 */
    public class MainNetRequest extends Activity {
    	
    	private TextView tvToast;
    	private static final String url ="http://www.baidu.com";
    	private static String TAG = "MainNetRequest";
    	private RequestQueue queue;

	    private Snackbar snackBar;
	    CoordinatorLayout layout;
	    @Override
	   protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
	    	setContentView(R.layout.activity_net_request);
	    	tvToast = (TextView) findViewById(R.id.tv_net_request);
	    	queue = Volley.newRequestQueue(this);
	    	getRequest();
	    	postRequest();
			initView();
			initToolBar();
	}

	private void initToolBar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
		toolbar.setLogo(R.mipmap.ic_launcher);
		toolbar.setTitle("Title");
		toolbar.setSubtitle("sonTitle");

		toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
                  int menuItemId  = item.getItemId();
				if (menuItemId == R.id.action_search) {
					Toast.makeText(MainNetRequest.this ,"搜索", Toast.LENGTH_SHORT).show();

				} else if (menuItemId == R.id.action_notification) {
					Toast.makeText(MainNetRequest.this ,"通知",Toast.LENGTH_SHORT).show();

				} else if (menuItemId == R.id.action_item1) {
					Toast.makeText(MainNetRequest.this , "item 1" , Toast.LENGTH_SHORT).show();

				} else if (menuItemId == R.id.action_item2) {
					Toast.makeText(MainNetRequest.this , "item 2" , Toast.LENGTH_SHORT).show();
				}
				return true;
			}
		});
	}

	private void initView() {
		final TextView tvRight = (TextView) findViewById(R.id.tv_right);
		layout = (CoordinatorLayout) findViewById(R.id.layout);

		tvToast.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Snackbar sb = snackBar.make(layout,"先给自己定一个小目标",snackBar.LENGTH_SHORT).setAction("点我", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						tvRight.setVisibility(View.VISIBLE);
						tvRight.setText("猴赛雷");
					}
				});
//				Snackbar sb = snackBar.make(layout,"haha",snackBar.LENGTH_SHORT);
				 sb.getView().setBackgroundColor(Color.parseColor("#32cd99"));
                 sb.show();
			}

		});
	}


	private void getRequest() {
	    	StringRequest request = new StringRequest(url, new Response.Listener<String>() {

				@Override
				public void onResponse(String response) {
					Log.e("TAG",response);
					System.out.println("TAG信息被打印了"+response);
				}
			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError arg0) {
					
				}
			});
	    	queue.add(request);			
		}
		
		private void postRequest() {
			StringRequest request = new StringRequest(Method.POST, url, new Response.Listener<String>() {

				@Override
				public void onResponse(String s) {
					System.out.println("TAG信息被打印了"+s);
				}
			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError v) {
					
				}
			}){
				@Override
				protected Map<String, String> getParams()throws AuthFailureError {
		               Map<String,String> map=new HashMap<>();  
		               map.put("phone","13800138000");  
		               map.put("appkey", "10003");  
		               map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");  
		               map.put("format", "json");  
		               map.put("idcard", "110101199001011114");  
		               return map;  
				}
			};
			queue.add(request);
		}
}
