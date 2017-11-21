package com.camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.date.and.nimaiton.MainListActivity;
import com.github.nkzawa.emitter.Emitter;
import com.main.functionlistsdemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;


import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL on 2017/3/2.
 * 拍照并进行裁剪
 */

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.btn_photo_album)
    Button photoAlbum;  //相册id
    @InjectView(R.id.btn_take_photo)
    Button takePhoto;   //照相机id
    @InjectView(R.id.iv_picture)
    ImageView picture;  //图片展示的imageView

    @InjectView(R.id.call)
    Button call;

    public static int TAKE_PHOTO_REQUEST_CODE = 1; //拍照
    public static int PHOTO_REQUEST_CUT = 2; //裁切
    public static int PHOTO_REQUEST_GALLERY = 3; //相册
    public Uri imageUri;  //图片路径


    private OutputStream out = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.inject(this);
        initView();


//            IO.Options options = new IO.Options();
//            options.port=9092;
//            Socket socket = IO.socket("http:112.74.33.74:9092/");
//            socket.connect();
//            Log.e("=====尝试连接","======");
//            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//                    Log.e("====== 连接成功",""+args[0]);
//                }
//            });



        }


    private void initView() {
        takePhoto.setOnClickListener(this);
        photoAlbum.setOnClickListener(this);
        call.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_take_photo:  //开启相机
                //把获取的文件转成Uri路径
                imageUri = Uri.fromFile(getImageStoragePath(CameraActivity.this));
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //指定拍照后的存储路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
                break;
            case R.id.btn_photo_album:  //打开图册
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(i, PHOTO_REQUEST_GALLERY);
                break;
            case R.id.call:
                Uri uri = Uri.parse("tel:18600054814");
                Intent intent2 = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent2);
        }
    }

    /**
     * 设置图片保存路径
     *
     * @return
     */
    private File getImageStoragePath(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "temp.jpg");
            return file;
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //当没有请求码的时候直接return
        if (resultCode == 0) {
            return;
        }

        if (requestCode == TAKE_PHOTO_REQUEST_CODE) { //拍照的请求码 路径不为null时 执行裁剪操作
            if (imageUri != null) {
                startPhotoZoom(imageUri);    //进行图片裁剪
            }
        }

        if (requestCode == PHOTO_REQUEST_CUT) {  //裁剪的请求码 获取裁剪后的图片
            if (data != null) {
                imageUri = data.getData();
                Bitmap bitmap = decodeUriBitmap(imageUri);  //把uri转成bitmap
                picture.setImageBitmap(bitmap);
            }
        }

        /**
         * 如果点击操作后再点击屏幕 当前操作被取消了 这个方法还是会被执行到 会传递data进行裁剪
         * 这时候的data为null 所以进入页面时 先判断是否是空 空就直接return
         */

        if (data == null){
            return;
        }

        if (requestCode == PHOTO_REQUEST_GALLERY) {  //开启相册 选好照片后 启动裁剪页面
            startPhotoZoom(data.getData());
        }
    }

    /**
     * 把uri转成bitmap
     * @param uri
     * @return
     */
    private Bitmap decodeUriBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            //跳转到截图页面 用户可能也会取消 所以要判断是否为空 取消了就直接返回null
            if (uri != null) {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 调用系统裁剪
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP"); //Action 为剪裁

        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 3);
        intent.putExtra("aspectY", 2);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);

        //设置了true的话直接返回bitmap，可能会很占内存
        intent.putExtra("return-data", false);
        //设置输出的格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //设置输出的地址
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //不启用人脸识别
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

}
