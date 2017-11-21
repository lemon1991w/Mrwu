package com.keyboard.custom.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.keyboard.custom.adapter.KeyBoardAdapter;
import com.main.functionlistsdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZJHL on 2016/10/14.
 * 密码输入框
 *
 */

public class PasswordView extends RelativeLayout {

    Context mContext;
//苹果安全问题 您的理想工作 软件开发  年轻时最好朋友 朱晓成  第一次飞机 北京  锁屏密码 159357852
    
    private VirtualKeyboardView virtualKeyboardView;

    private TextView[] tvList; //用数组保存6个文本

    private ImageView[] ivList; //用图片保存6个黑圆圈

    private GridView gridView;

    private ImageView ivCancel;

    private ArrayList<Map<String,String>> valueList;

    private int currentIndex = -1 ; //用来记录当前密码格位置

    public PasswordView(Context context) {
        this(context,null);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        View view = View.inflate(context,R.layout.layout_popup_bottom,null);

        virtualKeyboardView = (VirtualKeyboardView) view.findViewById(R.id.virtualKeyboardView);
        gridView = virtualKeyboardView.getGridView();
        ivCancel = (ImageView) view.findViewById(R.id.img_cancel);
        initValueList();
        initView(view);
        setupView();
        addView(view);
    }

    private void initView(View view) {
        tvList = new TextView[6];
        ivList = new ImageView[6];

        //找到数组对应的TextView
        tvList[0] = (TextView) view.findViewById(R.id.tv_pass1);
        tvList[1] = (TextView) view.findViewById(R.id.tv_pass2);
        tvList[2] = (TextView) view.findViewById(R.id.tv_pass3);
        tvList[3] = (TextView) view.findViewById(R.id.tv_pass4);
        tvList[4] = (TextView) view.findViewById(R.id.tv_pass5);
        tvList[5] = (TextView) view.findViewById(R.id.tv_pass6);

        //找到数组对应的ImageView
        ivList[0] = (ImageView) view.findViewById(R.id.img_pass1);
        ivList[1] = (ImageView) view.findViewById(R.id.img_pass2);
        ivList[2] = (ImageView) view.findViewById(R.id.img_pass3);
        ivList[3] = (ImageView) view.findViewById(R.id.img_pass4);
        ivList[4] = (ImageView) view.findViewById(R.id.img_pass5);
        ivList[5] = (ImageView) view.findViewById(R.id.img_pass6);

    }
    //这里我们使用的是自定义键盘，因为第10个不是数字 所以显示空白
    private void initValueList() {
        valueList = new ArrayList<>();
        //初始化键盘上的数字
        for (int i=1; i<13; i++){
            Map<String,String> map = new HashMap<>();
            if ( i < 10){
                map.put("name",String.valueOf(i));
            }else if (i == 10){
                map.put("name","");
            }else if (i == 11){
                map.put("name",String.valueOf(0));
            }else if (i == 12){
                map.put("name","");
            }
            valueList.add(map);
        }
    }

    private void setupView() {
        // 这里、重新为数字键盘gridView设置了Adapter
        KeyBoardAdapter keyBoardAdapter = new KeyBoardAdapter(mContext,valueList);
        gridView.setAdapter(keyBoardAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < 11 && position != 9){  //点击0~9的按钮
                       if (currentIndex >= -1 && currentIndex < 5){  //判断输入位置，小心数组越界
                            ++currentIndex;
                           tvList[currentIndex].setText(valueList.get(position).get("name"));
                           tvList[currentIndex].setVisibility(View.GONE);
                           ivList[currentIndex].setVisibility(View.VISIBLE);
                       }
                }else{
                      if (position == 11){   //退格键
                           if (currentIndex -1 >= - 1){  //判断是否删除完毕
                               tvList[currentIndex].setText("");

                               tvList[currentIndex].setVisibility(View.VISIBLE);
                               ivList[currentIndex].setVisibility(View.INVISIBLE);
                               currentIndex--;
                           }else{
                               Toast.makeText(mContext, "删除完毕 请重新输入密码！", Toast.LENGTH_SHORT).show();
                           }
                      }
                }
            }
        });
    }

    //设置监听方法 在第6位输入完成后触发 索引从0开始
    public void setOnFinishInput(final onFinishPasswordListener pass){
          tvList[5].addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

              }

              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {

              }

              @Override
              public void afterTextChanged(Editable s) {
                  if (s.toString().length() == 1){
                      String strPassword = "";  //每次触发都要把之前的数据置为空，以免删除增加造成数据混乱
                      for (int i = 0 ; i< 6; i++){
                          strPassword +=  tvList[i].getText().toString().trim();
                      }
                      pass.inputFinish(strPassword);
                  }
              }
          });
    }

    public VirtualKeyboardView getVirtualKeyboardView() {

        return virtualKeyboardView;
    }

    public ImageView getImgCancel() {
        return ivCancel;
    }

    //定义接口，文本实现这个接口，把输入的密码传递到方法里提供给调用者
    public interface onFinishPasswordListener{
        void inputFinish(String password);
    }
}
