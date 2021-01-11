package com.bill.model;

import java.util.List;

public interface BillDAO_interface {
	public void insert(BillVO billVO);
	public void update(BillVO billVO);
    public void delete(String bill_no);
    public BillVO findByPrimaryKey(String bill_no);
    public List<BillVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<BillVO> getAll(Map<String, String[]> map); 
}
