package com.keyboard.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.keyboard.custom.view.VirtualKeyboardView;
import com.main.functionlistsdemo.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ZJHL on 2016/10/13.
 */

public class NormalKeyBoardActivity extends AppCompatActivity {
    private RelativeLayout rlContent; //中间空白处
    private VirtualKeyboardView virtualKeyboardView; //自定义虚拟键盘
    private GridView gridView;
    private ArrayList<Map<String, String>> valueList; //存放键盘数字的list
    private EditText textAmount;    //输入框
    private Animation enterAnim;    //键盘进入的动画
    private Animation exitAnim;     //键盘出去的动画

    private boolean isShow = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_keyboard);
        initAnimation();
        initView();
        valueList = virtualKeyboardView.getValueList();
        isShow = true;
    }

    private void initAnimation() {
        enterAnim = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
        exitAnim = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);
    }

    private void initView(){
        rlContent = (RelativeLayout) findViewById(R.id.rl_content);
        textAmount = (EditText) findViewById(R.id.textAmount);

        // 设置不调用系统键盘
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            textAmount.setInputType(InputType.TYPE_NULL);
        } else {
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                //android 安卓4.0后屏蔽输入法时候会有光标问题  通过反射解决
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(textAmount, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        virtualKeyboardView = (VirtualKeyboardView) findViewById(R.id.virtualKeyboardView);
        virtualKeyboardView.getLayoutBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow == true) {
                    virtualKeyboardView.startAnimation(exitAnim);
                    virtualKeyboardView.setVisibility(View.GONE);
                    isShow = false;
                }
            }
        });

        //点击空白处隐藏键盘
        rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (isShow == false){
                  return;
              }else{
                  virtualKeyboardView.startAnimation(exitAnim);
                  virtualKeyboardView.setVisibility(View.GONE);
              }
            }
        });

        gridView = virtualKeyboardView.getGridView();
        gridView.setOnItemClickListener(onItemClickListener);

        textAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                virtualKeyboardView.setFocusable(true);
                virtualKeyboardView.setFocusableInTouchMode(true);

                virtualKeyboardView.startAnimation(enterAnim);
                virtualKeyboardView.setVisibility(View.VISIBLE);
            }
        });
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if (position < 11 && position != 9) {    //点击0~9按钮

                String amount = textAmount.getText().toString().trim();
                amount = amount + valueList.get(position).get("name");

                textAmount.setText(amount);

                Editable ea = textAmount.getText();
                textAmount.setSelection(ea.length());
            } else {

                if (position == 9) {      //点击.键
                    String amount = textAmount.getText().toString().trim();
                    if (!amount.contains(".")) {
                        amount = amount + valueList.get(position).get("name");
                        textAmount.setText(amount);

                        Editable ea = textAmount.getText();
                        textAmount.setSelection(ea.length());
                    }
                }

                if (position == 11) {      //点击退格键
                    String amount = textAmount.getText().toString().trim();
                    if (amount.length() > 0) {
                        amount = amount.substring(0, amount.length() - 1);
                        textAmount.setText(amount);

                        Editable ea = textAmount.getText();
                        textAmount.setSelection(ea.length());
                    }
                }
            }
        }
    };

}
