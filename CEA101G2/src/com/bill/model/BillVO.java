package com.bill.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class BillVO implements Serializable{
	private String bill_no;
	private String store_no;
	private Integer bill_price;
	private Timestamp bill_date;
	private String bill_period;
	
	
	public String getBill_no() {
		return bill_no;
	}
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}
	public String getStore_no() {
		return store_no;
	}
	public void setStore_no(String store_no) {
		this.store_no = store_no;
	}
	public Integer getBill_price() {
		return bill_price;
	}
	public void setBill_price(Integer bill_price) {
		this.bill_price = bill_price;
	}
	public Timestamp getBill_date() {
		return bill_date;
	}
	public void setBill_date(Timestamp bill_date) {
		this.bill_date = bill_date;
	}
	public String getBill_period() {
		return bill_period;
	}
	public void setBill_period(String bill_period) {
		this.bill_period = bill_period;
	}
	
}
