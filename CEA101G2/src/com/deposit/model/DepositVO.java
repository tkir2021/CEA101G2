package com.deposit.model;
import java.sql.Timestamp;

public class  DepositVO implements java.io.Serializable{
	private String deposit_no;
	private String mem_no;
	private Integer charge;
	private Timestamp charge_time;
	
	public String getDeposit_no() {
		return deposit_no;
	}
	public void setDeposit_no(String deposit_no) {
		this.deposit_no = deposit_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getCharge() {
		return charge;
	}
	public void setCharge(Integer charge) {
		this.charge = charge;
	}
	public Timestamp getCharge_time() {
		return charge_time;
	}
	public void setCharge_time(Timestamp charge_time) {
		this.charge_time = charge_time;
	}
	
}
	