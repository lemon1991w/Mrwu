package com.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.MyApplication;
import com.main.functionlistsdemo.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wusourece on 2017/6/7.
 * 如果需要使用actionbar的话继承此类 就继承BaseActionBarActivity，如果需要自定义布局的话，就继承此类
 *
 *  基础的activity 日后有共性的功能都可以在此类中进行封装 所有activity均继承此类
 * 提供activity的堆栈管理
 * 以后若需要页面跳转统计等服务，可以在此类中进行
 *
 * 绑定注解框架ButterKnife.inject(this)时， 应先初始化actionBar在前，绑定在后，否则抛异常
 *
 * 注意!!!  代码编写的时候尽量不要使用硬编码 抽取到values文件夹下
 *
 *   布局代码的编写 如果是左右布局的话尽量使用权重 这样不管在什么机型下都可以正常展示布局
 */

public class BaseActivity extends AppCompatActivity{

    private static boolean isExit = false;
    private static Boolean hasTask = false;

    private Timer tExit;
    private TimerTask task;

    public int ACTIONBAR_MODE_TEXT = 0x0001;//标题文字模式
    public int ACTIONBAR_MODE_LOGO = 0x0002;//标题logo模式

    /**
     * ActionBar框架 文字模式,其他页面可使用此方法
     *
     * @param text                标题文字
     * @param hasLeftButton      是否显示标题栏左边Button
     * @param leftListener       左边Button点击事件,若无特殊处理,则可填null
     * @param leftImageResource  左边Button图片id,若无特殊处理,则可填0,默认返回按钮
     * @param hasRightButton     是否显示标题栏右边Button
     * @param rightListener      右边Button点击事件,填null则无点击事件
     * @param rightImageResource 右边Button图片id,必填
     */
    public void setupActionBar(String text, boolean hasLeftButton, View.OnClickListener leftListener, int leftImageResource, boolean hasRightButton, View.OnClickListener rightListener, int rightImageResource) {
        setupActionBar(ACTIONBAR_MODE_TEXT, text, hasLeftButton, leftListener, leftImageResource, hasRightButton, rightListener, rightImageResource);
    }

    /**
     * ActionBar框架
     *
     * @param mode               标题模式
     * @param text               标题文字 若logo模式,可填null
     * @param hasLeftButton      是否显示标题栏左边Button
     * @param leftListener       左边Button点击事件,若无特殊处理,则可填null
     * @param leftImageResource  左边Button图片id,若无特殊处理,则可填0,默认返回按钮
     * @param hasRightButton     是否显示标题栏右边Button
     * @param rightListener      右边Button点击事件,填null则无点击事件
     * @param rightImageResource 右边Button图片id,必填
     */
    public void setupActionBar(int mode, String text, boolean hasLeftButton, View.OnClickListener leftListener, int leftImageResource, boolean hasRightButton, View.OnClickListener rightListener, int rightImageResource) {

        if (ACTIONBAR_MODE_LOGO == mode) {
            titleText.setVisibility(View.GONE);
            titleImage.setVisibility(View.VISIBLE);
        } else {
            titleImage.setVisibility(View.GONE);
            titleText.setVisibility(View.VISIBLE);
            titleText.setText(text);
        }

        if (hasLeftButton) {
            leftButton.setVisibility(View.VISIBLE);
            if (leftImageResource != 0)
                leftButton.setImageResource(leftImageResource);
            if (null != leftListener)
                leftButton.setOnClickListener(leftListener);
            else
                leftButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
        } else {
            leftButton.setVisibility(View.GONE);
        }

        if (hasRightButton) {
            rightButton.setVisibility(View.VISIBLE);
            rightButton.setImageResource(rightImageResource);
            rightButton.setOnClickListener(rightListener);
        } else {
            rightButton.setVisibility(View.GONE);
        }
    }

    public  void setupActionBarView() {
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();

        View cust = View.inflate(this, R.layout.titlebar, null);
        android.support.v7.app.ActionBar.LayoutParams lp = new android.support.v7.app.ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        actionbar.setElevation(2.0f);

        actionbar.setDisplayShowHomeEnabled(false);
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setCustomView(cust, lp);
        Toolbar parent = (Toolbar) cust.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        titleText = (TextView) cust.findViewById(R.id.titleText);
        titleImage = (ImageView) cust.findViewById(R.id.titleImage);
        leftButton = (ImageView) cust.findViewById(R.id.leftButton);
        rightButton = (ImageView) cust.findViewById(R.id.rightButton);

    }

    private TextView titleText;
    private ImageView titleImage;
    private ImageView leftButton;
    private ImageView rightButton;
    private RelativeLayout rlComm;

    private static Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && allowSavedInstanceState()){
            savedInstanceState = null;
        }
        try {
            super.onCreate(savedInstanceState);
        }catch (Exception e){
        }
        //将activity加入堆栈
        MyApplication.getInstance().addActivity(this);
//        setupActionBarView();
//        CheckNetConnectionUtils.checkConnection(this);
//        LoginSharedPreferencesUtil.initContext(this);  //初始化sp
        this.mContext = this;

        tExit = new Timer();
        task = new TimerTask() {

            public void run() {
                isExit = false;
                hasTask = true;
            }
        };
//        LoginSharedPreferencesUtil.initContext(this);   //初始化sp文件夹

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity从堆栈中移除
 //       MyApplication.getInstance().finishActivity(this);
    }

    /**
     * 设置状态栏的颜色，可以改变不兼容的问题，目前只是在4.0以上有效
     */
    public void setStatusBarView(int color) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            /**
             * 不能设置为透明，因为activity布局会往上顶，影响了界面的展示
             */
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(false);
            tintManager.setTintColor(color);

        }
    }

    /**
     * 是否允许保存状态，默认不允许
     *
     * @return
     */
    public boolean allowSavedInstanceState() {
        return false;
    }

    /**
     * 开启启动和关闭activity的动画
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//        overridePendingTransition(R.anim.animation0_in_current,R.anim.animation0_in_new);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
//        overridePendingTransition(R.anim.animation0_in_current,R.anim.animation0_in_new);
    }

    /**
     *     以下方法判断栈中是否有activity或者fragment  也可以在finish后添加出入动画
     */

    @SuppressLint("NewApi")
    @Override
    public void onBackPressed() {
        /* 如果堆栈中有Fragment可以返回的话，先返回上一个Fragment */
        if (getFragmentManager().getBackStackEntryCount() == 0){
            /* isTaskRoot可以判断当前Activity是否为程序最后一个 */
            if (isTaskRoot()){
                if (isExit == false){

                    isExit = true;
//                    Snackbar snackbar = SnackbarUtil.shortSnackbar(rlComm,getResources().getString(R.string.exit_application), Color.parseColor("#ffffff"),getResources().getColor(R.color.immersion_color));
//                    snackbar.show();
                    ToastUtil.showToast(this,getResources().getString(R.string.exit_application));
                    if (!hasTask){
                        tExit.schedule(task,2000);
                    }
                }else {
                    finish();
                    System.exit(0);
                    /**
                     *  退出时结束堆栈中的所有activity
                     */
                    MyApplication.getInstance().finishAllActivity();
                }
            }else{
                super.onBackPressed();
//                overridePendingTransition(R.anim.animation_exit_in_current,R.anim.animation_exit_in_new);
            }
        }else {
            getFragmentManager().popBackStack();
        }
    }

}

