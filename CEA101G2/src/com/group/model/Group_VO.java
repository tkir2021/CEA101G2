package com.group.model;
import java.io.Serializable;
import java.sql.Date;

//一個 Group_VO 物件代表一列資料
public class Group_VO implements Serializable{
	private String group_no;
	private String mem_no;
	private String store_no;
	private String booking_no;
	private Integer gp_kind;
	private Integer mem_least;
	private Integer mem_limit;
	private Date dining_date;
	private Date start_date;
	private Date end_date;
	private String gp_info;
	private Integer gp_status;
	
	public String getGroup_no() {
		return group_no;
	}
	public void setGroup_no(String group_no) {
		this.group_no = group_no;
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
	public String getBooking_no() {
		return booking_no;
	}
	public void setBooking_no(String booking_no) {
		this.booking_no = booking_no;
	}
	public Integer getGp_kind() {
		return gp_kind;
	}
	public void setGp_kind(Integer gp_kind) {
		this.gp_kind = gp_kind;
	}
	public Integer getMem_least() {
		return mem_least;
	}
	public void setMem_least(Integer mem_least) {
		this.mem_least = mem_least;
	}
	public Integer getMem_limit() {
		return mem_limit;
	}
	public void setMem_limit(Integer mem_limit) {
		this.mem_limit = mem_limit;
	}
	public Date getDining_date() {
		return dining_date;
	}
	public void setDining_date(Date dining_date) {
		this.dining_date = dining_date;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getGp_info() {
		return gp_info;
	}
	public void setGp_info(String gp_info) {
		this.gp_info = gp_info;
	}
	public Integer getGp_status() {
		return gp_status;
	}
	public void setGp_status(Integer gp_status) {
		this.gp_status = gp_status;
	}
}
