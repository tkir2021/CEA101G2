package com.booking.model;

import java.util.List;

public interface B_orderDAO_interface {

	public void insert(B_orderVO b_orderVO);
	public void update(B_orderVO b_orderVO);
	/***更新評分 by Sheng***/
	public void upGivestar(String bookingno, double givestar);
	/***更新評分 by Sheng***/
	public B_orderVO findByPrimaryKey(String bookingno);
	public List<B_orderVO> findByPrimaryKey2(String number);
	public List<B_orderVO> getAll();
}
