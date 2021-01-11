package com.bill.model;

import java.sql.Timestamp;
import java.util.List;

public class BillService {
	
	private BillDAO_interface dao;
	
	public BillService() {
		dao = new BillJDBCDAO();
	}
	
	public BillVO addBill(String store_no, Integer bill_price, Timestamp bill_date, String bill_period) {
		
		BillVO billVO = new BillVO();
		
		billVO.setStore_no(store_no);
		billVO.setBill_price(bill_price);
		billVO.setBill_date(bill_date);
		billVO.setBill_period(bill_period);
		dao.insert(billVO);
		
		return billVO;
	}
	
	public BillVO updateBill(String bill_no, String store_no, Integer bill_price, Timestamp bill_date, String bill_period) {
		
		BillVO billVO = new BillVO();
		billVO.setBill_no(bill_no);
		billVO.setStore_no(store_no);
		billVO.setBill_price(bill_price);
		billVO.setBill_date(bill_date);
		billVO.setBill_period(bill_period);
		dao.update(billVO);
		
		return billVO;
	}
	 
	public void deleteBill(String bill_no) {
		dao.delete(bill_no);
	}
	
	public BillVO getOneBill(String bill_no) {
		return dao.findByPrimaryKey(bill_no);
	}
	
	public List<BillVO> getAll(){
		return dao.getAll();
	}
	
	
}
