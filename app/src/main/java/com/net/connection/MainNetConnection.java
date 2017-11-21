package com.net.connection;

import com.base.BaseActivity;
import com.main.functionlistsdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

//

public class MainNetConnection extends BaseActivity {
         @Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
             requestWindowFeature(Window.FEATURE_NO_TITLE);
        	setContentView(R.layout.activity_net_connection);
//        	CheckNetConnectionUtils.checkConnection(MainNetConnection.this);
        	initData();
             getPhone();
         }
   
        /** 
        * 当activity创建或者从被覆盖 、 后台回到前台的时候被调用
        */
       @Override
       protected void onResume() {
       CheckNetConnectionUtils.checkConnection(MainNetConnection.this);
       super.onResume();
      }

       //袁胜平招租电话
       public void initData() {
		  int [] arr = new int []{1,8,0,7,4,5,9,3};
		  int [] index = new int[]{0,1,5,2,0,7,3,4,5,6,7};
		  String tel = "";
		  for(int i : index){             
			  tel+=arr[i];
		  }
           if (tel.length() >10){
               System.out.println("小袁联系方式"+tel);
           }
	}

       //小巫招租电话
       public void getPhone(){
           int [] arr = new int[]{1,8,6,0,5,4,3};
           int [] index = new int[]{0,1,2,3,3,3,4,5,1,0,6}; //通过索引找对应的数字
           String phone = "";
           for (int i: index){
               phone += arr[i];
           }
           if (phone.length() >10){
               System.out.println("小巫联系方式"+phone);
           }
       }

}
