package com.recording.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.czt.mp3recorder.MP3Recorder;
import com.main.functionlistsdemo.R;
import com.utils.ToastUtil;
import com.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

import butterknife.InjectView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wusourece on 2017/7/11.
 * 录音测试
 */

public class RecordingTest extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    @InjectView(R.id.btn_recording)
    Button btnRecording;
    @InjectView(R.id.btn_play)
    Button btnPlay;

    MP3Recorder mRecorder;

    public static final String RECORDER_URL = "http://112.74.33.74/platform/file/audioFileUpload";

    File file;
    private String url;
    private MediaPlayer mediaPlayer;

    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_test);
        initView();
    }

    private void initView() {
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnRecording = (Button) findViewById(R.id.btn_recording);
        btnRecording.setOnTouchListener(this);
        btnPlay.setOnClickListener(this);

        file = new File(Environment.getExternalStorageDirectory(), "voice"+".mp3");
        mRecorder = new MP3Recorder(file);

        AudioManager am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);

        //设置音量为最大
//        am.setStreamVolume(AudioManager.STREAM_MUSIC,am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),AudioManager.FLAG_PLAY_SOUND);
        sp = getSharedPreferences("test", MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_recording:
                ToastUtil.showToast(this, "recording");
                break;
            case R.id.btn_play:
                 ToastUtil.showToast(RecordingTest.this,"play");
                //  MediaPlayer这个对象要在这里进行创建  进来的时候创建会报非法状态异常 mediaplayer api中用到了jni
                // 出现这个问题的原因是因为MediaPlayer和native对象的状态不一致导致的
                  mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("======down", "=======");
                try {
                    mRecorder.start();
                    btnRecording.setText("松开发送...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("======move", "=======");

                break;
            case MotionEvent.ACTION_UP:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                uploadRecording();
                        Log.e("======up1", "=======");
                    }
                },500);
                mRecorder.stop();
                btnRecording.setText("开始语音");
                Log.e("======up2", "=======");
                break;
        }
        return false;
    }

    private void uploadRecording() {
        Utils.executeThread(new Runnable() {
            @Override
            public void run() {

                Log.e("=====file", file.getAbsolutePath() + "");
               RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);
               RequestBody requestBody = new MultipartBody.Builder()
                       .setType(MultipartBody.FORM)
                       .addFormDataPart("audioFile",file.getName(),fileBody).build();

                Request request = new Request.Builder().url(RECORDER_URL).post(requestBody).build();

                OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
                OkHttpClient okHttpClient = httpBuilder.connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS).build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("=====e", e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String str = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(str);

                            if (jsonObject.has("url")){
                                  url =  jsonObject.getString("url");
                                Log.e("=====success", url);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });
    }
}