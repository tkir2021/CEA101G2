package com.mem.model;
import java.util.*;

public interface Mem_DataDAO_interface {
	public void insert(Mem_DataVO mem_dataVO);
    public void update(Mem_DataVO mem_dataVO);
    public void updateStatus(String mem_acct, Integer mem_auth);
    public void updateAuth(Mem_DataVO mem_dateVO);
    public void delete(String MEM_NO);
    public Mem_DataVO findByPrimaryKey(String mem_no);
    public Mem_DataVO findByAccount(String mem_acct);
    public Mem_DataVO findByAccNoImg(String mem_acct);
    public List<Mem_DataVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
	

}
