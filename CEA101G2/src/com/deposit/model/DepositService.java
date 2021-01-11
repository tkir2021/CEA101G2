package com.deposit.model;

import java.sql.Timestamp;
import java.util.List;

public class DepositService {

	private DepositDAO_interface dao;

	public DepositService() {
		dao = new DepositJDBCDAO();
	}

	public DepositVO addDeposit(String mem_no, Integer charge,
			Timestamp charge_time) {

		DepositVO depositVO = new DepositVO();

//		depositVO.setDeposit_no(deposit_no);
		depositVO.setMem_no(mem_no);
		depositVO.setCharge(charge);
		depositVO.setCharge_time(charge_time);
		dao.insert(depositVO);

		return depositVO;
	}

	public DepositVO updateDeposit(String deposit_no, String mem_no, Integer charge,
			Timestamp charge_time) {

		DepositVO depositVO = new DepositVO();
		depositVO.setDeposit_no(deposit_no);
		depositVO.setMem_no(mem_no);
		depositVO.setCharge(charge);
		depositVO.setCharge_time(charge_time);
		dao.insert(depositVO);

		return depositVO;
	}

	public void deleteDeposit(String deposit_no) {
		dao.delete(deposit_no);
	}

	public DepositVO getOneDeposit(String deposit_no) {
		return dao.findByPrimaryKey(deposit_no);
	}
	
	/****************取得會員儲存金記錄 by Sheng*********************/
	public List<DepositVO> getOneMemDeposit(String mem_no) {
		return dao.findByMem_no(mem_no);
	}
	/****************取得會員儲存金記錄 by Sheng*********************/
	
	public List<DepositVO> getAll() {
		return dao.getAll();
	}
}
