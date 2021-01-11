package com.store.model;

import java.sql.Date;

public class Store_MemVO 
	 implements java.io.Serializable{
		private String store_no;
		private String store_acct;
		private String store_pwd;
		private String store_name;
		private String addr;
		private String open_dates;
		private String email;
		private String s_category;
		private String store_info;
		private Integer upload_status;
		private Integer s_permission;
		private Integer sum_grade;
		private Integer blocked;
		private Double star_total;
		private Integer star_times;
		private Integer table_limit;
		private byte[] rest_img;
		
		public String getStore_no() {
			return store_no;
		}
		public String getStore_acct() {
			return store_acct;
		}
		public String getStore_pwd() {
			return store_pwd;
		}
		public String getStore_name() {
			return store_name;
		}
		public String getAddr() {
			return addr;
		}
		public String getOpen_dates() {
			return open_dates;
		}
		public String getEmail() {
			return email;
		}
		public String getS_category() {
			return s_category;
		}
		public String getStore_info() {
			return store_info;
		}
		public Integer getUpload_status() {
			return upload_status;
		}
		public Integer getS_permission() {
			return s_permission;
		}
		public Integer getSum_grade() {
			return sum_grade;
		}
		public Integer getBlocked() {
			return blocked;
		}
		public Double getStar_total() {
			return star_total;
		}
		public Integer getStar_times() {
			return star_times;
		}
		public Integer getTable_limit() {
			return table_limit;
		}
		public byte[] getRest_img() {
			return rest_img;
		}
		public void setStore_no(String store_no) {
			this.store_no = store_no;
		}
		public void setStore_acct(String store_acct) {
			this.store_acct = store_acct;
		}
		public void setStore_pwd(String store_pwd) {
			this.store_pwd = store_pwd;
		}
		public void setStore_name(String store_name) {
			this.store_name = store_name;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		public void setOpen_dates(String open_dates) {
			this.open_dates = open_dates;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public void setS_category(String s_category) {
			this.s_category = s_category;
		}
		public void setStore_info(String store_info) {
			this.store_info = store_info;
		}
		public void setUpload_status(Integer upload_status) {
			this.upload_status = upload_status;
		}
		public void setS_permission(Integer s_permission) {
			this.s_permission = s_permission;
		}
		public void setSum_grade(Integer sum_grade) {
			this.sum_grade = sum_grade;
		}
		public void setBlocked(Integer blocked) {
			this.blocked = blocked;
		}
		public void setStar_total(Double star_total) {
			this.star_total = star_total;
		}
		public void setStar_times(Integer star_times) {
			this.star_times = star_times;
		}
		public void setTable_limit(Integer table_limit) {
			this.table_limit = table_limit;
		}
		public void setRest_img(byte[] rest_img) {
			this.rest_img = rest_img;
		}
		
		
		
}