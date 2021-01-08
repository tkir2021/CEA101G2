package com.ordermaster.model;

import java.util.List;

public interface OrderMasterDAO_interface {
	public void insert(OrderMasterVO orderMasterVO);
	public void update(OrderMasterVO orderMasterVO);
	
	/************************取得訂餐資料 by Sheng*************************/
	public void upGivestar(String order_no, double om_givestar);
	/************************取得訂餐資料 by Sheng*************************/
	
	public void delete(String order_no);
	public OrderMasterVO findByPrimaryKey(String order_no);
	
	/************************取得訂餐資料 by Sheng*************************/
	public List<OrderMasterVO> findByNumber(String number);
	/************************取得訂餐資料 by Sheng*************************/
	
	public List<OrderMasterVO> getAll();
}
