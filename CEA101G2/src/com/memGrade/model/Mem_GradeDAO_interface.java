package com.memGrade.model;

import java.util.*;

public interface Mem_GradeDAO_interface {
          public void insert(Mem_GradeVO mem_gradeVO);
          public void update(Mem_GradeVO mem_gradeVO);
          public void delete(Integer grade_no);
          public Mem_GradeVO findByPrimaryKey(Integer grade_no);
          public List<Mem_GradeVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Mem_GradeVO> getAll(Map<String, String[]> map); 
}
