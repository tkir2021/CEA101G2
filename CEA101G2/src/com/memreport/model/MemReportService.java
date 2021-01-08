package com.memreport.model;

import java.util.*;

public class MemReportService {
    private MemReportDAO_interface dao;
 
    public MemReportService() {
    	dao = new MemReportJDBCDAO();
    }
    
    public MemReportVO addMR (String group_no, String reported_mem, String report_mem, String report_reason) {
    	MemReportVO mrVO = new MemReportVO();
    	mrVO.setGroup_no(group_no);
    	mrVO.setReported_mem(reported_mem);
    	mrVO.setReport_mem(report_mem);
    	mrVO.setReport_reason(report_reason);
    	
    	return mrVO;
    }
    
    public MemReportVO updateMR(Integer report_status, Integer commit_status, String m_report_no) {
    	MemReportVO mrVO = new MemReportVO();
//    	mrVO.setEmp_no(emp_no);
    	mrVO.setReport_status(report_status);
    	mrVO.setM_report_no(m_report_no);
    	mrVO.setCommit_status(commit_status);
    	dao.update(mrVO);   	
    	
    	return mrVO;
    }
    
    public Set<MemReportVO> findByMemno(String reported_mem) {
    	return dao.findByMemno(reported_mem);
    	
    }
    
    public MemReportVO findByMRno(String m_report_no) {
    	return dao.findByMRno(m_report_no);
    	
    }
    public List<MemReportVO> getall(){
    	return dao.getAll();
    }
}
