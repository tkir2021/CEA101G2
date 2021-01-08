package com.memreport.model;

import java.util.*;

public interface MemReportDAO_interface {
	public void insert(MemReportVO mrVO);
	public void update(MemReportVO mrVO);
	//查詢某一會員的所有的檢舉記錄
	public Set<MemReportVO> findByMemno(String mem_no);
	//查詢某一筆檢舉記錄
	public MemReportVO findByMRno(String memreport_no);
	//查詢所有的檢舉記錄
	public List<MemReportVO> getAll();	
	
	
}
