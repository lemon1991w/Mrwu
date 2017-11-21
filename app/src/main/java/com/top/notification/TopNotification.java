package com.top.notification;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.main.functionlistsdemo.R;
import com.top.notification.library.ConnectionStatusView;
import com.top.notification.library.Status;

/**
 * Created by ZJHL on 2017/2/24.k
 * 屏幕顶部通知栏
 */

public class TopNotification extends AppCompatActivity implements View.OnClickListener {

    private ConnectionStatusView statusView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_notification);
        initView();

    }

    private void initView() {
        statusView = (ConnectionStatusView) findViewById(R.id.status);
        Button success = (Button) findViewById(R.id.btn_success);
        Button error = (Button) findViewById(R.id.btn_error);
        Button loadding = (Button) findViewById(R.id.btn_connection);
        Button idle = (Button) findViewById(R.id.btn_idle);

        success.setOnClickListener(this);
        error.setOnClickListener(this);
        loadding.setOnClickListener(this);
        idle.setOnClickListener(this);

//        Cursor cursor =  getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
//        if (cursor !=null){
//            while (cursor.moveToNext()){
//               String displayName =  cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            }
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_success:
                statusView.setStatus(Status.COMPLETE);
                break;
            case R.id.btn_error:
                statusView.setStatus(Status.ERROR);
                break;
            case R.id.btn_connection:
                statusView.setStatus(Status.LOADING);
                break;
            case R.id.btn_idle:
                statusView.setStatus(Status.IDLE);
                break;
        }
    }
}
