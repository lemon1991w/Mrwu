package com.viewpager.fragment;

public class FragmentDataBean{
    
	private int iv;
	private String des;
	private String date;
	
	public FragmentDataBean(){
	}
	
	public FragmentDataBean(int iv,String des, String date){
		   this.iv = iv;
		   this.des = des;
		   this.date = date;
	}

	public int getIv() {
		return iv;
	}

	public void setIv(int iv) {
		this.iv = iv;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
