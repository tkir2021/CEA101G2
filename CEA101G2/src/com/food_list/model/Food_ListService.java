package com.food_list.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.store.model.Store_MemDAO;
import com.store.model.Store_MemJDBCDAO;
import com.store.model.Store_MemVO;
import com.store.model.Store_Mem_interface;


public class Food_ListService {
	private Food_List_interface dao;
	private Store_Mem_interface sdao;

	public Food_ListService() {
//		dao = new Food_ListJDBCDAO();
//		sdao = new Store_MemJDBCDAO();
		dao = new Food_ListDAO();
		sdao = new Store_MemDAO();
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
			Integer limit_, String food_info, Integer food_status,byte[]food_img) {
		Food_ListVO food_ListVO = new Food_ListVO();
		food_ListVO.setFood_no(food_no);
		food_ListVO.setStore_no(store_no);
		food_ListVO.setFood_name(food_name);
		food_ListVO.setFood_price(food_price);
		food_ListVO.setLimit_(limit_);
		food_ListVO.setFood_info(food_info);
		food_ListVO.setFood_status(food_status);
		food_ListVO.setFood_img(food_img);
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
	
	
//	========================ListAll餐點上架狀態order by審核與否by Mike========================
	
	public List<Food_ListVO> getAll2() {
		return dao.getAll2();
	}
	
//	========================複合式搜尋 by 麻麻========================
	public Map<String, List<String>> searchbystring(String keyword, String loc) {
		Map<String, List<String>> resultlist = new TreeMap<>();
		List<Food_ListVO> fdlist = dao.getAll();
		List<Store_MemVO> smlist = sdao.getAll();
		Map<String, List<String>> newlist =null;
		
		for (Store_MemVO slist : smlist) {
			List<String> list = new ArrayList<>();
			list.add(slist.getStore_name()+slist.getAddr());
			resultlist.put(slist.getStore_no(), list);
		}
		
		for (Food_ListVO alist : fdlist) {
			resultlist.get(alist.getStore_no()).add(alist.getFood_no()
			+ alist.getFood_name());
		}
		newlist = resultlist.entrySet().stream()
				.filter(a ->a.getValue().toString().contains(loc))
				.filter(f -> f.getValue().toString().contains(keyword))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
//		newlist.keySet().stream().forEach(e -> System.out.println(e));
//		newlist.values().stream().forEach(e -> System.out.println(e));
		return newlist;
		}


}
