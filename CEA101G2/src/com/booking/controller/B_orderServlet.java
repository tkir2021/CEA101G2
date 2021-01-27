package com.booking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.booking.model.B_orderService;
import com.booking.model.B_orderVO;
import com.mem.model.MailService;
import com.mem.model.Mem_DataService;
import com.mem.model.Mem_DataVO;
import com.ordermaster.model.OrderMasterService;
import com.ordermaster.model.OrderMasterVO;
import com.store.model.Store_MemService;
import com.store.model.Store_MemVO;

public class B_orderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		/********************** Ajax新增資料 *****************************/
		if ("insertByAjax".equals(action)) {
			JSONObject jsinsert = new JSONObject();
			Mem_DataVO memVO = (Mem_DataVO) session.getAttribute("memVO");
			Store_MemVO storeVO = (Store_MemVO) session.getAttribute("storeVO");
			String storeno = storeVO.getStore_no();
			String memno = memVO.getMem_no();
			String selectDate = req.getParameter("bookingdate");
			String timeperiod = req.getParameter("openHour");
			Integer people = Integer.parseInt(req.getParameter("people"));

			B_orderService orderSvc = new B_orderService();
			B_orderVO orderVO = orderSvc.addOrder(memno, storeno, "", java.sql.Date.valueOf(selectDate), timeperiod,
					people);

			MailService mail = new MailService();
			String content = "[訂位通知] 親愛的" + memVO.getMem_name() + "您好，" + "\n" + "以下為您的訂位資訊：" + "\n" + "店家名稱："
					+ storeVO.getStore_name() + "\n" + "店家地址" + storeVO.getAddr() + "訂位日期：" + selectDate + "\n"
					+ "貴賓人數：" + people + "位" + "\n" + "訂位時段" + timeperiod + "\n" + "感謝您的訂位！";
			mail.sendMail("bz850308@gmail.com", "非食不可Let's Eat美食平台", content);
			try {
				jsinsert.put("orderVO", orderVO.getMemno());
				jsinsert.put("orderVO", orderVO.getStoreno());
				jsinsert.put("orderVO", orderVO.getTimeperiod());
				jsinsert.put("orderVO", orderVO.getBookingdate());
				jsinsert.put("orderVO", orderVO.getPeople());
				jsinsert.put("memVO", memVO.getMem_name());
				// res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(jsinsert.toString());
				System.out.println("--------------------------------");
				System.out.println("jsinsert:" + jsinsert.toString());
				out.flush();
				out.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		;

		/********************** Ajax查詢訂位人數總和 *************************/
		if ("getPeople".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			JSONObject jsonObject = new JSONObject();
			Store_MemVO storeVO = (Store_MemVO) session.getAttribute("storeVO");
			String storeno = storeVO.getStore_no();
			String open = req.getParameter("openHour");
			String Date = req.getParameter("bookingdate");
			System.out.println("storeno:" + storeno);
			System.out.println("open:" + open);
			System.out.println("Date:" + Date);
			B_orderService orderSvc = new B_orderService();
			B_orderVO peopleSum = orderSvc.getPeople(storeno, java.sql.Date.valueOf(Date), open);
			System.out.println(peopleSum.getPeople());
			try {
				jsonObject.put("peopleSum", peopleSum.getPeople());
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(jsonObject.toString());
//		    System.out.println(String.valueOf(jsonObject));
				out.flush();
				out.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		/*********************** Servlet 新增資料 *************************/
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String storeno = (String) session.getAttribute("storeno");
			String memno = (String) session.getAttribute("memno");

			/******************* step1.錯誤驗證 *************************/
			try {
				// String memno = (String) req.getAttribute("memno");
				System.out.println(memno);
				String memnoReg = "/^[A-Z0-9]{10}$/";
				if (memno == null || memno.trim().length() == 0) {
					errorMsgs.add("會員編號請勿留白");
//				} else if (!memno.trim().matches(memnoReg)) {
//					errorMsgs.add("會員編號格式錯誤");
				}

				// String storeno = (String) req.getAttribute("storeno");
				String storenoReg = "/^[A-Z0-9]{10}$/";
				if (storeno == null || storeno.trim().length() == 0) {
					errorMsgs.add("店家編號請勿留白");
//				} else if (!storeno.trim().matches(storenoReg)) {
//					errorMsgs.add("店家編號格式錯誤");
				}

				String groupno = req.getParameter("groupno");
				String groupnoReg = "/^[A-Z0-9]{10}$/";
				if (groupno != null) {
					if (!groupno.trim().matches(groupnoReg)) {
						errorMsgs.add("揪團編號格式錯誤");
					}
				}

				java.sql.Date bookingdate = null;
				try {
					bookingdate = java.sql.Date.valueOf(req.getParameter("bookingdate").trim());
				} catch (IllegalArgumentException e) {
					bookingdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請在下方選擇日期!");
				}
				String timeperiod = req.getParameter("timeperiod").trim();
				Integer people = new Integer(req.getParameter("people").trim());

				// 將取得的資料放進去VO拆解
				B_orderVO b_order = new B_orderVO();
				b_order.setMemno(memno);
				b_order.setStoreno(storeno);
				b_order.setGroupno(groupno);
				b_order.setBookingdate(bookingdate);
				b_order.setTimeperiod(timeperiod);
				b_order.setPeople(people);

				// 如果新增資料有問題則停在當前頁面，並且保留輸入的資料
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("b_orderVO", b_order);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/booking/addBooking.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************** step2.開始將資料送進DB *************************/
				B_orderService b_orderSvc = new B_orderService();
				b_order = b_orderSvc.addOrder(memno, storeno, groupno, bookingdate, timeperiod, people);
				/*************** step3.新增完成,跳轉下一頁 *****************/
				session.setAttribute("b_order", b_order);
				String url = "/front-customer-end/booking/orderCheck.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/booking/addBooking.jsp");
				failureView.forward(req, res);
			}
		}

		/************************ 取得訂位資料 by Sheng *************************/

		if ("bookingDisplay".equals(action)) {
			res.setContentType("text/html;charset=UTF-8");
			String memno = req.getParameter("memno");
			PrintWriter out = res.getWriter();
			B_orderService orderSvc = new B_orderService();
			Store_MemService smSvc = new Store_MemService();

			JSONArray jsar = new JSONArray(orderSvc.getOrderByNo(memno));

			for (int i = 0; i < jsar.length(); i++) {

				try {
					JSONObject jsob = jsar.getJSONObject(i);
					String ordercommit = jsob.getString("ordercommit");
					String storeno = jsob.getString("storeno");
					String storename = smSvc.getOneStore_Mem(storeno).getStore_name();
					jsob.put("storename", storename);
					String dateFormat[] = ordercommit.split("\\.");
					jsob.put("ordercommit", dateFormat[0]);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

//			System.out.println("List to JSON: " + jsar.toString());
			out.println(jsar.toString());
		}
		/************************ 取得訂位資料 by Sheng *************************/

		/************************ 對訂單的店家評分 by Sheng *************************/

		if ("Score".equals(action)) {
			System.out.println(action);
			String bookingno = req.getParameter("bookingno");
			double givestar = new Double(req.getParameter("givestar"));

			B_orderService b_orderSvc = new B_orderService();
			b_orderSvc.upGivestar(bookingno, givestar);
			PrintWriter out = res.getWriter();
			out.print("OK");
			out.flush();
			out.close();
		}
		/*********************** 更新訂位出席狀態 by Bella *************************/
		if ("updateAttend_status".equals(action)) {
			String bookingno = req.getParameter("bookingno");
			Integer attend = new Integer(req.getParameter("attend"));
			try {
				B_orderService b_orderSvc = new B_orderService();
				B_orderVO bookingVO = b_orderSvc.upAttendStatus(bookingno, attend);

				req.setAttribute("bookingVO", bookingVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-store-end/store/store_detail.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/store/store_detail.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
