package com.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.main.functionlistsdemo.R;

/**
 * 显示内容的Toast
 * Created by ZJHL on 2016/11/2.
 */

public class ToastUtil {
    /**
     * 显示内容
     */
    public static void showToast(Context context, String msg){
        try {
            if (msg == null){
                return;
            }
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }

    /**
     * 显示 Toast
     *
     * @param context
     * @param msgId
     */
    public static void showToast(final Context context, int msgId) {
        try {
            Toast.makeText(context, msgId, Toast.LENGTH_SHORT).show();
        } catch (Throwable e) {
        }
    }

    /**
     * 显示 Toast 自定义UI每行展示一行文字
     *
     * @param list 文字列表
     */
    public static void showToast(Context context, String[] list){
        try {
             if (list == null || list.length==0){
                 return;
             }
            Toast toast = new Toast(context);
            View root = LayoutInflater.from(context).inflate(R.layout.toast_multi_lines,null);
            TextView tv = (TextView) root.findViewById(R.id.text_line1);
            String text = "";
            for (int i = 0; i<list.length;i++) {
                String s = list[i];
                if (!TextUtils.isEmpty(s)) {
                    if (i != list.length-1)
                    text = text + s + "\n";
                    else
                    text = text + s;
                }
            }
            if (!TextUtils.isEmpty(text)){
                tv.setText(text);
                toast.setView(root);
                toast.show();
            }
        }catch (Exception e){

        }
    }
}
