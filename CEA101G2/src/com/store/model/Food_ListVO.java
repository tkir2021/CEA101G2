package com.store.model;

public class Food_ListVO implements java.io.Serializable{
	private String food_no;
	private String store_no;
	private String food_name;
	private Integer food_price;
	private Integer limit_;
	private String food_info;
	private byte[] food_img;
	private Integer food_status;
	
	public String getFood_no() {
		return food_no;
	}
	public String getStore_no() {
		return store_no;
	}
	public String getFood_name() {
		return food_name;
	}
	public Integer getFood_price() {
		return food_price;
	}
	public Integer getLimit_() {
		return limit_;
	}
	public String getFood_info() {
		return food_info;
	}
	public byte[] getFood_img() {
		return food_img;
	}
	public Integer getFood_status() {
		return food_status;
	}
	public void setFood_no(String food_no) {
		this.food_no = food_no;
	}
	public void setStore_no(String store_no) {
		this.store_no = store_no;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public void setFood_price(Integer food_price) {
		this.food_price = food_price;
	}
	public void setLimit_(Integer limit_) {
		this.limit_ = limit_;
	}
	public void setFood_info(String food_info) {
		this.food_info = food_info;
	}
	public void setFood_img(byte[] food_img) {
		this.food_img = food_img;
	}
	public void setFood_status(Integer food_status) {
		this.food_status = food_status;
	}
}
