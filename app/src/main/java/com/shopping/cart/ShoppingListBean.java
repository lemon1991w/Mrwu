package com.shopping.cart;

import android.widget.ImageView;

public class ShoppingListBean {
      private ImageView productPicture;
      private String productInfo;
      private float productPrice;
      private float originalPrice;
      private int productCount;
      
      public ShoppingListBean(){}
      
      public ShoppingListBean(String productInfo,float productPrice,float originalPrice,int productCount){
    	  this.productInfo = productInfo;
    	  this.productPrice = productPrice;
    	  this.originalPrice = originalPrice;
    	  this.productCount = productCount;
      }
      
      
	public ImageView getProductPicture() {
		return productPicture;
	}
	public void setProductPicture(ImageView productPicture) {
		this.productPicture = productPicture;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
      
      
}
