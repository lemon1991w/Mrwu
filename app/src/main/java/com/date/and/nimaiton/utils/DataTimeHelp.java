package com.date.and.nimaiton.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/9/23.
 */
public class DataTimeHelp {

    //获取Calendar对象，用于获取当前时间
    private static final Calendar calendar = Calendar.getInstance();
    private static final int year = calendar.get(Calendar.YEAR);
    private static final int month = calendar.get(Calendar.MONTH);
    private static final int day = calendar.get(Calendar.DAY_OF_MONTH);
    private static final int hour = calendar.get(Calendar.HOUR_OF_DAY);
    private static final int minute = calendar.get(Calendar.MINUTE);

    public static void showTime(Context context, final DataCallBack dataCallBack){
        //实例化TimePickerDialog对象
        final TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            //选择完时间后会调用该回调函数
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time=(hourOfDay<10?"0"+hourOfDay:""+hourOfDay)+" : "+(minute<10?"0"+minute:""+minute);
                //设置TextView显示最终选择的时间
                    dataCallBack.getData(time);

            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    public static void showDate(Context context, final DataCallBack dataCallBack){
        //实例化DatePickerDialog对象
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            //选择完日期后会调用该回调函数
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //因为monthOfYear会比实际月份少一月所以这边要加1
                String time=year+"-"+(monthOfYear<10?"0"+monthOfYear:""+monthOfYear)+"-"+(dayOfMonth<10?"0"+dayOfMonth:""+dayOfMonth);
                dataCallBack.getData(time);
            }
        }, year, month, day);
        //弹出选择日期对话框
        datePickerDialog.show();
    }

    //将两个选择时间的dialog放在该函数中
    public static void  showDialogPicker(Context context, final DataCallBack dataCallBack) {
        final StringBuffer time = new StringBuffer();

        //实例化TimePickerDialog对象
        final TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            //选择完时间后会调用该回调函数
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.append(" "+(hourOfDay<10?"0"+hourOfDay:""+hourOfDay)+" : "+(minute<10?"0"+minute:""+minute));
                //设置TextView显示最终选择的时间
                dataCallBack.getData(time.toString());
            }
        }, hour, minute, true){
            // 重写onStop()
            @Override
            protected void onStop() {

            }
        };

        //实例化DatePickerDialog对象
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            //选择完日期后会调用该回调函数
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //因为monthOfYear会比实际月份少一月所以这边要加1
                time.append(year+"-"+(monthOfYear+1<10?"0"+monthOfYear:""+monthOfYear)+"-"+(dayOfMonth<10?"0"+dayOfMonth:""+dayOfMonth));
                //选择完日期后弹出选择时间对话框
                timePickerDialog.show();
            }
        }, year, month, day){
            // 重写onStop()
            @Override
            protected void onStop() {
                /**
                 *  //点击确定和取消按钮时，会出发onTimeSet；在dialog的onStop（比如dialog dismiss时）中，也调用了onTimeSet方法。
                 所以说复写对话框注掉supper.onStop()就行了，
                 */
            }
        };
        //弹出选择日期对话框
        datePickerDialog.show();


    }

}
