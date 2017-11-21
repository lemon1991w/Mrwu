package com.shopping.cart;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.main.functionlistsdemo.R;

/**
 * 购物车页面
 * @author admin
 * @name 小巫
 * @date 2016/06/08 
 */

public class ShoppingCartAdapter extends BaseAdapter{

	private Context context;
	private LayoutInflater inflater;
	private List<ShoppingListBean> entities;
	
	public boolean editOrFinish = true;
	
	private int productCount = 1; //产品的数量默认为1
	private int checkedcount = 0; //选中商品的数量（item的数量）
	
	private List<CheckBox> shops = new ArrayList<CheckBox>();  //商铺的checkbox
	private List<CheckBox> products = new ArrayList<CheckBox>(); //产品的checkbox
	private int position;
	
	public ShoppingCartAdapter(Context context,List<ShoppingListBean> entities){
		this.context = context;
		this.entities = entities;
		inflater = LayoutInflater.from(context);
	}	
	
	@Override
	public int getCount() {
		return entities.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
//		this.position = position;
		final ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.shopping_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.productInfo = (TextView) convertView.findViewById(R.id.tv_product_info); //产品介绍
			viewHolder.productPrice = (TextView) convertView.findViewById(R.id.tv_price); //产品价格
			viewHolder.tvPricePast = (TextView) convertView.findViewById(R.id.tv_price_past); //产品原价
			viewHolder.productPageCount = (TextView) convertView.findViewById(R.id.tv_product_count_up); // 产品信息页面购买数量
			viewHolder.tv_edit = (TextView) convertView.findViewById(R.id.tv_edit);  //编辑按钮
			viewHolder.ll_product_info = (LinearLayout) convertView.findViewById(R.id.ll_product_info);  //产品信息页面
			viewHolder.ll_eit_product = (LinearLayout) convertView.findViewById(R.id.ll_eit_product); //产品编辑页面
			viewHolder.tv_edit_product_count = (TextView) convertView.findViewById(R.id.tv_edit_product_count); //产品编辑页面数量
			viewHolder.tv_product_count_plus = (TextView) convertView.findViewById(R.id.tv_product_count_plus); //编辑页面的+号按钮
			viewHolder.tv_product_count_subtract = (TextView) convertView.findViewById(R.id.tv_product_count_subtract); //编辑页面的-号按钮
			viewHolder.bt_del_product = (Button) convertView.findViewById(R.id.bt_del_product);
			
			viewHolder.check_product = (CheckBox) convertView.findViewById(R.id.check_product);//产品checkbox勾选按钮
			viewHolder.check_shop = (CheckBox) convertView.findViewById(R.id.check_shop); //店铺checkbox勾选按钮
			shops.add(viewHolder.check_shop);
			products.add(viewHolder.check_product);
			
			viewHolder.productPricePass = (TextView) convertView.findViewById(R.id.tv_price_past); //产品页面原价控件
			viewHolder.productPricePass.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
			
			
		    convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		/**
		 * 通过集合动态设置数据
		 */
		viewHolder.productInfo.setText(entities.get(position).getProductInfo()+"");
		viewHolder.productPrice.setText(entities.get(position).getProductPrice()+"");
		viewHolder.tvPricePast.setText("¥\t"+entities.get(position).getOriginalPrice());
		viewHolder.productPageCount.setText(entities.get(position).getProductCount()+"");
		
		/**
		 * 点击edit(编辑)切换页面 进行编辑
		 */
		viewHolder.tv_edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				productCount = 1;
				 if (editOrFinish == true) {
					 viewHolder.tv_edit.setText("完成");
					 viewHolder.ll_product_info.setVisibility(View.GONE);
					 viewHolder.ll_eit_product.setVisibility(View.VISIBLE);
					 productCount = Integer.parseInt(viewHolder.productPageCount.getText()+"");
					 viewHolder.tv_edit_product_count.setText(productCount+"");
					 editOrFinish = false;
				 }else{
					 viewHolder.tv_edit.setText("编辑");
					 viewHolder.ll_product_info.setVisibility(View.VISIBLE);
					 viewHolder.ll_eit_product.setVisibility(View.GONE);
					 
					 //产品数量改变时改变全选价格
					 viewHolder.productPageCount.setText(viewHolder.tv_edit_product_count.getText()+"");
					 editOrFinish = true;
				 }
			}
		});
		
		/**
		 * 编辑页面的+号按钮点击事件
		 */
		
		viewHolder.tv_product_count_plus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				productCount = productCount + 1;
				viewHolder.tv_edit_product_count.setText(productCount+"");
				
				//当前产品被选中进行编辑的话 要把实时价格传给合计 
				if (products.get(position).isChecked()) {
					Intent intent = new Intent("add_price");
				    intent.putExtra("add_price", entities.get(position).getProductPrice());
				    context.sendBroadcast(intent);
				}
			}
		});
		
		/**
		 * 编辑页面的 - 号按钮点击事件
		 */
		
		viewHolder.tv_product_count_subtract.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//索引从0开始 实际>=2等同于1
				if (productCount >=1) {
					productCount = productCount - 1;
					viewHolder.tv_edit_product_count.setText(productCount+"");
					
					if (products.get(position).isChecked()) {
						Intent intent = new Intent("subtract_price");
						intent.putExtra("subtract_price", entities.get(position).getProductPrice());
						context.sendBroadcast(intent);
					}
				}else{
					Toast.makeText(context, "不能再减了", 0).show();
				}
			}
		});
		
		/**
		 * 删除商品item
		 */
		
		viewHolder.bt_del_product.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setMessage(entities.get(position).getProductInfo()+"");
                dialog.setTitle("确定删除该商品吗?").setPositiveButton("确定", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						entities.remove(position);
						shops.remove(position);
						products.remove(position);
						ShoppingListBean list = entities.get(position);
						float price = list.getProductCount() * list.getProductPrice();
						Intent intent = new Intent("del_price");
						intent.putExtra("del_price", price);
						context.sendBroadcast(intent);
						
						//删除后 把当前页面置为产品信息页面
						 if (editOrFinish == true) {
							 viewHolder.tv_edit.setText("完成");
							 viewHolder.ll_product_info.setVisibility(View.GONE);
							 viewHolder.ll_eit_product.setVisibility(View.VISIBLE);
							 editOrFinish = true;
						 }else{
							 viewHolder.tv_edit.setText("编辑");
							 viewHolder.ll_product_info.setVisibility(View.VISIBLE);
							 viewHolder.ll_eit_product.setVisibility(View.GONE);
							 editOrFinish = true;
						 }
						notifyDataSetChanged();
					}
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				}).create();
                dialog.show();
			}
		});
		
	    /**
	     * 产品页面checkbox勾选按钮
	     */
		viewHolder.check_product.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				buttonView.setButtonDrawable(R.drawable.checkbox_bg);
				//产品页面
				if (editOrFinish) {
//					buttonView.setTag(position);
					viewHolder.check_shop.setButtonDrawable(isChecked?R.drawable.icon_checked:R.drawable.icon_unchecked);
				    
					float price = new Float(viewHolder.productPrice.getText()+"");
					productCount = new Integer(viewHolder.productPageCount.getText()+"");
					float totalPrice = price * productCount;
					Intent intent = new Intent();
					
					//为true则加 为fals则减
					if (isChecked) {
						checkedcount = checkedcount+1;
						intent.setAction("price"); // 设置价格
						intent.putExtra("checkedcount", checkedcount); // 选择的item数量
						intent.putExtra("price", totalPrice); // 产品总价(产品单价*产品数量)
						context.sendBroadcast(intent);  //发送广播进行传值
					}else{
						checkedcount = checkedcount-1;
						intent.setAction("unchecked"); 
						intent.putExtra("checkedcount", checkedcount); 
						intent.putExtra("price", totalPrice); 
						context.sendBroadcast(intent);
					}
				}else{
					Toast.makeText(context, "请点编辑完成后再选择", 0).show();
				}
			}
		});
		
		return convertView;
	}
	
	    public void allChecked(boolean isChecked){
	    	for(CheckBox shop: shops){
	    		shop.setChecked(isChecked);
	    	}
	    	
	    	for(CheckBox product: products){
	    		product.setChecked(isChecked);
	    	}
	    }
    
	class ViewHolder{
		TextView productInfo;
		TextView productPrice;
		TextView tvPricePast;
		TextView productPageCount;
		TextView tv_edit;
		TextView tv_edit_product_count;
		TextView tv_product_count_plus;
		TextView tv_product_count_subtract;
		CheckBox check_product;
		CheckBox check_shop;
		TextView productPricePass;
		Button bt_del_product;
		
		LinearLayout ll_product_info;
		LinearLayout ll_eit_product;
	}
}
