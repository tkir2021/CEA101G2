package com.store_scale.model;

import java.util.List;
import java.util.Set;

public class Store_ScaleService {
	private Store_Scale_interface dao;
	
	public Store_ScaleService() {
		dao = new Store_ScaleJDBCDAO();
	}
		
	public Store_ScaleVO addStore_Scale(String food_no,String food_scale) {
		Store_ScaleVO store_ScaleVO = new Store_ScaleVO();
		
		store_ScaleVO.setFood_no(food_no);
		store_ScaleVO.setFood_scale(food_scale);
		dao.insert(store_ScaleVO);
		return store_ScaleVO;
	}
	
	public Store_ScaleVO update_store_Scale_input(String food_no, String food_scale
			) {
		Store_ScaleVO store_ScaleVO = new Store_ScaleVO();
		
		store_ScaleVO.setFood_no(food_no);
		store_ScaleVO.setFood_scale(food_scale);
		dao.update(store_ScaleVO);
		return store_ScaleVO;
	}
	
	public List<Store_ScaleVO> getAll() {
		return dao.getAll();
	}

	public List<Store_ScaleVO> getOneStore_Scale(String food_no) {
		return dao.findByPrimaryKey(food_no);
	}

	public void deleteStore_Scale(String food_no) {
		dao.delete(food_no);
	}
	
	
	
}
