package com.main.functionlistsdemo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.LoveCurve.LoveCurveActivity;
import com.SlidingTabLayout.SlidingTabActivity;
import com.banner.change.BannerActivity;
import com.camera.CameraActivity;
import com.city.index.list.CityIndexListActivity;
import com.com.swipelayout.recyclerview.MainSwipeLayoutActivity;
import com.comment.reply.MainCommentReply;
import com.coordinatorlayout.header.CoordinatorLayoutActivity;
import com.date.and.nimaiton.MainListActivity;
import com.imageloader.ImageLoaderDemo;
import com.imitation.ios.dialog.ImitationIosDialogActivity;
import com.immersive.demo.MainImmersive;
import com.keyboard.custom.MainKeyBoardActivity;
import com.net.connection.MainNetConnection;
import com.net.request.more.MainNetRequest;
import com.notification.bar.MainNotificationBar;
import com.progressbar.ProgressBarActivity;
import com.recording.test.RecordingTest;
import com.screen.shot.ScreenShotActivity;
import com.senior.custom.view.AnnularProgressBarActivity;
import com.senior.custom.view.CustomViewOneActivity;
import com.senior.custom.view.CustomViewThreeActivity;
import com.senior.custom.view.CustomViewTwoActivity;
import com.sesame.annular.view.SesameCreditActivity;
import com.shopping.cart.MainShoppingCart;
import com.show.more.ClickShowMore;
import com.suspension.bar.SupensionBarActivity;
import com.top.notification.TopNotification;
import com.viewpager.fragment.MainPager;
import com.zxing.code.QrcodeScannerActivity;

	/**
 * 功能集合的列表页面
 * @author admin
 *  feel 科学增高 10.25  3969人购买
 */

public class MainActivity extends Activity {

	String url = "http://172.16.10.87:8081/mcar/protal/rondamImage.do";

	private static String[] adapterData = new String[]{"自定义环形进度条","自定义view三","自定义view二","自定义view一","长图截取","录音测试","芝麻信用的环形view","二维码扫描","camera","顶部通知栏","城市索引列表","悬浮条的recycleView","系统通知栏","LoveCurve","SlidingTabLayout+viewpager","CoordinatorLayout + 顶部特效","自定义进度条","滑动切换轮播圆点","自定义键盘和支付键盘","swipeLayoutRecyclerView","时间选择和常用动画","仿ios的dialog","评论回复功能","snackBar Toast","shoppingCart","显示更多","进入应用时判断网络是否连接","沉浸式状态栏","imageLoaderDemo","viewPager+Fragment"};

	private static Class []  clazzs = new Class[]{AnnularProgressBarActivity.class,CustomViewThreeActivity.class,CustomViewTwoActivity.class,CustomViewOneActivity.class,ScreenShotActivity.class,RecordingTest.class,SesameCreditActivity.class,QrcodeScannerActivity.class,CameraActivity.class,TopNotification.class,CityIndexListActivity.class,SupensionBarActivity.class,MainNotificationBar.class,LoveCurveActivity.class,SlidingTabActivity.class,CoordinatorLayoutActivity.class,ProgressBarActivity.class,BannerActivity.class,MainKeyBoardActivity.class,MainSwipeLayoutActivity.class,MainListActivity.class, ImitationIosDialogActivity.class,MainCommentReply.class,MainNetRequest.class,MainShoppingCart.class,ClickShowMore.class,MainNetConnection.class,MainImmersive.class,ImageLoaderDemo.class,MainPager.class};
	
    private ArrayAdapter<String> adapter ;
    private ListView listView;
	
    private static boolean isExit = false;
    private static Boolean hasTask = false;

	private Timer tExit;
	private TimerTask task;


    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listview);
		adapter = new ArrayAdapter<String>(getApplicationContext(),adapterData);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override 
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this,clazzs[position]);
				startActivity(intent);
			}
		  });
		
		tExit = new Timer();
		task = new TimerTask() {
		   
		   public void run() {
		       isExit = false;
		       hasTask = true;
		   }
		};
		}
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	 
    	   if (keyCode == KeyEvent.KEYCODE_BACK) {
    	       if(isExit == false ) {
    	           isExit = true;
    	           Toast.makeText(this, "再按一次后退键退出应用程序", Toast.LENGTH_SHORT).show();
    	           if(!hasTask) {
    	               tExit.schedule(task, 2000);
    	           }
    	       } else {
    	           finish();
    	           System.exit(0);
    	       }
    	   }
    	   return false;
    	}
}