package com.memreport.model;

import java.sql.Date;

public class MemReportVO {
	private String m_report_no;
    private String group_no;
    private String reported_mem;
    private String report_mem;
    private String emp_no;
    private String report_reason;
    private Date report_time;
    private Integer report_status;
    private Integer commit_status;
	public String getM_report_no() {
		return m_report_no;
	}
	public void setM_report_no(String m_report_no) {
		this.m_report_no = m_report_no;
	}
	public String getGroup_no() {
		return group_no;
	}
	public void setGroup_no(String group_no) {
		this.group_no = group_no;
	}
	public String getReported_mem() {
		return reported_mem;
	}
	public void setReported_mem(String reported_mem) {
		this.reported_mem = reported_mem;
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
