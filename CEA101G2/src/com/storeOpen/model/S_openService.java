package com.storeOpen.model;

import java.util.List;

public class S_openService {
	private S_openDAO_interface dao;

	public S_openService() {
		dao = new S_openDAO();
	}

	public S_openVO addOpen(String storeno, String timeperiod) {

		S_openVO openVO = new S_openVO();
		openVO.setStoreno(storeno);
		openVO.setTimeperiod(timeperiod);
		dao.insert(openVO);

		return openVO;
	}
//
//	public S_openVO updateOpen(String storeno, String timeperiod) {
//
//		S_openVO openVO = new S_openVO();
//		openVO.setStoreno(storeno);
//		openVO.setTimeperiod(timeperiod);
//		dao.update(openVO);
//
//		return openVO;
//	}
	
	public S_openVO deleteOpen(String storeno, String timeperiod) {
		S_openVO openVO = new S_openVO();
		openVO.setStoreno(storeno);
		openVO.setTimeperiod(timeperiod);
		dao.delete(openVO);

		return openVO;
	}
	
	public List<S_openVO> getOneOpen(String storeno) {
		return dao.findByPrimaryKey(storeno);
	}
	
	public List<S_openVO> getAll(){
		return dao.getAll();
	}
	

}
