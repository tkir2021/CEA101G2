package com.storereport.model;

import java.util.List;
import java.util.Set;

public interface StoreReportDAO_interface {
	public void insert(StoreReportVO srVO);
	public void update(StoreReportVO srVO);
	//�d�߬Y�@���a�|�����Ҧ������|�O��
	public Set<StoreReportVO> findByStoreno(String store_no);
	//�d�ߩҦ������|�O��
	public List<StoreReportVO> getAll();	
	public StoreReportVO findBySRno(String s_report_no);
	
}
