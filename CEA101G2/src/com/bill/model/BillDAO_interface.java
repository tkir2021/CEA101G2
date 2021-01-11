package com.bill.model;

import java.util.List;

public interface BillDAO_interface {
	public void insert(BillVO billVO);
	public void update(BillVO billVO);
    public void delete(String bill_no);
    public BillVO findByPrimaryKey(String bill_no);
    public List<BillVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<BillVO> getAll(Map<String, String[]> map); 
}
