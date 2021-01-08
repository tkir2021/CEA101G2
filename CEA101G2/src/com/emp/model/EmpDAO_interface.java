package com.emp.model;

import java.util.List;

public interface EmpDAO_interface {
    public void insert(EmpVO empVO);
    public void update(EmpVO empVO);
    public EmpVO findByPK(String emp_no); 
    public List<EmpVO> getAll();
    
    //12/26新增for登入查找emp用
    public boolean searchEmp(String emp_no, String emp_pwd);

}
