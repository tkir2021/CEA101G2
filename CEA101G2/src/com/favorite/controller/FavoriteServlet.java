package com.favorite.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.favorite.model.*;


public class FavoriteServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_pageFavorite.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("store_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入店家編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/select_pageFavorite.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String store_no = null;
				try {
					store_no = str;
				} catch (Exception e) {
					errorMsgs.add("店家編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/select_pageFavorite.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FavoriteService favoriteSvc = new FavoriteService();
				List<FavoriteVO> favoriteVO = favoriteSvc.getOneFavorite(store_no);
				if (favoriteVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/select_pageFavorite.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("favoriteVO", favoriteVO); // 資料庫取出的favoriteVO物件,存入req
				                                            //FavoriteServlet.java(Concroller));
				String url = "/front-customer-end/member/listOneFavorite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFavorite.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/select_pageFavorite.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllFavorite.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String store_no = req.getParameter("store_no");
				
				/***************************2.開始查詢資料****************************************/
				FavoriteService favoriteSvc = new FavoriteService();
				List<FavoriteVO> favoriteVO = favoriteSvc.getOneFavorite(store_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("favoriteVO",favoriteVO);         // 資料庫取出的FavoriteVO物件,存入req
				String url = "/front-customer-end/member/update_favorite_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_favorite_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/listAllFavorite.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_favorite_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String store_no = req.getParameter("store_no").trim();
				
				
				
				String mem_no = req.getParameter("mem_no").trim();
				if (mem_no == null || mem_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}	
				
				FavoriteVO favoriteVO = new FavoriteVO();
				favoriteVO.setStore_no(store_no);
				favoriteVO.setMem_no(mem_no);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("favoriteVO", favoriteVO); // 含有輸入格式錯誤的favoriteVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/update_favorite_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FavoriteService favoriteSvc = new FavoriteService();
				favoriteVO = favoriteSvc.updateFavorite(store_no, mem_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("favoriteVO", favoriteVO); // 資料庫update成功後,正確的的favoriteVO物件,存入req
				String url = "/front-customer-end/member/listOneFavorite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFavorite.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/update_favorite_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addFavorite.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				System.out.print(req.getParameter("store_no"));
				System.out.print(req.getParameter("mem_no"));
				
				String store_no = req.getParameter("store_no");
				String mem_no = req.getParameter("mem_no");
				

				FavoriteVO favoriteVO = new FavoriteVO();
				favoriteVO.setMem_no(mem_no);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("favoriteVO", favoriteVO); // 含有輸入格式錯誤的FavoriteVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/addFavorite.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FavoriteService favoriteSvc = new FavoriteService();
				favoriteVO = favoriteSvc.addFavorite(store_no,mem_no);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-customer-end/member/listAllFavorite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFavorite.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/addFavorite.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllFavorite.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String store_no = req.getParameter("store_no");
				String mem_no = req.getParameter("mem_no");
				
				/***************************2.開始刪除資料***************************************/
				FavoriteService favoriteSvc = new FavoriteService();
				favoriteSvc.deleteFavorite(store_no,mem_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-customer-end/member/listAllFavorite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/listAllFavorite.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
