package com.viewpager.fragment;

import java.util.ArrayList;

import com.main.functionlistsdemo.R;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;

/**
 * ViewPager+Fragment+refresh
 * @author admin
 * @date 2016-6-1
 * @name Mrwu
 */

public class MainPager extends FragmentActivity{
	private RadioButton rb;
	private RadioButton rb1;
	private RadioButton rb2;
	private ImageView img_bottom_line;
	private ViewPager viewPager;
	int currentFragment = 0;
	private FragmentManager fm = null;

	int screenWidth = 0;
	int screenHeight = 0;
	int screenW = 0;

	float offset; //动画图片偏移量
	int endx = 0;
	private int currImagex; // 线条距离y主坐标

	ArrayList<Fragment> list = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.viewpager_fragment_main);
		rb = (RadioButton) findViewById(R.id.main_rb0);
		rb1 = (RadioButton) findViewById(R.id.main_rb1);
		rb2 = (RadioButton) findViewById(R.id.main_rb2);
		viewPager = (ViewPager) findViewById(R.id.main_viewpager);
		img_bottom_line = (ImageView) findViewById(R.id.img_bottom_line);
		init();
	}

	@SuppressLint("NewApi")
	private void init() {
		/**
		 * 将Fragment加入到集合里
		 */
		Fragment_one one = new Fragment_one();
		Fragment_two two = new Fragment_two();
		Fragment_three three = new Fragment_three();
		list.add(one);
		list.add(two);
		list.add(three);
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		viewPager.setOffscreenPageLimit(0);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				currentFragment = position; //把当前viewPager赋值给当前Fragment
				imageLines(position); //初始化的下划线最终终点
				lineMove(currImagex, endx); //下划线的偏移量
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		initRadioButton(); //初始化radioButton点击事件
		initImageView();  //获得屏幕信息
	}

	public  class ViewPagerAdapter extends FragmentPagerAdapter{

		ArrayList<String> lists = new ArrayList<String>();

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(View container, int position) {
			lists.add(makeFramentName(container.getId(), (int)getItemId(position)));
			return super.instantiateItem(container, position);

		}

		public  String makeFramentName(int view,int index){
			return "android:switcher:"+view+":"+index;
		}
	}

	/**
	 * 计算初始化线条滑动最终点
	 * @param currentDadioButton
	 */
	public void imageLines(int currentDadioButton){
		switch (currentDadioButton) {
			case 0:
				endx= (int)offset;
				break;
			case 1:
				endx = (int)(screenW/3+offset);
				break;
			case 2:
				endx = (int)offset + screenW/3*2;
				break;

			default:
				break;
		}
	}
	/**
	 * 下划线的偏移量
	 * @param currIndex
	 * @param endX
	 */
	public void lineMove(int currIndex , int endX){
		Animation animation = new TranslateAnimation(currIndex, endX, 0, 0);
		currImagex = endX;
		animation.setFillAfter(true); //停留在结束位置
		animation.setDuration(300);
		img_bottom_line.startAnimation(animation);
	}

	/**
	 *  获得屏幕信息
	 */
	private void initImageView() {
		//得到显示屏宽
		DisplayMetrics dm = new DisplayMetrics();
		MainPager.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenW = dm.widthPixels;
		currImagex = (int)(offset = 0);  //offset 偏移量  默认没有值
		Matrix matrix = new Matrix();
		matrix.postTranslate(0, 1);
		img_bottom_line.setImageMatrix(matrix);  //设置动画初始位置
	}

	/**
	 * RadioButton点击事件
	 */
	private void initRadioButton() {
		rb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(0);
				lineMove(currImagex, endx);
				imageLines(0);
			}
		});

		rb1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(1);
				lineMove(currImagex, endx);
				imageLines(1);
			}
		});

		rb2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(2);
				lineMove(currImagex, endx);
				imageLines(2);
			}
		});
	}
}

