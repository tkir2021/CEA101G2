package com.storereport.model;

import java.util.List;
import java.util.Set;

public interface StoreReportDAO_interface {
	public void insert(StoreReportVO srVO);
	public void update(StoreReportVO srVO);
	//查詢某一店家會員的所有的檢舉記錄
	public Set<StoreReportVO> findByStoreno(String store_no);
	//查詢所有的檢舉記錄
	public List<StoreReportVO> getAll();	
	public StoreReportVO findBySRno(String s_report_no);
	
}
