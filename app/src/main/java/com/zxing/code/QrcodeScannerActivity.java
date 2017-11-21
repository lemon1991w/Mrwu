package com.zxing.code;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.base.BaseActivity;
import com.google.zxing.Result;
import com.main.functionlistsdemo.R;
import com.utils.LoadingDialog;
import com.utils.MaterialDialog;
import com.utils.ToastUtil;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * @author  巫维庭
 * Created by ZJHL on 2017/3/6.
 * 二维码扫描
 */

public class QrcodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private Handler mScannerHandler = new Handler();
    /**
     * 正在处理中的加载框
     */
    private MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
//        setupActionBar(getResources().getString(R.string.qr_scanner),true,null,R.drawable.icon_return,false,null,R.drawable.icon_return);

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.qrcode_scanner_container);
        mScannerView =  new ZXingScannerView(this){
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                //返回一个自定义的扫描视图
                return new CustomViewFinderView(QrcodeScannerActivity.this);
            }
        };
        contentFrame.addView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideDialog();
        //开启扫描的回调
        mScannerView.setResultHandler(this);
        //开启相机
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideDialog();
        mScannerHandler.removeCallbacksAndMessages(null);  //清空回调和信息
        mScannerView.stopCamera();                         //停止相机
    }

    @Override
    public void handleResult(Result result) {

        //把扫描的结果传递给QrCodeAction 进行处理
        QrCodeAction.checkURI(this,result.getText());

        mMaterialDialog = LoadingDialog.showLoadingDialog(QrcodeScannerActivity.this,"正在处理请稍等...");
        mScannerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // If you would like to resume scanning, call this method below:
                mScannerView.resumeCameraPreview(QrcodeScannerActivity.this);
                hideDialog();
            }
        },2000);
    }

    /**
     * hide 加载框
     */
    private void hideDialog() {
        try {
            if (mMaterialDialog != null) {
                mMaterialDialog.dismiss();
                mMaterialDialog = null;
            }
        } catch (Throwable e) {
        }
    }
}
