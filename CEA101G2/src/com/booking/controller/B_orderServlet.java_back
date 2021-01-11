package com.booking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

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
		
		/********************** 查詢單筆資料 *************************/
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String storeno = req.getParameter("storeno");
			String memno = req.getParameter("memno");

			B_orderService orderSvc = new B_orderService();

			if (storeno != null) {
				try {
					// 模擬店家已經登入狀態
					//session.setAttribute("storeno", storeno);
					List<B_orderVO> SorderList = orderSvc.getOrderByNo(storeno);
					req.setAttribute("SorderList", SorderList);
					RequestDispatcher success = req
							.getRequestDispatcher("/front-store-end/booking/S_listAllBooking.jsp");
					success.forward(req, res);
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("店家輸入失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
				}
				
			} else if (memno != null) {
				try {
					// 模擬會員已經登入狀態
					session.setAttribute("memno", memno);
					List<B_orderVO> MorderList = orderSvc.getOrderByNo(memno);
					req.setAttribute("MorderList", MorderList);
					RequestDispatcher success = req
							.getRequestDispatcher("/front-customer-end/booking/M_listAllBooking.jsp");
					success.forward(req, res);
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("會員輸入失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
				}
				
			}

		}
		
		/************************取得訂位資料 by Sheng*************************/
		
		if ("bookingDisplay".equals(action)) {
			res.setContentType("text/html;charset=UTF-8");
			String memno = req.getParameter("memno");
			PrintWriter out = res.getWriter();
			B_orderService orderSvc = new B_orderService();
			Store_MemService smSvc = new  Store_MemService();
			
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
		/************************取得訂位資料 by Sheng*************************/
		
		
		
		/************************對訂單的店家評分 by Sheng*************************/
		
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
		/************************對訂單的店家評分 by Sheng*************************/
		
		

		/*********************** 新增資料 *************************/
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String storeno = (String) session.getAttribute("storeno");
			String memno = (String) session.getAttribute("memno");
			
			/******************* step1.錯誤驗證 *************************/
			try {
				//String memno = (String) req.getAttribute("memno");
				System.out.println(memno);
				String memnoReg = "/^[A-Z0-9]{10}$/";
				if (memno == null || memno.trim().length() == 0) {
					errorMsgs.add("會員編號請勿留白");
//				} else if (!memno.trim().matches(memnoReg)) {
//					errorMsgs.add("會員編號格式錯誤");
				}

				//String storeno = (String) req.getAttribute("storeno");
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

	}
}
