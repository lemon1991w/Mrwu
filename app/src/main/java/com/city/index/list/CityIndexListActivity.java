package com.city.index.list;

import  android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.promeg.pinyinhelper.Pinyin;
import com.main.functionlistsdemo.R;
import com.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ZJHL on 2017/2/15.
 * 城市索引列表
 */

public class CityIndexListActivity extends AppCompatActivity implements MySlideView.onTouchListener, CityAdapter.onItemClickListener {

    public static List<City> cityList = new ArrayList<>();  //汉字列表集合
    private Set<String> firstPinYin = new LinkedHashSet<>();  //首拼音
    public static List<String> pinyinList = new ArrayList<>(); //

    private PinyinComparator pinyinComparator;
    private MySlideView mySlideView;   //右边字母列表控件
    private CircleTextView circleText; //点击字母中间的提示文字

    private RecyclerView recyclerView;
    private CityAdapter adapter;
    private LinearLayoutManager layoutManager   ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_index_list);
        ActionBar bar = getSupportActionBar();
        if (bar != null){
            bar.hide();
        }
        initView();
    }

    private void initView() {
        cityList.clear();   //初始化前 擦除之前集合的数据
        firstPinYin.clear();
        pinyinList.clear();

        mySlideView = (MySlideView) findViewById(R.id.my_slide_view);
        circleText = (CircleTextView) findViewById(R.id.my_circle_view);
        pinyinComparator = new PinyinComparator();
        for (int i = 0; i<City.stringCitys.length;i++){
            City city = new City();
            city.setCityName(City.stringCitys[i]);
            city.setCityPinyin(transformPinYin(City.stringCitys[i])); //把转换好的拼音字母传给city
            cityList.add(city);
        }

        //进行比较排序
        Collections.sort(cityList,pinyinComparator);
        //遍历汉字城市列表 获取汉字的首拼音字母 添加到集合中
        for (City city: cityList){
            firstPinYin.add(city.getFirstPinYin().substring(0,1));
        }

        //遍历首字母集合,添加到拼音列表中
        for (String string: firstPinYin){
            pinyinList.add(string);
        }

        mySlideView.setListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_sticky_example);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CityAdapter(getApplicationContext(),cityList);
        adapter.setListener(this); //给recycleView item设置监听事件
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new StickyDecoration(this));  //设置索引条的属性
    }

    @Override
    public void showTextView(String textView, boolean dismiss) {
         if (dismiss){
             circleText.setVisibility(View.GONE);
         }else{
             circleText.setVisibility(View.VISIBLE);
             circleText.setText(textView);
         }

        int selectPosition = 0;
        for (int i = 0; i<cityList.size();i++){
            if (cityList.get(i).getFirstPinYin().equals(textView)){
                 selectPosition = i;
                  break;
            }
        }
        scrollPosition(selectPosition);
    }

    @Override
    public void itemClick(int position) {
        ToastUtil.showToast(getApplicationContext(),"你选择了:" + cityList.get(position).getCityName());
    }

    public class PinyinComparator implements Comparator<City> {
        @Override
        public int compare(City cityFirst, City citySecond) {
            return cityFirst.getCityPinyin().compareTo(citySecond.getCityPinyin());
        }
    }

    /**
     * 汉字转拼音
     * @param character
     * @return
     */
    public String transformPinYin(String character) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < character.length(); i++) {
            buffer.append(Pinyin.toPinyin(character.charAt(i)));
        }
        return buffer.toString();
    }

    /**
     * 滚动到当前的索引位置
     * @param index
     */
    private void scrollPosition(int index) {
        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        if (index <= firstPosition) {
            recyclerView.scrollToPosition(index);
        } else if (index <= lastPosition) {
            int top = recyclerView.getChildAt(index - firstPosition).getTop();
            recyclerView.scrollBy(0, top);
        } else {
            recyclerView.scrollToPosition(index);
        }
    }
}
