package com.booking.model;

import java.sql.Date;
import java.util.List;

public class B_orderService {

	private B_orderDAO_interface dao;

	public B_orderService() {
		dao = new B_orderDAO();
	}

	public B_orderVO addOrder(String memno, String storeno, String groupno, Date bookingdate,
			String timeperiod, Integer people) {

		B_orderVO b_orderVO = new B_orderVO();

		//b_orderVO.setBookingno(bookingno);
		b_orderVO.setMemno(memno);
		b_orderVO.setStoreno(storeno);
		b_orderVO.setGroupno(groupno);
		b_orderVO.setBookingdate(bookingdate);
		b_orderVO.setTimeperiod(timeperiod);
		b_orderVO.setPeople(people);
		//b_orderVO.setBookingstatus(bookingstatus);
		//b_orderVO.setAttendstatus(attendstatus);
		//b_orderVO.setGivestar(givestar);
		dao.insert(b_orderVO);

		return b_orderVO;
	}
	
	//GiveStar, AttendStatus, Booking 會用到修改
	public B_orderVO updateOrder(String bookingno, String memno, String storeno, String groupno, Date bookingdate,
			String timeperiod, Integer people, Integer bookingstatus, Integer attendstatus, Double givestar) {
		
		B_orderVO b_orderVO = new B_orderVO();
		
		b_orderVO.setBookingno(bookingno);
		b_orderVO.setMemno(memno);
		b_orderVO.setStoreno(storeno);
		b_orderVO.setGroupno(groupno);
		b_orderVO.setBookingdate(bookingdate);
		b_orderVO.setTimeperiod(timeperiod);
		b_orderVO.setPeople(people);
		b_orderVO.setBookingstatus(bookingstatus);
		b_orderVO.setAttendstatus(attendstatus);
		b_orderVO.setGivestar(givestar);		
		dao.update(b_orderVO);
		
		return b_orderVO;
	}

	/*******************更新評分 by Sheng********************/
	public B_orderVO upGivestar(String bookingno, double givestar) {
		B_orderVO b_orderVO = new B_orderVO();
		b_orderVO.setBookingno(bookingno);
		b_orderVO.setGivestar(givestar);
		dao.upGivestar(bookingno, givestar);
		
		return b_orderVO;
	}
	/*******************更新出席狀態 by Bella********************/
	 public B_orderVO upAttendStatus(String bookingno,Integer attendStatus ) {
	  B_orderVO b_orderVO = new B_orderVO();
	  b_orderVO.setBookingno(bookingno);
	  b_orderVO.setAttendstatus(attendStatus);
	  dao.upAttendstatus(bookingno, attendStatus);
	  
	  return b_orderVO;
	 }
	
	public B_orderVO getOneOrder(String bookingno) {
		return dao.findByPrimaryKey(bookingno);
	}

	public List<B_orderVO> getOrderByNo(String number){
		return dao.findByPrimaryKey2(number);
	}
		
	public List<B_orderVO> getAll() {
		return dao.getAll();
	}
	public B_orderVO getPeople(String storeno, Date bookingdate,String timeperiod){
		return dao.findByPrimaryKey3(storeno, bookingdate, timeperiod);		
	}
}
