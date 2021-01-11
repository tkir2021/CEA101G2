package com.mem.model;

public class Mem_DataVO implements java.io.Serializable{
	private String mem_no;
	private Integer mem_grade;
	private String mem_acct;
	private String mem_pwd;
	private String mem_name;
	private String men_phone;
	private String mem_mail;
	private byte[] mem_img;
	private Integer mem_auth;
	private Integer report_count;
	private Integer consume_times;
	private Integer deposit;
	
	
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getMem_grade() {
		return mem_grade;
	}
	public void setMem_grade(Integer mem_grade) {
		this.mem_grade = mem_grade;
	}
	public String getMem_acct() {
		return mem_acct;
	}
	public void setMem_acct(String mem_acct) {
		this.mem_acct = mem_acct;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMen_phone() {
		return men_phone;
	}
	public void setMen_phone(String men_phone) {
		this.men_phone = men_phone;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public byte[] getMem_img() {
		return mem_img;
	}
	public void setMem_img(byte[] mem_img) {
		this.mem_img = mem_img;
	}
	public Integer getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(Integer mem_auth) {
		this.mem_auth = mem_auth;
	}
	public Integer getReport_count() {
		return report_count;
	}
	public void setReport_count(Integer report_count) {
		this.report_count = report_count;
	}
	public Integer getConsume_times() {
		return consume_times;
	}
	public void setConsume_times(Integer consume_times) {
		this.consume_times = consume_times;
	}
	public Integer getDeposit() {
		return deposit;
	}
	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}
	
}
