package com.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.application.*;
import com.application.MyApplication;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ZJHL wuweiting on 2016/11/1.
 *
 */

public class Utils {

    /**
     * 创建一个定长线程池，减少重复创建和销毁带来的系统资源开销
     * 可控制线程最大并发数为3，超出的线程会在队列中等待
     */
    private static ExecutorService sExecutorService  = Executors.newFixedThreadPool(3);

    /**
     * 使用线程池执行线程
     *
     * @param runnable
     */
    public static void executeThread(Runnable runnable){
        if (runnable == null){
           return;
        }
        try {
            sExecutorService.submit(runnable);
        }catch (Exception e){
        }
    }

    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

    /**
     * 判断是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断密码长短是否合法
     *
     * @param str
     * @return
     */
    public static boolean isPasswdAuthorized(String str) {
        return (str.length()>6 && str.length()<18);
    }

    /**
     * 判断邮箱格式
     * */
    public static boolean isEmail(String str) {
        String check = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否存在SD卡
     *
     * @return
     */
    public static boolean hasSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            return true;
        return false;
    }

    /**
     * 判断是否是正确的手机号
     *
     * @param phone
     * @return
     */
    public boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile("^13/d{9}||15[8,9]/d{8}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 获取版本名
     *
     * @param context
     * @return
     */
     public static String getVersionName(Context context){
         String versionName = "";
         try {
             Context appContext = context.getApplicationContext();
             PackageManager pm = appContext.getPackageManager();
             PackageInfo info = pm.getPackageInfo(appContext.getPackageName(),0);
            versionName = info.versionName;
         }catch (Exception e){
         }
         return versionName;
     }

    /**
     * 获取版本code
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        Context appContext = context.getApplicationContext();

        try {
            PackageManager pm = appContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(appContext.getPackageName(), 0);
            versionCode = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取packageName
     */
    public static String getPackageName(){
        return getPackageName();
    }

    /**
     * Toast
     */
    public static void showToast(final Context context,String msg){
        try {
            if (msg == null){
                return;
            }
            ToastUtil.showToast(context,msg);
        }catch (Exception e){

        }
    }

    /**
     * 获取GPS 是否开启
     *
     * @param context
     * @return
     */
    public static boolean isGPSEnable(Context context) {

        try {
            LocationManager locationManager = (LocationManager) context.
                    getSystemService(Context.LOCATION_SERVICE);
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (SecurityException ex) {
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 将版本里面的 . 替换成 _
     *
     * @param context
     * @return
     */
    public static String getVersionNameWithoutPoint(Context context) {
        String versionName = getVersionName(context);
        return versionName.replace(".", "_");
    }

    /**
     * 是否存在SD卡
     *
     * @return
     */
    public static boolean ifExitsSD() {
        String SDState = Environment.getExternalStorageState();
        return SDState.equals(Environment.MEDIA_MOUNTED);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private static class BitmapHoneycombMR1 {
        static int getByteCount(Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    }

    /**
     * 获取 bitmap 大小
     *
     * @param bitmap
     * @return
     */
    public static int getBitmapBytes(Bitmap bitmap) {
        int result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            result = BitmapHoneycombMR1.getByteCount(bitmap);
        } else {
            result = bitmap.getRowBytes() * bitmap.getHeight();
        }
        if (result < 0) {
            throw new IllegalStateException("Negative size: " + bitmap);
        }
        return result;
    }

    /**
     * bitmap 转为 byte数组
     *
     * @param bm
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bm) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        } catch (Throwable e) {
            return null;
        }
    }



    /**
     * 判断是否是图片
     *
     * @param fileName
     * @return
     */
    public static boolean isImage(String fileName) {
        String fn = fileName.toLowerCase();
        return fn.endsWith(".jpg") || fn.endsWith(".jpeg") || fn.endsWith(".png") || fn.endsWith(".gif");
    }

    /**
     * 检测app是否存在
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_META_DATA);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        } catch (Throwable e) {
            return false;
        }
    }

    /**
     * 判断app是否在运行
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取android sdk版本
     *
     * @return
     */
    public static int getAndroidSDKVersion() {
        try {
            return android.os.Build.VERSION.SDK_INT;
        } catch (Throwable e) {
            return 0;
        }
    }

    /**
     * drawable 转为 bitmap
     *
     * @param drawable drawable
     * @return bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof GlideBitmapDrawable) {
            return ((GlideBitmapDrawable) drawable).getBitmap();
        }

        final int width = !drawable.getBounds().isEmpty() ?
                drawable.getBounds().width() : drawable.getIntrinsicWidth();

        final int height = !drawable.getBounds().isEmpty() ?
                drawable.getBounds().height() : drawable.getIntrinsicHeight();

        final Bitmap bitmap = Bitmap.createBitmap(width <= 0 ? 1 : width, height <= 0 ? 1 : height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * intent 转map
     *
     * @param intent
     * @return
     */
    public static Map getMapByIntent(Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return Collections.emptyMap();
        }
        Bundle bundle = intent.getExtras();
        Set<String> keys = bundle.keySet();
        Map res = new HashMap();
        for (String key : keys) {
            res.put(key, bundle.get(key));
        }
        return res;
    }

    /**
     * 用来判断服务是否运行.        
     *
     * @param context
     * @param className 判断的服务名字          
     * @return true 在运行 false 不在运行         
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
            if (!(serviceList.size() > 0)) {
                return false;
            }
            for (int i = 0; i < serviceList.size(); i++) {
                if (serviceList.get(i).service.getClassName().equals(className) == true) {
                    isRunning = serviceList.get(i).started;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRunning;
    }

    /**
     * 检查权限
     *
     * @param context
     * @param premission
     * @return
     */
    public static boolean checkPermission(Context context, String premission) {
        try {
            PackageManager localPackageManager = context.getPackageManager();
            if (localPackageManager.checkPermission(premission, context.getPackageName()) != 0) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    /**
     * 检测是否是在主线程
     *
     * @return
     */
    public static boolean isMainProcess() {
        try {
            ActivityManager am =(ActivityManager) com.application.MyApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
            String mainProcessName = MyApplication.getInstance().getPackageName();
            int myPid = android.os.Process.myPid();
            if (processInfos != null) {
                for (ActivityManager.RunningAppProcessInfo info : processInfos) {
                    if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                        return true;
                    }
                }
            } else {//这种情况很少，返回true可能执行某些操作会多几次，但是至少不会出现不执行的问题
                return true;
            }
        } catch (Throwable e) {
        }
        return false;
    }

    /**
     * 获取文件扩展名
     *
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * input stream 获取 字节数组
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static byte[] getByte(InputStream inputStream) throws Exception {
        byte[] b = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;
        while ((len = inputStream.read(b)) != -1) {
            byteArrayOutputStream.write(b, 0, len);
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

}
