package com.orderdetail.model;

import java.sql.Connection;
import java.util.List;

public class OrderDetailService {
	
	private OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailJDBCDAO();
	}
	
	public OrderDetailVO addOrderDetail(String order_no, String food_no, String food_scale, 
			Integer quantity, Integer food_price, Integer total) {
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrder_no(order_no);
		orderDetailVO.setFood_no(food_no);
		orderDetailVO.setFood_scale(food_scale);
		orderDetailVO.setQuantity(quantity);
		orderDetailVO.setFood_price(food_price);
		orderDetailVO.setTotal(total);
		dao.insert(orderDetailVO);
		
		return orderDetailVO;
	}
	
	public OrderDetailVO updateOrderDetail(String order_no, String food_no, String food_scale, 
			Integer quantity, Integer food_price, Integer total) {
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrder_no(order_no);
		orderDetailVO.setFood_no(food_no);
		orderDetailVO.setFood_scale(food_scale);
		orderDetailVO.setQuantity(quantity);
		orderDetailVO.setFood_price(food_price);
		orderDetailVO.setTotal(total);
		dao.insert(orderDetailVO);
		
		return orderDetailVO;
	}
	
	 /************************購物車：新增訂餐明細 by Sheng*************************/
		public void updateByShopping(OrderDetailVO orderDetailVO, Connection con){
			dao.updateByShopping(orderDetailVO, con);			
		}
	/************************購物車：新增訂餐明細  by Sheng*************************/
	
	public void deleteOrderNo(String order_no) {
		dao.delete(order_no);
	}
	
	public List<OrderDetailVO> getOneOrderDetail(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}
	
	public List<OrderDetailVO> getAll(){
		return dao.getAll();
	}
}
