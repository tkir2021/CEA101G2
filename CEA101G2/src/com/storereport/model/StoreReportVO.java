package com.storereport.model;

import java.sql.Date;

public class StoreReportVO {
    private String s_report_no;
    private String order_no;
    private String booking_no;
    private String reported_store;
    private String report_mem;
    private String emp_no;
    private String report_reason;
    private Date report_time;
    private Integer report_grade;
    private Integer report_status;
    private Integer commit_status;
    
	public String getS_report_no() {
		return s_report_no;
	}
	public void setS_report_no(String s_report_no) {
		this.s_report_no = s_report_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getBooking_no() {
		return booking_no;
	}
	public void setBooking_no(String booking_no) {
		this.booking_no = booking_no;
	}
	public String getReported_store() {
		return reported_store;
	}
	public void setReported_store(String reported_store) {
		this.reported_store = reported_store;
	}
	public String getReport_mem() {
		return report_mem;
	}
	public void setReport_mem(String report_mem) {
		this.report_mem = report_mem;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getReport_reason() {
		return report_reason;
	}
	public void setReport_reason(String report_reason) {
		this.report_reason = report_reason;
	}
	public Date getReport_time() {
		return report_time;
	}
	public void setReport_time(Date report_time) {
		this.report_time = report_time;
	}
	public Integer getReport_grade() {
		return report_grade;
	}
	public void setReport_grade(Integer report_grade) {
		this.report_grade = report_grade;
	}
	public Integer getReport_status() {
		return report_status;
	}
	public void setReport_status(Integer report_status) {
		this.report_status = report_status;
	}
	public Integer getCommit_status() {
		return commit_status;
	}
	public void setCommit_status(Integer commit_status) {
		this.commit_status = commit_status;
	}
    
    
    
}
