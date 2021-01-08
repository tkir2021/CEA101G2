package com.store.model;

import java.util.List;
import java.util.Set;

public class Food_ListService {
	private Food_List_interface dao;

	public Food_ListService() {
		dao = new Food_ListJDBCDAO();
	}

	public Food_ListVO addFood_List(String store_no, String food_name, Integer food_price, Integer limit_,
			String food_info, Integer food_status,byte[]food_img) {
		Food_ListVO food_ListVO = new Food_ListVO();
		food_ListVO.setStore_no(store_no);
		food_ListVO.setFood_name(food_name);
		food_ListVO.setFood_price(food_price);
		food_ListVO.setLimit_(limit_);
		food_ListVO.setFood_info(food_info);
		food_ListVO.setFood_status(food_status);
		food_ListVO.setFood_img(food_img);
		dao.insert(food_ListVO);
		return food_ListVO;
	}

	public Food_ListVO update_food_List_input(String food_no, String store_no, String food_name, Integer food_price,
			Integer limit_, String food_info, Integer food_status) {
		Food_ListVO food_ListVO = new Food_ListVO();
		food_ListVO.setFood_no(food_no);
		food_ListVO.setStore_no(store_no);
		food_ListVO.setFood_name(food_name);
		food_ListVO.setFood_price(food_price);
		food_ListVO.setLimit_(limit_);
		food_ListVO.setFood_info(food_info);
		food_ListVO.setFood_status(food_status);
		dao.update(food_ListVO);
		return food_ListVO;
	}
	
	/************************購物車 20210101 by 皓哥*************************/
	public List<Food_ListVO> getAllFood(String store_no) {
		return dao.getAllFood(store_no);
	}
	 /************************購物車 20210101 by 皓哥*************************/

	public List<Food_ListVO> getAll() {
		return dao.getAll();
	}

	public Food_ListVO getOneFood_List(String food_no) {
		return dao.findByPrimaryKey(food_no);
	}

	public void deleteFood_List(String food_no) {
		dao.delete(food_no);
	}

}
