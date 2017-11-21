package com.notification.bar;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.base.News;
import com.main.functionlistsdemo.MainActivity;
import com.main.functionlistsdemo.R;
import com.utils.DataBases;
import com.utils.VibratorUtil;

import org.litepal.tablemanager.Connector;

/**
 * Created by ZJHL on 2017/1/11.
 * 通知栏
 */

public class MainNotificationBar extends AppCompatActivity{
    /** Notification管理 */
    public NotificationManager mNotificationManager;

    private Button mNotification;
    private NotificationCompat.Builder mBuilder;
    int notifyId = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_bar);
        mNotification = (Button) findViewById(R.id.btn_notification);
//        initDataBase();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
//        initView();
        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        Log.e("====== memory ",heapSize+"MB");

        mNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIntentActivityNotify();
            }
        });

    }

    private void initDataBase() {
        SQLiteOpenHelper dbHelper = new DataBases(this,"demo.db",null,2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    private void initView() {
        SQLiteDatabase db =  Connector.getDatabase();
        SQLiteOpenHelper helper = new DataBases(this,"demo.db",null,2);
        SQLiteDatabase db2 = helper.getWritableDatabase();

        SQLiteDatabase database  = Connector.getDatabase();

    }

    /**
     * 显示通知栏点击跳转到指定Activity
     */
    private void showIntentActivityNotify() {
        VibratorUtil.Vibrate(MainNotificationBar.this,200);
        mBuilder.setAutoCancel(true) //点击后让通知消失
        .setContentTitle("新能源介绍")
        .setContentText("查看详情")
        .setSmallIcon(R.drawable.gwc)
        .setWhen(System.currentTimeMillis());// 通知产生的时间，会在通知信息里显示
        mBuilder.setLights(Color.RED,1000,1000).build();
        //点击的意图ACTION是跳转到Intent
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pengdingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pengdingIntent);
        mNotificationManager.notify(notifyId,mBuilder.build());
    }
}

