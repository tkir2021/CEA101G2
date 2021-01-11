package com.orderdetail.model;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	 /************************購物車：新增訂餐明細 by Sheng*************************/
	public void updateByShopping(OrderDetailVO orderDetailVO, Connection con);
	 /************************購物車：新增訂餐明細  by Sheng*************************/
	public void delete(String order_no);
	public List<OrderDetailVO> findByPrimaryKey(String order_no);
	public List<OrderDetailVO> getAll();
}
