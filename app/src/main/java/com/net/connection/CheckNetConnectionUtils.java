package com.net.connection;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.provider.Settings;
import android.widget.Toast;

   public  class CheckNetConnectionUtils{
	   
	    public static boolean checkConnection(Context context) {
	
	ConnectivityManager manager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
	
	//3G数据网络
	State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
	
	//wifi网络连接
	State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
	
    //如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
	
	if (mobile == State.CONNECTED || mobile == State.CONNECTING) {
		Toast.makeText(context, "移动数据已连接", Toast.LENGTH_SHORT).show();
	      return true;
	  }
	
	  if (wifi == State.CONNECTED || wifi == State.CONNECTING) {
		  Toast.makeText(context, "Wifi已连接", Toast.LENGTH_SHORT).show();
	  	  return true;
	  }else {
		  //跳转到设置主页
		  Toast.makeText(context, "网络连接不可用，请连接网络！",Toast.LENGTH_SHORT).show();
		  return false;  
	    }
     }
   }
