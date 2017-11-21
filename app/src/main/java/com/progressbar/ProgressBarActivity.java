package com.progressbar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.main.functionlistsdemo.R;
import com.progressbar.view.CBProgressBar;

public class ProgressBarActivity extends Activity {
    private static final int UPDATE_PROGRESS=0;
    boolean isDownloading;
    boolean stop;
    private Button btnDownload;
    CBProgressBar cbProgress,cbProgress2,cbProgress3 ;
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PROGRESS:
                    cbProgress.setProgress(msg.arg1);
                    cbProgress2.setProgress(msg.arg1);
                    cbProgress3.setProgress(msg.arg1);
                    if(msg.arg1==100){
                        isDownloading = false;
                        btnDownload.setText("下载");
                        Toast.makeText(ProgressBarActivity.this, "下载完成！", Toast.LENGTH_SHORT).show();
                    }
                    break;

                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        cbProgress = (CBProgressBar) this.findViewById(R.id.my_progress);
        cbProgress2 = (CBProgressBar) this.findViewById(R.id.my_progress2);
        cbProgress3 = (CBProgressBar) this.findViewById(R.id.my_progress3);
        cbProgress.setMax(100);
        cbProgress2.setMax(100);
        cbProgress3.setMax(100);
        btnDownload =  (Button) this.findViewById(R.id.btn_download);
        btnDownload.setText("下载");
        btnDownload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isDownloading){
                    stop = false;
                    isDownloading = true;
                    btnDownload.setText("停止");
                    downloading(cbProgress);
                    downloading(cbProgress2);
                    downloading(cbProgress3);
                }else{
                    isDownloading = false;
                    stop = true;
                    btnDownload.setText("下载");
                }
            }
        });
    }

    private void downloading(CBProgressBar cbProgress){

        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while(!stop){
                    if(progress>=100){
                        break;
                    }
                    Message msg = handler.obtainMessage();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress+=1;
                    msg.what= UPDATE_PROGRESS;
                    msg.arg1 = progress;
                    msg.sendToTarget();
                }
                progress = 0;
            }
        }).start();

    }
}
