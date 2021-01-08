package com.orderdetail.model;

public class OrderDetailVO implements java.io.Serializable{
	private String order_no;
	private String food_no;
	private String food_scale;
	private Integer quantity;
	private Integer food_price;
	private Integer total;
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getFood_no() {
		return food_no;
	}
	public void setFood_no(String food_no) {
		this.food_no = food_no;
	}
	public String getFood_scale() {
		return food_scale;
	}
	public void setFood_scale(String food_scale) {
		this.food_scale = food_scale;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getFood_price() {
		return food_price;
	}
	public void setFood_price(Integer food_price) {
		this.food_price = food_price;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
