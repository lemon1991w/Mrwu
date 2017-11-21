package com.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Debug;

import com.utils.FileCache;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZJHL on 2016/11/9.
 * 全局异常抓取
 */

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final SimpleDateFormat mFormater;

    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    MyApplication application;

    /**
     * @param application 上下文
     * @brief 构造函数
     * @details 获取系统默认的UncaughtException处理器，设置该CrashHandler为程序的默认处理器 。
     */
    public MyUncaughtExceptionHandler(MyApplication application) {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mFormater = new SimpleDateFormat("yyyy.MM.dd_HH-mm-ss");
        this.application = application;
    }

    public static void with(MyApplication application) {
        new MyUncaughtExceptionHandler(application);
    }

    /**
     * @brief 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        FeelApplication.getInstance().finishAllActivity();
        // 如果用户没有处理则让系统默认的异常处理器来处理
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            // 等待会后结束程序
            try {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                }

                Intent intent = new Intent(application, Class.forName("com.zhiyun.feel.activity.MainActivity"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                PendingIntent restartIntent = PendingIntent.getActivity(
                        application.getApplicationContext(), 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                //退出程序
                AlarmManager mgr = (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                        restartIntent); // 1秒钟后重启应用

                application.finishAllActivity();
                mDefaultHandler.uncaughtException(thread, ex);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

            } catch (Exception e) {
            }
        }

    }

    /**
     * @param ex 异常
     *           [url=home.php?mod=space&uid=7300]@return[/url] true：如果处理了该异常信息；否则返回false。
     * @brief 自定义错误处理，收集错误信息
     * @details 发送错误报告等操作均在此完成
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return true;
        }
        //保存错误信息到文件中
        saveCrashInfoToFile(ex);
        return true;
    }

    /**
     * @param ex 异常
     * @brief 保存错误信息到文件中
     */
    private void saveCrashInfoToFile(Throwable ex) {
        final Date date = new Date(System.currentTimeMillis());
        final String dateStr = mFormater.format(date);

        final String appPath = FileCache.getRootDirectory(application);
        if (appPath != null) {
            final String logPath = appPath + File.separator + "Unhandled";
            final String filePath = logPath + File.separator + "Ex." + dateStr + ".txt";

            final Writer writer = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);

            final String trace = writer.toString();
            printWriter.close();

            try {
                final File dirFile = new File(logPath);
                if (dirFile.exists() == false) {
                    if (!dirFile.mkdirs()) {
                    }
                }

                final String hprof = logPath + File.separator + "Ex." + dateStr + ".hprof";
                final File hprofFile = new File(hprof);

                Debug.dumpHprofData(hprofFile.getAbsolutePath());

                BufferedWriter buffer = new BufferedWriter(new FileWriter(filePath));
                buffer.write(trace);
                buffer.flush();
                buffer.close();
            } catch (IOException e) {
            }
        }
    }

}
