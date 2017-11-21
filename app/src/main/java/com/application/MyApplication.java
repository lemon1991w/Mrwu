package com.application;

import android.app.Activity;
import android.app.Application;

import com.utils.FileCache;
import com.utils.ScreenUtil;
import com.utils.ToastUtil;
import com.utils.Utils;

import org.litepal.LitePalApplication;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author wuweiting
 * Created by ZJHL on 2016/11/8.
 * <p>
 * 程序入口 全局只有一个application对象
 * 用来数据共享 初始化配置等操作
 * 数据传递时  可以把application当成一个中转站 可以传递对象类型(普通情况下只能传基本数据类型)
 *
 * <p>
 */

public class MyApplication extends LitePalApplication {

    private static MyApplication instance;

    private static HashMap<String,Object> map = new HashMap<>();

    /**
     * activity 堆栈
     */
    private static Stack<Activity> activityStack = new Stack<>();
    /**
     * 是否是调试模式
     */
    private static boolean DEBUG = false;

    /**
     * 获取application
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    public static HashMap<String,Object> getMap(){
        return map;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 主进程才process
         */
        if (instance == null && Utils.isMainProcess()){
            instance = this;

            /**
             * init screen 参数  初始化屏幕分辨率
             * file工具  初始化缓存文件夹
             */
             try{
                 ScreenUtil.initScreen(this);
                 FileCache.init(this);
             }catch (Exception e){
             }
        }
         MyUncaughtExceptionHandler.with(this);
    }

    /**
     * 获取activity堆栈大小
     */
    public int getActivityStackSize() {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        return activityStack.size();
    }

    /**
     * add Activity 添加Activity到栈
     */
    public static void addActivity(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        try {
            if (activity != null) {
                if (activityStack != null) {
                    activityStack.remove(activity);
                }
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return null;
        }
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 栈 中是否有 Activity
     */
    public boolean hasActivity(Class<?> cls) {
        if (cls == null || activityStack == null || activityStack.isEmpty()) {
            return false;
        }
        for (Activity activity1 : activityStack) {
            if (cls.equals(activity1.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack != null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity activity = activityStack.get(i);
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        }
    }

    /**
     * 遍历结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack != null) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    try {
                        Activity activity = activityStack.get(i);
                        if (!activity.isFinishing()) {
                            activity.finish();
                        }
                    } catch (Exception e) {
                    }
                }
            }
            activityStack.clear();
        }
    }

    @Override
    public void onTerminate() {
        try {
            finishAllActivity();
            super.onTerminate();
        }catch (Exception e){
            ToastUtil.showToast(this,"application exit");
        }
    }

    /**
     * app 是否在debug 模式。app分为debug模式和release模式
     *
     * @return
     */
    public static boolean isAppDebug() {
        return DEBUG;
    }

}
