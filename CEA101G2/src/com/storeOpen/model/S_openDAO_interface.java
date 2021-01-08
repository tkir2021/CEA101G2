package com.storeOpen.model;

import java.util.List;


public interface S_openDAO_interface {
	public void insert(S_openVO openVO);
   // public void update(S_openVO openVO);
    public void delete(S_openVO openVO);
    public List<S_openVO> findByPrimaryKey(String storeno);
    public List<S_openVO> getAll();
}
