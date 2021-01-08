package com.emp.model;

import java.util.List;

public class AuthService {
	private AuthDAO_interface dao;

	public AuthService() {
		dao= new AuthJDBCDAO();
	}
	
	public AuthVO addAuth(String auth_no, String auth_name) {
		AuthVO authVO = new AuthVO();
		authVO.setAuth_no(auth_no);
		authVO.setAuth_name(auth_name);
		dao.insert(authVO);
		return authVO;
	}
	
	public AuthVO updateAuth(String auth_name) {
		AuthVO authVO = new AuthVO();
		authVO.setAuth_name(auth_name);
		dao.update(authVO);
		return authVO;
	}
	
	public AuthVO findByAuth(String auth_no) {
		return dao.findByPK(auth_no);
	}
	
	public List<AuthVO> getAll(){
		return dao.getAll();
	}
}
