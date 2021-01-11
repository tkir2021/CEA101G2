package com.store_scale.model;
import java.util.List;
public interface Store_Scale_interface {
	public void insert(Store_ScaleVO store_ScaleVO);
	public void update(Store_ScaleVO store_ScaleVO);
	public void delete(String food_no);
	public List<Store_ScaleVO> findByPrimaryKey(String food_no);
	public List<Store_ScaleVO> getAll();
}

//public List<Store_ScaleVO> findByPrimaryKey(String food_no);
//未修改前


//public Store_ScaleVO findByPrimaryKey(String store_scale);
//修改後