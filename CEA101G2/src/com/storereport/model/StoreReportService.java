package com.storereport.model;

import java.util.*;

public class StoreReportService {
    private StoreReportDAO_interface dao;
 
    public StoreReportService() {
    	dao = new StoreReportJDBCDAO();
    }
    
    public StoreReportVO addSR (String order_no, String booking_no, String reported_store, String report_mem, String report_reason) {
    	StoreReportVO srVO = new StoreReportVO();
    	srVO.setOrder_no(order_no);
    	srVO.setBooking_no(booking_no);
    	srVO.setReported_store(reported_store);
    	srVO.setReport_mem(report_mem);
    	srVO.setReport_reason(report_reason);
    	return srVO;
    }
    
    public StoreReportVO updateSR(String s_report_no, Integer report_grade, Integer report_status, Integer commit_status) {
    	StoreReportVO srVO = new StoreReportVO();
    	srVO.setS_report_no(s_report_no);
    	srVO.setReport_grade(report_grade);
    	srVO.setReport_status(report_status);
    	srVO.setCommit_status(commit_status);
    	dao.update(srVO);   	
    	
    	return srVO;
    }
    
    public StoreReportVO findByS_report_no(String s_report_no) {
    	return dao.findBySRno(s_report_no);
    	
    }
    public List<StoreReportVO> getall(){
    	return dao.getAll();
    }
}
