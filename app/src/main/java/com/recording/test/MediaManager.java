package com.recording.test;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * MediaPlayer管理器
 * Created by haoran.shu on 2015/8/27.
 */
public class MediaManager {

    private static MediaPlayer player; // MediaPlayer播放音频
    private static MediaManager manager; // 静态的MediaManager
    private FileUtils fileUtils;

    /**
     * 默认的构造方法，构造MediaPlayer
     */
    private MediaManager() {
        if(player == null) {
            // 构造MediaPlayer
            player = new MediaPlayer();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset(); // 播放完成后,重置到初始(idle)状态
                }
            });
            player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mp.reset(); // 发生错误后,重置到初始状态
                    return false;
                }
            });
        }
        fileUtils = new FileUtils(1);
    }

    /**
     * 对外提供的初始化方法
     * @return  MediaManager
     */
    public static MediaManager getInstance() {
        if(manager == null) {
            manager = new MediaManager();
        }
        return manager;
    }

    /**
     * 播放音频文件
     * @param url  音频文件url地址
     */
    public void player(String url) {
        // 如果当前正在播放,则直接返回
        if(player.isPlaying()){
            Log.i("MediaManager", "MediaPlayer is playing……");
            return;
        }
        try {
            String path = fileUtils.exists(url); // 判断是否存在缓存文件
            if(path != null) { // 存在缓存文件
                // 直接播放缓存文件
                player.setDataSource(path); // 设置数据源
                player.prepare(); // 准备(File), 同步
                player.start(); // 播放音频文件
            }else { // 不存在音频缓存文件,则边存边播
                // 异步下载音频文件
                new AudioAsyncTask().execute(url);
                player.setDataSource(url); // 设置数据源为网络文件
                player.prepareAsync(); // 准备(InputStream), 异步
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        // 准备完成后, 开始播放音频文件
                        mp.start();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步下载音频文件
     */
    private class AudioAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(params[0]); // 构建URL
                // 构造网络连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 保存音频文件
                fileUtils.saveFile(conn.getInputStream());
                conn.disconnect(); // 断开网络连接
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 释放资源(一般在Activity的onDestroy()方法中调用)
     */
    public void release(){
        player.release(); // 释放资源
        player = null;
        manager = null;
        fileUtils = null;
    }
}
