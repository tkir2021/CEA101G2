package com.ordermaster.model;

import java.sql.Date;

public class OrderMasterVO implements java.io.Serializable{
	private String order_no;
	private String mem_no;
	private String store_no;
	private String sale_no;
	private Date order_date;
	private Integer pay_type;
	private Integer order_total;
	private Float sale_percent;
	private Float discount;
	private String order_status;
	private String take_status;
	private Float give_star;

	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getStore_no() {
		return store_no;
	}
	public void setStore_no(String store_no) {
		this.store_no = store_no;
	}
	public String getSale_no() {
		return sale_no;
	}
	public void setSale_no(String sale_no) {
		this.sale_no = sale_no;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	public Integer getOrder_total() {
		return order_total;
	}
	public void setOrder_total(Integer order_total) {
		this.order_total = order_total;
	}
	public Float getSale_percent() {
		return sale_percent;
	}
	public void setSale_percent(Float sale_percent) {
		this.sale_percent = sale_percent;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getTake_status() {
		return take_status;
	}
	public void setTake_status(String take_status) {
		this.take_status = take_status;
	}
	public Float getGive_star() {
		return give_star;
	}
	public void setGive_star(Float give_star) {
		this.give_star = give_star;
	}

	
	
}
