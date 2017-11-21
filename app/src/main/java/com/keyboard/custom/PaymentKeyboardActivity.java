package com.keyboard.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.keyboard.custom.view.PopEnterPassword;
import com.main.functionlistsdemo.R;

/**
 * Created by ZJHL on 2016/10/14.
 */

public class PaymentKeyboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_key_board);
    }

    public void showPayKeyBoard(View view) {

        PopEnterPassword popEnterPassword = new PopEnterPassword(this);

        // 显示窗口
        popEnterPassword.showAtLocation(this.findViewById(R.id.layoutContent),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
    }
}
