package com.memGrade.model;
import java.sql.Date;

public class Mem_GradeVO implements java.io.Serializable{
	private Integer grade_no;
	private Integer consume_level;
	private Double discount;
	public Integer getGrade_no() {
		return grade_no;
	}
	public void setGrade_no(Integer grade_no) {
		this.grade_no = grade_no;
	}
	public Integer getConsume_level() {
		return consume_level;
	}
	public void setConsume_level(Integer consume_level) {
		this.consume_level = consume_level;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	
}
	
	
	