package com.group.model;

import java.sql.Date;
import java.util.List;

public class Group_Service {
	
	private Group_DAO_interface dao;
	
	public Group_Service() {
		dao = new Group_JDBCDAO();
	}
	
	public Group_VO addGroup_(String mem_no, String store_no, String booking_no, Integer gp_kind, Integer mem_least, 
			Integer mem_limit, Date dining_date, Date start_date, Date end_date, String gp_info, Integer gp_status) {
		
		Group_VO group_VO = new Group_VO();
		group_VO.setMem_no(mem_no);
		group_VO.setStore_no(store_no);
		group_VO.setBooking_no(booking_no);
		group_VO.setGp_kind(gp_kind);
		group_VO.setMem_least(mem_least);
		group_VO.setMem_limit(mem_limit);
		group_VO.setDining_date(dining_date);
		group_VO.setStart_date(start_date);
		group_VO.setEnd_date(end_date);
		group_VO.setGp_info(gp_info);
		group_VO.setGp_status(gp_status);
		dao.insert(group_VO);
		
		return group_VO;
	}
	
	
	public Group_VO updateGroup_(String group_no,String mem_no, String store_no, String booking_no, Integer gp_kind, Integer mem_least, 
			Integer mem_limit, Date dining_date, Date start_date, Date end_date, String gp_info, Integer gp_status) {
		
		Group_VO group_VO = new Group_VO();
		group_VO.setGroup_no(group_no);
		group_VO.setMem_no(mem_no);
		group_VO.setStore_no(store_no);
		group_VO.setBooking_no(booking_no);
		group_VO.setGp_kind(gp_kind);
		group_VO.setMem_least(mem_least);
		group_VO.setMem_limit(mem_limit);
		group_VO.setDining_date(dining_date);
		group_VO.setStart_date(start_date);
		group_VO.setEnd_date(end_date);
		group_VO.setGp_info(gp_info);
		group_VO.setGp_status(gp_status);
		dao.update(group_VO);
		
		return group_VO;
	}
	
	
	public void deleteGroup_(String group_no) {
		dao.delete(group_no);
	}
	
	public Group_VO getOneGroup_(String group_no) {
		return dao.findByPrimaryKey(group_no);
	}
	
	public Group_VO getOneGpSta(Integer gp_status){
		return dao.getOneGpSta(gp_status);
	}
	
	public List<Group_VO> getAll(){
		return dao.getAll();
	}
}
