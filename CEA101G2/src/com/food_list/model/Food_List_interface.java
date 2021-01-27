package com.food_list.model;
import java.util.*;

public interface Food_List_interface {
	 public void insert(Food_ListVO food_ListVO);
	 public void update(Food_ListVO food_ListVO);
	 
	// ========================更新餐點上架狀態 by Mike========================
	 public void updateStatus(String food_no, Integer food_status);
	 
//		========================ListAll餐點上架狀態order by審核與否by Mike========================
	 public List<Food_ListVO> getAll2();
		 
	 public void delete(String food_ListVO);
	 public Food_ListVO findByPrimaryKey(String food_no);
	 public List<Food_ListVO> getAll();
		 
	 /************************購物車：取得所有食物列表 by Sheng*************************/
	 public List<Food_ListVO> getAllFood(String store_no);	 
	 /************************購物車：取得所有食物列表 by Sheng*************************/
	 
	 //20210109By 零零
	  public List<Food_ListVO> getAll(String store_no);
}

//public Food_ListVO findByPrimaryKey(String food_list);
