package com.mem.model;
import java.util.*;

import com.orderdetail.model.OrderDetailVO;
import com.ordermaster.model.OrderMasterVO;

public interface Mem_DataDAO_interface {
	public void insert(Mem_DataVO mem_dataVO);
    public void update(Mem_DataVO mem_dataVO);
    public void updateStatus(String mem_acct, Integer mem_auth);
    public void updateAuth(Mem_DataVO mem_dateVO);
    /************************購物車：更新會員儲值金 by Sheng*************************/
    public void updateDeposit_ByShopping(Mem_DataVO mem_dataVO, OrderMasterVO orderMasterVO, List<OrderDetailVO> list);
    /************************購物車：更新會員儲值金 by Sheng*************************/
    
    /************************儲值金 by 宏哥*************************/
    public void updateDeposit(Mem_DataVO mem_dataVO);
    /************************儲值金 by 宏哥*************************/
    public void delete(String MEM_NO);
    public Mem_DataVO findByPrimaryKey(String mem_no);
    public Mem_DataVO findByAccount(String mem_acct);
    public Mem_DataVO findByAccNoImg(String mem_acct);
    public List<Mem_DataVO> getAll();
    
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
	

}
