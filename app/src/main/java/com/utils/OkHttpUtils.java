package com.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ZJHL on 2016/10/26.
 *  OkHttp 网络连接工具类 基于3.0的使用 3.0相比2.0改动较大，使用了很多建造者模式
 *    success 中的result 已通过mHandler发送到主线程，非异步线程，耗时操作需要另开线程
 *
 */

public class OkHttpUtils {

    private static OkHttpUtils mInstance;
    private OkHttpClient.Builder mOkHttpClient;
    private Handler mHandler;

    private OkHttpUtils(){
        /**
         * 构建初始化 okhttpclient
         */
        mOkHttpClient = new OkHttpClient.Builder();
        /**
         *设置连接的超时时间 10秒
         */
        mOkHttpClient.connectTimeout(10, TimeUnit.SECONDS).build();
        /**
         *设置响应超时时间  10秒
         */
        mOkHttpClient.writeTimeout(10, TimeUnit.SECONDS).build();
        /**
         * 设置请求超时时间
         */
        mOkHttpClient.readTimeout(10, TimeUnit.SECONDS).build();
        /**
         * 允许使用cookie进行缓存
         */
        mOkHttpClient.cookieJar(new CookiesManger()).build();
        /**
         * 获取主线程的handler
         */
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 使用单例模式构造对象 保证对象在内存中的唯一性
     */
      private synchronized static OkHttpUtils getInstance(){
          if (mInstance == null){
              mInstance = new OkHttpUtils();
          }
          return mInstance;
      }

    /**
     * get的构造
     * callback 结果回调的方法
     */
    private void getRequest(String url, final ResultCallback callback){
         final Request request = new Request.Builder().url(url).build();
         executeResult(request,callback);
    }

    /**
     * 构造post 请求
     * @param url 请求的url
     * @param callback 结果回调的方法
     * @param params 请求参数
     */
    private void postRequest(String url, Map<String,String> params, final ResultCallback callback){
        Request request = buildPostRequest(url,params);
        executeResult(request,callback);
    }

    /**
     * 加入调度 执行请求结果的回调
     */
    private void executeResult(Request request,final ResultCallback callback){
         mOkHttpClient.build().newCall(request).enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
                 sendFailCallback(callback, e);
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 try {
                     //把获取到的结果(body) 转为字符串
                        String str = response.body().string();
                     if (callback.mType == String.class){
                         sendSuccessCallBack(callback,str);
                     }else{

                     }
                 }catch (Exception e){
                 }
             }
         });
    }

    /**
     * 发送失败的回调
     * @param callback
     * @param e
     */
    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    /**
     * 发送成功的调 enqueue是异步的 所以用handler发送到UI线程即主线程进行处理
     * @param callback
     * @param obj
     */
    private void sendSuccessCallBack(final ResultCallback callback, final Object obj) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }
        });
    }

    /**
     * 构造post请求 map是bod
     * @param url  请求url
     * @param map 请求的参数
     * @return 返回 Request
     */
    private Request  buildPostRequest(String url, Map<String,String> map) {
        //创建请求的body 以键值对的方式
        FormBody.Builder builder = new FormBody.Builder();
        //遍历map
         if (map != null){
             for (Map.Entry<String,String> entry : map.entrySet()){
                 builder.add(entry.getKey(),entry.getValue());
             }
         }
        //得到要构造的post body
        RequestBody body = builder.build();
        //直接return请求的url 、 参数
       return new Request.Builder().url(url).post(body).build();
    }

    /********  对外暴露调用 方法******/
    /**
     * get请求  获取实例 发起请求
     * @param url  请求url
     * @param callback  请求回调
     */
    public static void get(String url, ResultCallback callback){
          getInstance().getRequest(url,callback);
    }

    /**
     * post请求  参数为map集合
     * @param url
     * @param callback
     * @param
     */
     public static void post(String url, Map<String,String> params, ResultCallback callback){
         getInstance().postRequest(url,params,callback);
     }

    /**
     * 设置请求头，以备日后服务端可以根据头部类型返回客户端信息，提供更好的响应
     * 在Post请求方法后面进行追加
     * @param headersParams
     * @
     */
     public static Headers setHeaders(Map<String,String> headersParams){
          Headers headers = null;
         okhttp3.Headers.Builder headersBuilder = new okhttp3.Headers.Builder();

         if (headersParams != null){
             Iterator<String> iterator = headersParams.keySet().iterator();
             String key = "";
             while (iterator.hasNext()){
                 key = iterator.next().toString();
                 headersBuilder.add(key,headersParams.get(key));

             }
         }
        headers =  headersBuilder.build();
         return headers;
     }

    /**
     * http请求回调类，回调方法在UI线程中执行
     */
    public static abstract class ResultCallback<T>{
        Type mType;

        public ResultCallback(){
            //谁调用就会得到调用者的类
            mType = getSuperclassTypeParameter(getClass());
        }

        //使用反射获取参数类型
        static Type getSuperclassTypeParameter(Class<?> subclass){
            Type superclass =  subclass.getGenericSuperclass(); //返回父类类型
            if (superclass instanceof Class){
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功的回调
         */
        public abstract void onSuccess(T response);

        /**
         * 请求失败的回调
         */
        public abstract void onFailure(Exception e);
    }




    /**
     *  使用CookieJar统一管理Cookie
     */
    public class CookiesManger implements CookieJar{

        private final HashMap<String,List<Cookie>> cookieStore = new HashMap<>();
        //key是String类型且为url的host部分
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
         cookieStore.put(url.host(),cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
           List<Cookie> cookie = cookieStore.get(url.host());
            return cookie!=null ? cookie: new ArrayList<Cookie>();
        }
    }
}
