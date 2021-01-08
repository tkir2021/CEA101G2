package com.store.model;
import java.util.*;
public interface Food_List_interface {
	 public void insert(Food_ListVO food_ListVO);
	 public void update(Food_ListVO food_ListVO);
	 public void delete(String food_ListVO);
	 public Food_ListVO findByPrimaryKey(String food_no);
	 public List<Food_ListVO> getAll();	 
	 /************************購物車 20210101 by 皓哥*************************/
	 public List<Food_ListVO> getAllFood(String store_no);	 
	 /************************購物車 20210101 by 皓哥*************************/
}

//public Food_ListVO findByPrimaryKey(String food_list);
//�ק�e