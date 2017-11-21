package com.keyboard.custom.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.main.functionlistsdemo.R;

/**
 * Created by 巫维庭 on 2016/10/14.
 *   输入支付密码
 */

public class PopEnterPassword extends PopupWindow implements View.OnClickListener {

     private PasswordView pwdView;
     private View mMenuView;
     private Activity mContext;
     private RelativeLayout rlContentPayment;

     public PopEnterPassword(Activity context){
            super(context);
         this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         mMenuView  = inflater.inflate(R.layout.pop_enter_password,null);

         pwdView = (PasswordView) mMenuView.findViewById(R.id.pwd_view);

         rlContentPayment = (RelativeLayout) mMenuView.findViewById(R.id.rl_content_payment);
         rlContentPayment.setOnClickListener(this);

         //添加触发输入完成的回调监听
         pwdView.setOnFinishInput(new PasswordView.onFinishPasswordListener() {
             @Override
             public void inputFinish(String password) {
                 dismiss();
                 Toast.makeText(mContext, "支付成功 密码为: "+password, Toast.LENGTH_SHORT).show();
             }
         });

         //监听关闭按钮
         pwdView.getImgCancel().setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dismiss();
             }
         });

         //监听键盘上方的返回
         pwdView.getVirtualKeyboardView().getLayoutBack().setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dismiss();
             }
         });

         // 设置SelectPicPopupWindow的View
         this.setContentView(mMenuView);
         // 设置SelectPicPopupWindow弹出窗体的宽
         this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
         // 设置SelectPicPopupWindow弹出窗体的高
         this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
         // 设置SelectPicPopupWindow弹出窗体可点击
         this.setFocusable(true);
         // 设置SelectPicPopupWindow弹出窗体动画效果
         this.setAnimationStyle(R.style.pop_add_ainm);
         // 实例化一个ColorDrawable颜色为半透明
         ColorDrawable dw = new ColorDrawable(0x66000000);
         // 设置SelectPicPopupWindow弹出窗体的背景
         this.setBackgroundDrawable(dw);
     }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
