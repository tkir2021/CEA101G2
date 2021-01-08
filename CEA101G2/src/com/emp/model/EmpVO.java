package com.emp.model;

import java.io.*;
import java.sql.*;


public class EmpVO {
	
	private String emp_no;
	private String emp_pwd;
    private String emp_name;
    private Date emp_date;
    private Byte[] emp_img;
    private String emp_mail;
    private Integer emp_status;
    
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_pwd() { 
		//密碼亂數產生
		int z;
		StringBuilder emp_pwdb = new StringBuilder();
		int i;
		for(i = 0; i < 8 ; i++) {
			z =(int)((Math.random()*7)%3);
			if(z==1) {
				emp_pwdb.append((int)((Math.random()*10)));
			}else if(z==2) {
				emp_pwdb.append((char)((Math.random()*26)+65));				
			}else {
				emp_pwdb.append((char)((Math.random()*26)+97));
			}
		}
		emp_pwd = emp_pwdb.toString();
		return emp_pwd;
	}
	public void setEmp_pwd(String emp_pwd) {
		this.emp_pwd = emp_pwd;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Date getEmp_date() {
		return emp_date;
	}
	public void setEmp_date(Date emp_date) {
		this.emp_date = emp_date;
	}
	
	//上傳圖片異常
	public Byte[] getEmp_img() throws FileNotFoundException {
		File image = new File("image.png");
		InputStream is = new FileInputStream(image);
				
		return emp_img;
	}
	public void setEmp_img(Byte[] emp_img) {
		this.emp_img = emp_img;
	}
	public String getEmp_mail() {
		return emp_mail;
	}
	public void setEmp_mail(String emp_mail) {
		this.emp_mail = emp_mail;
	}
	public Integer getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(Integer emp_status) {
		this.emp_status = emp_status;
	}
	

}
