package com.orderdetail.model;

import java.util.List;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	public void delete(String order_no);
	public List<OrderDetailVO> findByPrimaryKey(String order_no);
	public List<OrderDetailVO> getAll();
}
