package com.date.and.nimaiton.time;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/8/19.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private String date=null;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //得到Calendar类实例，用于获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //返回DatePickerDialog对象
        //因为实现了OnDateSetListener接口，所以第二个参数直接传入this
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    //实现OnDateSetListener接口的onDateSet()方法
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //这样子写就将选择时间的fragment和选择日期的fragment完全绑定在一起
        //使用的时候只需直接调用DatePickerFragment的show()方法
        //即可选择完日期后选择时间
        if(null==date){
            TimePickerFragment timePicker = new TimePickerFragment();
            timePicker.show(getFragmentManager(), "time_picker");
            //将用户选择的日期传到TimePickerFragment
            date = year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日";
            timePicker.setTime(date);
        }

    }
    @Override
    public void onStop() {
        super.onStop();
        /**
         *  //点击确定和取消按钮时，会出发onTimeSet；在dialog的onStop（比如dialog dismiss时）中，也调用了onTimeSet方法。
         所以说复写对话框注掉onStop就行了，
         */
    }
}
