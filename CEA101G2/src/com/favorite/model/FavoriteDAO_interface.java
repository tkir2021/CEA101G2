package com.favorite.model;

import java.util.*;


public interface FavoriteDAO_interface {
          public void insert(FavoriteVO favoriteVO);
          public void update(FavoriteVO favoriteVO);
          public void delete(String store_no,String mem_no);
          public List<FavoriteVO> findByPrimaryKey(String store_no);
          public List<FavoriteVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
