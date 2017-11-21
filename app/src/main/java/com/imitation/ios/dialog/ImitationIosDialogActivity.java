package com.imitation.ios.dialog;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.imitation.ios.dialog.utils.ActionSheetDialog;
import com.imitation.ios.dialog.utils.AlertDialog;
import com.main.functionlistsdemo.R;
import com.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL on 2016/11/3.
 */

public class ImitationIosDialogActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.btn_photo)
    Button btnPhoto;
    @InjectView(R.id.btn)
    Button btnDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_ios_dialog);
        ButterKnife.inject(this);
        initEventListener();
    }

    private void initEventListener() {
        btnPhoto.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case   R.id.btn_photo:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true) //ture为按返回键有效
                            .setCanceledOnTouchOutside(true) //true为让dialog消失
                        .addSheetItem("打开相册", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                ToastUtil.showToast(ImitationIosDialogActivity.this,"暂未开通！");
                            }
                        }).addSheetItem("开启相机", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                }).addSheetItem("分享到朋友圈", ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                }).addSheetItem("清除缓存", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                       new  Handler().postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               ToastUtil.showToast(ImitationIosDialogActivity.this,"清除成功");
                           }
                       },1000);
                    }
                }).show();
                 break;
             case    R.id.btn:
                 new AlertDialog(this).builder().setTitle("是否退出当前账号？")
                         .setMsg("再连续登陆5天即可成为超级star,退出则成就清零，是否退出？")
                         .setPositiveButton("确认退出", new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {

                             }
                         }).setNegativeButton("取消", new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                     }
                 }).show();
                 break;
         }
    }
}
