package com.group_mem.model;

import java.util.List;

public interface Group_memDAO_interface {
	public void insert(Group_memVO group_memVO);
    public void delete(String group_no, String mem_no);
    public List<Group_memVO> findByPrimaryKey(String group_no);
    public List<Group_memVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<Group_memVO> getAll(Map<String, String[]> map); 
	
}
