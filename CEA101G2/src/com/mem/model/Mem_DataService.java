package com.mem.model;

import java.util.List;

import com.orderdetail.model.OrderDetailVO;
import com.ordermaster.model.OrderMasterVO;

public class Mem_DataService {

	private Mem_DataDAO_interface dao;

	public Mem_DataService() {
		dao = new Mem_DataJDBCDAO();
	}

	public Mem_DataVO addMem(Integer mem_grade, String mem_acct,
			String mem_pwd, String mem_name, String men_phone,String mem_mail,byte[] mem_img,Integer mem_auth,Integer report_count,Integer consume_times,Integer deposit) {

		Mem_DataVO mem_dataVO = new Mem_DataVO();

		mem_dataVO.setMem_grade(mem_grade);
		mem_dataVO.setMem_acct(mem_acct);
		mem_dataVO.setMem_pwd(mem_pwd);
		mem_dataVO.setMem_name(mem_name);
		mem_dataVO.setMen_phone(men_phone);
		mem_dataVO.setMem_mail(mem_mail);
		mem_dataVO.setMem_img(mem_img);
		mem_dataVO.setMem_auth(mem_auth);
		mem_dataVO.setReport_count(report_count);
		mem_dataVO.setConsume_times(consume_times);
		mem_dataVO.setDeposit(deposit);
		dao.insert(mem_dataVO);

		return mem_dataVO;
	}

	public Mem_DataVO updateMem(String mem_no, String mem_pwd, String mem_name, String men_phone,String mem_mail,byte[] mem_img) {

		Mem_DataVO mem_dataVO = new Mem_DataVO();

		mem_dataVO.setMem_no(mem_no);
		mem_dataVO.setMem_pwd(mem_pwd);
		mem_dataVO.setMem_name(mem_name);
		mem_dataVO.setMen_phone(men_phone);
		mem_dataVO.setMem_mail(mem_mail);
		mem_dataVO.setMem_img(mem_img);
		
		dao.update(mem_dataVO);

		return mem_dataVO;
	}
	
	public void updateStatus(String mem_acct, Integer mem_auth) {
		dao.updateStatus(mem_acct, mem_auth);
	}
	
	//----------------------------------20200107by Bella------------------------------------------------- 
	 public Mem_DataVO updateMemAuth(String mem_no ,Integer mem_auth) {
	  Mem_DataVO mem_dataVO = new Mem_DataVO(); 
	  mem_dataVO.setMem_no(mem_no);
	  mem_dataVO.setMem_auth(mem_auth);
	  
	  dao.updateAuth(mem_dataVO);
	  return mem_dataVO;
	 }
	 
	 /************************購物車：更新會員儲值金 by Sheng*************************/
	 public void updateDeposit_ByShopping(Mem_DataVO mem_dataVO, OrderMasterVO orderMasterVO, List<OrderDetailVO> list) {
		 dao.updateDeposit_ByShopping(mem_dataVO, orderMasterVO, list);
	 }
	 /************************購物車：更新會員儲值金 by Sheng*************************/
	 
	 
	 /************************儲值金 by 宏哥*************************/
	 public Mem_DataVO updateDeposit(String mem_no, Integer deposit) {

			Mem_DataVO mem_dataVO = new Mem_DataVO();

			mem_dataVO.setMem_no(mem_no);
			mem_dataVO.setDeposit(deposit);
			dao.updateDeposit(mem_dataVO);
			return mem_dataVO;
		}
	 /************************儲值金 by 宏哥*************************/
	 
	 
	public void deleteMem(String mem_no) {
		dao.delete(mem_no);
	}

	public Mem_DataVO getOneMem(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}
	
	public Mem_DataVO getMemAcc(String mem_acct) {
		return dao.findByAccount(mem_acct);
	}
	
	public Mem_DataVO getAccNoImg(String mem_acct) {
		return dao.findByAccNoImg(mem_acct);
	}

	public List<Mem_DataVO> getAll() {
		return dao.getAll();
	}
}
