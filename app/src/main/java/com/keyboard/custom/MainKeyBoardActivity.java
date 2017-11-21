package com.keyboard.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.main.functionlistsdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ZJHL on 2016/10/13.
 */

public class MainKeyBoardActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.btn_custom_keyboard)
    Button btnCustomKeyboard;
    @InjectView(R.id.btn_pay_keyboard)
    Button btnPayKeyboard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_main_layout);
        ButterKnife.inject(this);
        initEventListener();
    }

    private void initEventListener() {
        btnCustomKeyboard.setOnClickListener(this);
        btnPayKeyboard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case   R.id.btn_custom_keyboard:
                  startIntent(NormalKeyBoardActivity.class);
                  break;
              case   R.id.btn_pay_keyboard:
                  startIntent(PaymentKeyboardActivity.class);
                  break;
          }
    }

    private void startIntent(Class clazz){
        startActivity(new Intent(this,clazz));
    }
}
