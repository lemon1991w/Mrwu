package com.shopping.cart;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.main.functionlistsdemo.R;

/**
 * 购物车页面
 * @author admin
 * @name 巫维庭
 * @date 2016/06/08 
 */
public class MainShoppingCart extends Activity implements OnClickListener{
	
	private ImageView iv_back;
	private ListView listView;
	private CheckBox checkAll; //全选的checkbox
	private Button btSettleAccounts;
	private TextView tvTotalPrice;
	
	private List<ShoppingListBean> shoppingBeanList;
	
	private PriceBroadcastReceiver receiver;
	private boolean isAllCheckedClick = true;
	private ShoppingCartAdapter adapter; //购物车的adapter
    
	private float totalPrice = 0; //默认总计价格为0
	
	    public MainShoppingCart() {
		}
	    
	    //页面启动时注册广播
		@Override
 	    protected void onStart() {
			receiver = new PriceBroadcastReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction("price");   //勾选被添加后的产品价格
			filter.addAction("unchecked"); //被勾选后的商品
			filter.addAction("del_price"); //被删除的商品item
			filter.addAction("add_price"); 
			filter.addAction("subtract_price"); 
			this.registerReceiver(receiver, filter);
 	    	super.onStart();
 	    }
 	    
		//页面停止后注销广播
 	    @Override
 	    protected void onStop() {
 	    	this.unregisterReceiver(receiver);
 	    	super.onStop();
 	    }
      
	
         @Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_shopping_cart);
            initData();
         }

         /**
          * 初始化数据
          */
 	    private void initData() {
            iv_back = (ImageView) findViewById(R.id.iv_back);
            listView = (ListView) findViewById(R.id.lv_shopping_list);
            checkAll = (CheckBox) findViewById(R.id.check_all);
            btSettleAccounts = (Button) findViewById(R.id.bt_settle_accounts);
            tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
            
            iv_back.setOnClickListener(this);
            checkAll.setOnClickListener(this);
            btSettleAccounts.setOnClickListener(this);
            
            shoppingBeanList = new ArrayList<ShoppingListBean>();
            ShoppingListBean bean = new ShoppingListBean("高清智能镜头 为你增添色彩生活  数码类 你值得拥有",199,220,5); //1.产品信息 2.现价 3.原价 4.商品总数
            ShoppingListBean bean2 = new ShoppingListBean(" 放大你的生活色彩 为你增添色彩生活  只为你而来",210,239,9);
            ShoppingListBean bean3 = new ShoppingListBean(" 记录你的点滴生活 让你充满美好回忆  点滴生活只为你",180,215,2);
            
            shoppingBeanList.add(bean);
            shoppingBeanList.add(bean2);
            shoppingBeanList.add(bean3);
            
            adapter = new ShoppingCartAdapter(this, shoppingBeanList);
            listView.setAdapter(adapter);
            
            checkAll.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					
				}
			});
		}

   
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_back:
				finish();

				break;
			case R.id.check_all:
				checkAll.setButtonDrawable(R.drawable.checkbox_bg);
				adapter.allChecked(isAllCheckedClick);
				if (isAllCheckedClick) {
					isAllCheckedClick = false;
				}else{
					isAllCheckedClick = true;
				}
				break;
			
			case R.id.bt_settle_accounts:
				
				if (totalPrice>0) {
					Toast.makeText(MainShoppingCart.this, "将为您跳转到结算详情页面", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(MainShoppingCart.this, "请选择您要结算的商品", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}
		}
		
		class PriceBroadcastReceiver extends BroadcastReceiver{

			@Override
			public void onReceive(Context context, Intent intent) {
				switch (intent.getAction()) {
				case "price": 
					//计算checkbox多个item被选中的商品的数量
					btSettleAccounts.setText("结算(" + intent.getIntExtra("checkedcount", 0)+")"); //被选中的checkbox数量
					//设置商品总价
					totalPrice = totalPrice + intent.getFloatExtra("price", 0); 
					tvTotalPrice.setText(totalPrice + "");
					break;
				case "unchecked":  //计算checkbox多个item被选中取消后的价格
					btSettleAccounts.setText("结算("+ intent.getIntExtra("checkedcount", 0)+")");
					
					totalPrice = totalPrice - intent.getFloatExtra("price", 0);
					tvTotalPrice.setText(totalPrice + "");
					checkAll.setChecked(false);
				    break;
				case "del_price":
				
					float del_price = intent.getFloatExtra("del_price", 0);
					totalPrice = totalPrice - del_price;
                	tvTotalPrice.setText(totalPrice+"");				
				    break;
				case "add_price":  // 编辑页面数量添加后改变总价价格
			         float addPrice = intent.getFloatExtra("add_price", 0);
					  
					totalPrice = totalPrice + addPrice;
                    tvTotalPrice.setText(totalPrice + "");					
				    break;
				case "subtract_price": // 编辑页面数量添加后改变总价价格
					float subtractPrice =  intent.getFloatExtra("subtract_price", 0);
					
					totalPrice = totalPrice - subtractPrice;
					tvTotalPrice.setText(totalPrice +"");
				    break;
				default:
					break;
				}
			}
	   }
}
