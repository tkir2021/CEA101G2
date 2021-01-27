package com.emp.model;

import java.util.*;

public class EmpService {
    private EmpDAO_interface dao;
    
    public EmpService() {
//    	dao = new EmpJDBCDAO();
    	dao = new EmpDAO();
    }
    
    public EmpVO addEmp (String emp_name, java.sql.Date emp_date, String emp_mail) { //Byte[] emp_img
		EmpVO empVO = new EmpVO();
		empVO.setEmp_name(emp_name);
		empVO.setEmp_pwd(empVO.getEmp_pwd());
		empVO.setEmp_mail(emp_mail);
		empVO.setEmp_date(emp_date);
//		empVO.setEmp_img(emp_img);
		

		dao.insert(empVO);
    	
    	return empVO;
    }
    
    public EmpVO updateEmp(String emp_no,String emp_name, String emp_mail, java.sql.Date emp_date,Integer emp_status ) { // Byte[] emp_img,
    	EmpVO empVO =new EmpVO();
    	empVO.setEmp_no(emp_no);
		empVO.setEmp_name(emp_name);
		empVO.setEmp_mail(emp_mail);
		empVO.setEmp_date(emp_date);
//		empVO.setEmp_img(emp_img);
		empVO.setEmp_status(emp_status);
		dao.update(empVO);
    	return empVO;
    }
    
    public EmpVO findByPK (String emp_no) {
    	return dao.findByPK(emp_no);
    }
    
    public List<EmpVO> getAll(){
    	return dao.getAll();
    }
    
    public boolean searchEmp(String emp_no, String emp_pwd) {
    	return dao.searchEmp(emp_no, emp_pwd);
    }
}