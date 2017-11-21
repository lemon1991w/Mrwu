package com.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by ZJHL on 2016/11/1.
 */

public class JsonUtil {
    /**
     * 变成了private 如果想使用这个变量请使用 getGsonInstance 方法
     */
    private static Gson gson = null;

    //该类加载时执行
    static {
         gson = new GsonBuilder().create();
    }

    /**
     * 获取gson
     */
    public static Gson getGsonInstance(){
        if (gson == null){
            gson = new GsonBuilder().create();
        }
        return gson;
    }

    /**
     * 把java对象转成json
     */
    public static String convertToString(Object object){
        try {
            return  getGsonInstance().toJson(object);
        }catch (Exception e){
        }
        return null;
    }

    /**
     * 把json转成java对象
     */
    public static <T> T fromJson(String json, Type typeOfT){
        try {
             return getGsonInstance().fromJson(json,typeOfT);
        }catch (Exception e){
        }
        return null;
    }

}
