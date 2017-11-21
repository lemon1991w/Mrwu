package com.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 文件缓存处理
 */
public class FileCache {

    /**
     * 初始化文件缓存文件夹
     *
     * @param context
     */
    public static void init(final Context context) {
        /**
         * 网络图片缓存，LRU算法控制
         */
        mkDirs(getImageCacheDir(context));
        /**
         * 业务图片缓存，业务需求
         */
        mkDirs(getPhotoDir(context));
        /**
         * 数据缓存
         */
        mkDirs(getDataCacheDir(context));
        /**
         * 临时文件夹
         */
        mkDirs(getTempDir(context));

        //每次进入系统清理一下剪裁缓存
        Utils.executeThread(new Runnable() {
            @Override
            public void run() {
                try {
                    File[] imgCachedFiles = new File(getTempDir(context)).listFiles();
                    if (imgCachedFiles != null) {
                        for (File f : imgCachedFiles) {
                            f.delete();
                        }
                    }
                } catch (Throwable e) {
                }
            }
        });
    }

    /**
     * 创建文件夹
     *
     * @param dirPath
     */
    public static void mkDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) file.mkdirs();
    }

    /**
     * 获取image路径，网络图片缓存目录，LRU算法控制
     *
     * @param context
     * @return
     */
    public static String getImageCacheDir(Context context) {
        return getRootDirectory(context) + "/image/";
    }

    /**
     * 业务图片缓存区，但是不会出现在系统相册
     *
     * @param context
     * @return
     */
    public static String getPhotoDir(Context context) {
        return getRootDirectory(context) + "/photo/";
    }

    /**
     * 获取数据缓存路径
     *
     * @param context
     * @return
     */
    public static String getDataCacheDir(Context context) {
        return getRootDirectory(context) + "/data/";
    }

    /**
     * 剪裁的存储路径一般都是临时的，每次进入启动feel清理一次
     *
     * @param context
     * @return
     */
    public static String getTempDir(Context context) {
        return getRootDirectory(context) + "/temp/";
    }

    /**
     * 获取临时文件path
     *
     * @param suffix 后缀名
     * @return
     */
    public static String getTempFilePath(Context context, String suffix) {
        int random = (int) (Math.random() * 100000);
        return getTempDir(context) + "temp_" + random + "_" + System.currentTimeMillis() + "." + suffix;
    }

    /**
     * 获取根路径
     *
     * @param context
     * @return
     */
    public static String getRootDirectory(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            final String cacheDir = "/Android/data/" + context.getPackageName();
            return Environment.getExternalStorageDirectory() + cacheDir;
        } else {
            String path = null;
            File cacheDir = context.getCacheDir();
            if (cacheDir.exists()) path = cacheDir.getAbsolutePath();
            return path;
        }
    }
}
