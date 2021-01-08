package com.group_mem.model;

import java.util.List;

public class Group_memService {

	private Group_memDAO_interface dao;
	
	public Group_memService() {
		dao = new Group_memJDBCDAO();
	}
	
	public Group_memVO addGroup_mem(String group_no, String mem_no) {
		
		Group_memVO group_memVO = new Group_memVO();
		
		group_memVO.setGroup_no(group_no);
		group_memVO.setMem_no(mem_no);
		dao.insert(group_memVO);
		
		return group_memVO;
	}
	
	public void deleteGroup_mem(String group_no, String mem_no) {
		dao.delete(group_no, mem_no);		
	}
	
	public List<Group_memVO> getOneGroup_mem(String group_no) {				
		return dao.findByPrimaryKey(group_no);	
	}
	
	public List<Group_memVO> getAll() {
		return dao.getAll();
	}
	
	
}
