package com.group.model;

import java.util.*;

public interface Group_DAO_interface {
	public void insert(Group_VO groupVO);
	public void update(Group_VO groupVO);
	public void delete(String group_no);
	public Group_VO findByPrimaryKey(String group_no);
	public Group_VO getOneGpSta(Integer gp_status);
	public List<Group_VO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<Group_VOVO> getAll(Map<String, String[]> map); 
}
