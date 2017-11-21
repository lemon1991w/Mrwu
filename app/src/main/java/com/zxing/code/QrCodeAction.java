package com.zxing.code;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.main.functionlistsdemo.R;
import com.utils.ToastUtil;
import com.utils.Utils;

/**
 * Created by ZJHL on 2017/3/7.
 * 解析扫码后的动作
 */

public class QrCodeAction  {

    /**
     * 检查url
     * @param activity
     * @param url
     */
    public static void checkURI(Activity activity, String url){
           try{
               Uri _uri = Uri.parse(url);
               /**
                * 通过这个完整的uri地址 可以获取到
                *    scheme 、 host、port部分、访问路径、query部分、指定的参数值(url.getQueryParameter("params"))
                */
               String scheme = _uri.getScheme();
               switch (scheme){
                   case "http":
                   case "https":
                   openWebPage(activity,url);  //打开web页面
                       break;
                   default:
                       tipNotSupportError(activity);  //不支持的提示
               }
           }catch (Exception e){

           }
    }

    /**
     * 打开网页 进行跳转
     * @param activity
     * @param url
     */
    private static void openWebPage(Activity activity, String url) {
        if (TextUtils.isEmpty(url)){
                return;
            }

        try {
            /**
             * 开启本地浏览器，如果开启异常的话、开启系统浏览器
             */
              openWebUrlWithBrowser(activity,url);
        }catch (Exception e){
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            activity.startActivity(intent);

        }
    }

    /**
     * 开启本地浏览器
     * @param activity
     * @param url
     */
    private static void openWebUrlWithBrowser(Activity activity, String url) {
        //把常用的浏览器包名放到数组中
        String [] browser = {"com.UCMobile", "com.uc.browser", "com.tencent.mtt", "com.android.browser", "com.oupeng.browser", "com.oupeng.mini.android"};
        Intent intent = null;
        //遍历数组中的浏览器 并检查apk是否存在
        for (String br: browser){
            if (Utils.checkApkExist(activity,br)){
                String clsName = null;
                try {
                    //获得包的管理器
                    PackageManager pm = activity.getApplicationContext().getPackageManager();
                    //获取返回要跳转的Intnet 即br对象
                    Intent intent1 = pm.getLaunchIntentForPackage(br);
                    ComponentName act = intent1.resolveActivity(pm); //返回组件名称
                    clsName = act.getClassName(); //获得组件类名
                }catch (Exception e){
                   e.printStackTrace();
                }

                if (clsName == null){
                    break;
                }
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                intent.setClassName(br,clsName); //设置包名和类名
                break;
            }
        }
        if (intent == null){
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
        }
        activity.startActivity(intent);
    }

    /**
     * 不支持的连接错误提示
     *
     * @param activity
     */
    private static void tipNotSupportError(Activity activity) {
        ToastUtil.showToast(activity, R.string.check_qr_code);
    }
}
