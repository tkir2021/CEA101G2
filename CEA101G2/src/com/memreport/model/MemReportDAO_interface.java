package com.memreport.model;

import java.util.*;

public interface MemReportDAO_interface {
	public void insert(MemReportVO mrVO);
	public void update(MemReportVO mrVO);
	//�d�߬Y�@�|�����Ҧ������|�O��
	public Set<MemReportVO> findByMemno(String mem_no);
	//�d�߬Y�@�����|�O��
	public MemReportVO findByMRno(String memreport_no);
	//�d�ߩҦ������|�O��
	public List<MemReportVO> getAll();	
	
	
}
