package com.store.model;

import java.util.*;



public interface Store_Mem_interface {
	 public void insert(Store_MemVO store_memVO);
     public void update(Store_MemVO store_memVO);
     public void delete(String store_memVO);
     public Store_MemVO findByPrimaryKey(String store_mem);
     public List<Store_MemVO> getAll();
}
