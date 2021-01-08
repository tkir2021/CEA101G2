package com.emp.model;

import java.util.*;

public interface AuthDAO_interface {
    public void insert(AuthVO authVO);
    public void update(AuthVO authVO);
    public AuthVO findByPK(String auth_no);
    public List<AuthVO> getAll();
    
}
