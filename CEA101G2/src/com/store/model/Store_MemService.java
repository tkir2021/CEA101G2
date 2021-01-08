package com.store.model;

import java.util.List;



public class Store_MemService {
	
	private Store_Mem_interface dao;
	
	public Store_MemService() {
		dao = new Store_MemJDBCDAO();
	}
	
	public Store_MemVO addStore_Mem(String store_acct, String store_pwd, String store_name, String addr, String open_dates, String email, String s_category, String store_info, 
			Integer upload_status, Integer s_permission, Integer sum_grade, Integer blocked, Double star_total, Integer star_times, Integer table_limit,byte[] rest_img) {
//		¹Ï¤ù¥Î:byte[] rest_img 
		Store_MemVO store_MemVO = new Store_MemVO();
		 store_MemVO.setStore_acct(store_acct);
		 store_MemVO.setStore_pwd(store_pwd);
		 store_MemVO.setStore_name(store_name);
		 store_MemVO.setAddr(addr);
		 store_MemVO.setOpen_dates(open_dates);
		 store_MemVO.setEmail(email);
		 store_MemVO.setS_category(s_category);
		 store_MemVO.setStore_info(store_info);
		 store_MemVO.setUpload_status(upload_status);
		 store_MemVO.setS_permission(s_permission);
		 store_MemVO.setSum_grade(sum_grade);
		 store_MemVO.setBlocked(blocked);
		 store_MemVO.setStar_total(star_total);
		 store_MemVO.setStar_times(star_times);
		 store_MemVO.setTable_limit(table_limit);
		 store_MemVO.setRest_img(rest_img);		
		 
		 dao.insert(store_MemVO);
		
		return store_MemVO;
	}

	
	public Store_MemVO update_Store_Mem_input(String store_no, String store_acct, String store_pwd, String store_name, String addr, String open_dates, String email, String s_category, String store_info, 
			Integer upload_status, Integer s_permission, Integer sum_grade, Integer blocked, Double star_total, Integer star_times, Integer table_limit ) {
		
		Store_MemVO store_MemVO = new Store_MemVO();
		 store_MemVO.setStore_no(store_no);
		 store_MemVO.setStore_acct(store_acct);
		 store_MemVO.setStore_pwd(store_pwd);
		 store_MemVO.setStore_name(store_name);
		 store_MemVO.setAddr(addr);
		 store_MemVO.setOpen_dates(open_dates);
		 store_MemVO.setEmail(email);
		 store_MemVO.setS_category(s_category);
		 store_MemVO.setStore_info(store_info);
		 store_MemVO.setUpload_status(upload_status);
		 store_MemVO.setS_permission(s_permission);
		 store_MemVO.setSum_grade(sum_grade);
		 store_MemVO.setBlocked(blocked);
		 store_MemVO.setStar_total(star_total);
		 store_MemVO.setStar_times(star_times);
		 store_MemVO.setTable_limit(table_limit);
//		 store_MemVO.setRest_img(rest_img);		
		 dao.update(store_MemVO);
		return store_MemVO;
	}
	
		public void deleteStore_Mem(String store_no) {
			dao.delete(store_no);
		}
	
		public Store_MemVO getOneStore_Mem(String store_no) {
			return dao.findByPrimaryKey(store_no);
		}
	
		public List<Store_MemVO> getAll(){
			return dao.getAll();
		}
}
