package com.ordermaster.model;

import java.sql.Connection;
import java.util.List;

import com.orderdetail.model.OrderDetailVO;

public interface OrderMasterDAO_interface {
	public void insert(OrderMasterVO orderMasterVO);
	public void update(OrderMasterVO orderMasterVO);
	public void delete(String order_no);
	public OrderMasterVO findByPrimaryKey(String order_no);
	public List<OrderMasterVO> getAll();
	/************************更新取餐狀況by Bella*******************************/
	 public void updateGetFood(String order_no, String take_status);
	/************************購物車：新增訂餐主檔 by Sheng*************************/
	public void updateByShopping(OrderMasterVO orderMasterVO, List<OrderDetailVO> list, Connection con);	
	/************************取得訂餐資料 by Sheng*************************/
	public void upGivestar(String order_no, double om_givestar);
	/************************取得訂餐資料 by Sheng*************************/
	public List<OrderMasterVO> findByNumber(String number);

	
}
