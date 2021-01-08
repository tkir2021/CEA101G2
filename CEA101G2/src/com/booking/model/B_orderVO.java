package com.booking.model;

import java.sql.Date;
import java.sql.Timestamp;

public class B_orderVO implements java.io.Serializable{
	private String bookingno;
	private String memno;
	private String storeno;
	private String groupno;
	private Date bookingdate;
	private String timeperiod;
	private Integer people;
	private Integer bookingstatus;
	private Integer attendstatus;
	private Double givestar;
	private Timestamp ordercommit;
	
	public B_orderVO() {
		
	}
	
	public String getBookingno() {
		return bookingno;
	}
	public void setBookingno(String bookingno) {
		this.bookingno = bookingno;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getStoreno() {
		return storeno;
	}
	public void setStoreno(String storeno) {
		this.storeno = storeno;
	}
	public String getGroupno() {
		return groupno;
	}
	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}
	public Date getBookingdate() {
		return bookingdate;
	}
	public void setBookingdate(Date bookingdate) {
		this.bookingdate = bookingdate;
	}
	public String getTimeperiod() {
		return timeperiod;
	}
	public void setTimeperiod(String timeperiod) {
		this.timeperiod = timeperiod;
	}
	public Integer getPeople() {
		return people;
	}
	public void setPeople(Integer people) {
		this.people = people;
	}
	public Integer getBookingstatus() {
		return bookingstatus;
	}
	public void setBookingstatus(Integer bookingstatus) {
		this.bookingstatus = bookingstatus;
	}
	public Integer getAttendstatus() {
		return attendstatus;
	}
	public void setAttendstatus(Integer attendstatus) {
		this.attendstatus = attendstatus;
	}
	public Double getGivestar() {
		return givestar;
	}
	public void setGivestar(Double givestar) {
		this.givestar = givestar;
	}
	public Timestamp getOrdercommit() {
		return ordercommit;
	}
	public void setOrdercommit(Timestamp ordercommit) {
		this.ordercommit = ordercommit;
	}

}
