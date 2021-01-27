package com.ordermaster.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.booking.model.B_orderVO;
import com.orderdetail.model.OrderDetailVO;

public class OrderMasterService {

	private OrderMasterDAO_interface dao;

	public OrderMasterService() {
//		dao = new OrderMasterJDBCDAO();
		dao = new OrderMasterDAO();
	}

	public OrderMasterVO addOrderMaster(String mem_no, String store_no, String sale_no,
			Date order_date, Integer pay_type, Integer order_total, Float sale_percent, Float discount,
			String order_status, String take_status, Float give_star) {

		OrderMasterVO orderMasterVO = new OrderMasterVO();

		orderMasterVO.setMem_no(mem_no);
		orderMasterVO.setStore_no(store_no);
		orderMasterVO.setSale_no(sale_no);
		orderMasterVO.setOrder_date(order_date);
		orderMasterVO.setPay_type(pay_type);
		orderMasterVO.setOrder_total(order_total);
		orderMasterVO.setSale_percent(sale_percent);
		orderMasterVO.setDiscount(discount);
		orderMasterVO.setOrder_status(order_status);
		orderMasterVO.setTake_status(take_status);
		orderMasterVO.setGive_star(give_star);
		dao.insert(orderMasterVO);

		return orderMasterVO;

	}
	
	public OrderMasterVO updateOrderMaster(String order_no, String mem_no, String store_no, String sale_no,
			Date order_date, Integer pay_type, Integer order_total, Float sale_percent, Float discount,
			String order_status, String take_status, Float give_star) {
		
		OrderMasterVO orderMasterVO = new OrderMasterVO();

		orderMasterVO.setOrder_no(order_no);
		orderMasterVO.setMem_no(mem_no);
		orderMasterVO.setStore_no(store_no);
		orderMasterVO.setSale_no(sale_no);
		orderMasterVO.setOrder_date(order_date);
		orderMasterVO.setPay_type(pay_type);
		orderMasterVO.setOrder_total(order_total);
		orderMasterVO.setSale_percent(sale_percent);
		orderMasterVO.setDiscount(discount);
		orderMasterVO.setOrder_status(order_status);
		orderMasterVO.setTake_status(take_status);
		orderMasterVO.setGive_star(give_star);
		dao.update(orderMasterVO);
		
		return orderMasterVO;
	}
	
	/************************購物車：新增訂餐主檔 by Sheng*************************/
	public void updateByShopping(OrderMasterVO orderMasterVO, List<OrderDetailVO> list, Connection con) {
		dao.updateByShopping(orderMasterVO, list, con);		
	}
	
	/************************更新評分 by Sheng*************************/
	public B_orderVO upGivestar(String order_no, double om_givestar) {
		B_orderVO b_orderVO = new B_orderVO();
		b_orderVO.setBookingno(order_no);
		b_orderVO.setGivestar(om_givestar);
		dao.upGivestar(order_no, om_givestar);
		
		return b_orderVO;
	}
	
	public void deleteOrderNo(String order_no) {
		dao.delete(order_no);
	}
	
	public OrderMasterVO getOneOrderMaster(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}
	
	/************************更新取餐狀態 by Bella*************************/
	 public OrderMasterVO upGetFood(String order_no, String take_status) {
	  OrderMasterVO orderMasterVO = new OrderMasterVO();
	  orderMasterVO.setOrder_no(order_no);
	  orderMasterVO.setTake_status(take_status);
	  dao.updateGetFood(order_no, take_status);
	  return orderMasterVO;
	 }
	 
	/************************取得訂餐資料 by Sheng*************************/
	public List<OrderMasterVO> findByNumber(String number){
		return dao.findByNumber(number);
	}
	
	public List<OrderMasterVO> getAll(){
		return dao.getAll();
	}
}