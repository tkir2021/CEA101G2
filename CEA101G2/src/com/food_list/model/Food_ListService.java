package com.food_list.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


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

	 public List<Food_ListVO> getFoodFromOne(String store_no) {
		  return dao.getAll(store_no);
		 }
	 
	/************************購物車：取得所有食物列表 by Sheng*************************/
	public List<Food_ListVO> getAllFood(String store_no) {
		return dao.getAllFood(store_no);
	}
	/************************購物車：取得所有食物列表 by Sheng*************************/

	public List<Food_ListVO> getAll() {
		return dao.getAll();
	}

	public Food_ListVO getOneFood_List(String food_no) {
		return dao.findByPrimaryKey(food_no);
	}

	public void deleteFood_List(String food_no) {
		dao.delete(food_no);
	}
	
//	========================更新餐點上架狀態 by Mike========================
	public void updateStatus(String food_no, Integer food_status) {
		dao.updateStatus(food_no, food_status);
	}
	
	/**********待處理**********/
	 public List<Food_ListVO> searchKeyword(String keyword){
	    List<Food_ListVO> fdlist = dao.getAll();
	    List<Food_ListVO> resultlist = null;
	    resultlist = fdlist.stream()
	      .filter(f -> f.getFood_name().contains(keyword.toUpperCase()))
	      .collect(Collectors.collectingAndThen(
	        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Food_ListVO::getStore_no))),
	        ArrayList::new));
	    
	    return resultlist;
	 }

}
