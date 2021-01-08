
package com.store.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.store.model.Store_MemVO;
import com.store.model.Store_MemService;
import com.store.model.Store_MemVO;
@MultipartConfig
/**@WebServlet(name="Food_ListServlet" , urlPatterns = {"/CEA101G2/front-store-end/foodlist/select_page.jsp"})
 * Servlet implementation class Food_ListServlet
 */
//@WebServlet("/Food_ListServlet")
public class Store_MemServlet extends HttpServlet {
	
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String store_no = req.getParameter("store_no");
				if (store_no == null || (store_no.trim()).length() == 0) {
					errorMsgs.add("請輸入店家編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
		
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-store-end/foodlist/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
			
				/***************************2.開始查詢資料*****************************************/
				Store_MemService store_MemSvc = new Store_MemService();
				Store_MemVO store_MemVO = store_MemSvc.getOneStore_Mem(store_no);
				if (store_MemVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("store_MemVO", store_MemVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-store-end/store/listOneStore_Mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/select_page.jsp");
				failureView.forward(req, res);
			}
		}	
			
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String store_no = req.getParameter("store_no");
				
				/***************************2.開始查詢資料****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
				Store_MemService store_MemSvc = new Store_MemService();
				Store_MemVO store_MemVO = store_MemSvc.getOneStore_Mem(store_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("store_MemVO", store_MemVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-store-end/store/update_Store_Mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/listAllStore_Mem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String store_no = req.getParameter("store_no");
				String store_acct = req.getParameter("store_acct");
				String store_acctReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (store_acct == null || store_acct.trim().length() == 0) {
					errorMsgs.add("店家帳號: 請勿空白");
				} else if(!store_acct.trim().matches(store_acctReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("店家帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String store_pwd = req.getParameter("store_pwd").trim();
				if (store_pwd == null || store_pwd.trim().length() == 0) {
					errorMsgs.add("店家密碼請勿空白");
				}
				
				String store_name = req.getParameter("store_name").trim();
				if (store_name == null || store_name.trim().length() == 0) {
					errorMsgs.add("店家名稱請勿空白");
				}
				
				
				
				String addr = req.getParameter("addr").trim();
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("地址名稱請勿空白");
				}
				
				String open_dates = req.getParameter("open_dates").trim();
				if (open_dates == null || open_dates.trim().length() == 0) {
					errorMsgs.add("營業時間請勿空白");
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("Email請勿空白");
				}
				
				
				String s_category = req.getParameter("s_category").trim();
				if (s_category == null || s_category.trim().length() == 0) {
					errorMsgs.add("分類請勿空白");
				}
				
				
				String store_info = req.getParameter("store_info").trim();
				if (store_info == null || store_info.trim().length() == 0) {
					errorMsgs.add("店家簡介請勿空白");
				}
				
				
				


				Integer upload_status = null;
				try {
					upload_status = new Integer(req.getParameter("upload_status").trim());
				} catch (NumberFormatException e) {
					upload_status = 0;
					errorMsgs.add("上架狀態請填數字.");
				}
				
				Integer s_permission = null;
				try {
					s_permission = new Integer(req.getParameter("s_permission").trim());
				} catch (NumberFormatException e) {
					s_permission = 0;
					errorMsgs.add("權限狀態請填數字.");
				}
				
				Integer sum_grade = null;
				try {
					sum_grade = new Integer(req.getParameter("sum_grade").trim());
				} catch (NumberFormatException e) {
					sum_grade = 0;
					errorMsgs.add("累積檢舉評分填數字.");
				}
				
				Integer blocked = null;
				try {
					blocked = new Integer(req.getParameter("blocked").trim());
				} catch (NumberFormatException e) {
					blocked = 0;
					errorMsgs.add("被檢舉次數請填數字.");
				}
				
				Double star_total = null;
				try {
					star_total = new Double(req.getParameter("star_total").trim());
				} catch (NumberFormatException e) {
					star_total = 0.0;
					errorMsgs.add("累積檢舉評分填數字.");
				}
				
				Integer star_times = null;
				try {
					star_times = new Integer(req.getParameter("star_times").trim());
				} catch (NumberFormatException e) {
					star_times = 0;
					errorMsgs.add("累積總評價次數請填數字.");
				}
				
				Integer table_limit = null;
				try {
					table_limit = new Integer(req.getParameter("table_limit").trim());
				} catch (NumberFormatException e) {
					table_limit = 0;
					errorMsgs.add("桌位上限請填數字.");
				}
				
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
//				store_MemVO.setRest_img(rest_img);	
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("store_MemVO", store_MemVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/update_Store_Mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
//				/***************************2.開始修改資料*****************************************/

				Store_MemService store_MemSvc = new Store_MemService();
				store_MemVO = store_MemSvc.update_Store_Mem_input(store_no,store_acct,store_pwd, store_name,addr, open_dates, email, s_category,store_info,upload_status,s_permission,sum_grade,blocked,star_total,star_times,table_limit);
				              
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("store_MemVO", store_MemVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-store-end/store/listOneStore_Mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/update_Store_Mem_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//        	System.out.println(req.getParameter("store_no"));
     
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			//(\u4e00-\u9fa5)只能中文
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String store_acct = req.getParameter("store_acct");
				String store_noReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (store_acct == null || store_acct.trim().length() == 0) {
					errorMsgs.add("店家帳號: 請勿空白");
				} else if(!store_acct.trim().matches(store_noReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("店家帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String store_pwd = req.getParameter("store_pwd").trim();
				if (store_pwd == null || store_pwd.trim().length() == 0) {
					errorMsgs.add("店家密碼請勿空白");
				}
				
				String store_name = req.getParameter("store_name").trim();
				if (store_name == null || store_name.trim().length() == 0) {
					errorMsgs.add("店家名稱請勿空白");
				}
				
				
				
				String addr = req.getParameter("addr").trim();
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("地址名稱請勿空白");
				}
				
				String open_dates = req.getParameter("open_dates").trim();
				if (open_dates == null || open_dates.trim().length() == 0) {
					errorMsgs.add("營業時間請勿空白");
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("Email請勿空白");
				}
				
				
				String s_category = req.getParameter("s_category").trim();
				if (s_category == null || s_category.trim().length() == 0) {
					errorMsgs.add("分類請勿空白");
				}
				
				
				String store_info = req.getParameter("store_info").trim();
				if (store_info == null || store_info.trim().length() == 0) {
					errorMsgs.add("店家簡介請勿空白");
				}
				
				
				Integer upload_status = null;
				try {
					upload_status = new Integer(req.getParameter("upload_status").trim());
				} catch (NumberFormatException e) {
					upload_status = 0;
					errorMsgs.add("上架狀態請填數字.");
				}
				
				Integer s_permission = null;
				try {
					s_permission = new Integer(req.getParameter("s_permission").trim());
				} catch (NumberFormatException e) {
					s_permission = 0;
					errorMsgs.add("權限狀態請填數字.");
				}
				
				Integer sum_grade = null;
				try {
					sum_grade = new Integer(req.getParameter("sum_grade").trim());
				} catch (NumberFormatException e) {
					sum_grade = 0;
					errorMsgs.add("累積檢舉評分填數字.");
				}
				
				Integer blocked = null;
				try {
					blocked = new Integer(req.getParameter("blocked").trim());
				} catch (NumberFormatException e) {
					blocked = 0;
					errorMsgs.add("被檢舉次數請填數字.");
				}
				
				Double star_total = null;
				try {
					star_total = new Double(req.getParameter("star_total").trim());
				} catch (NumberFormatException e) {
					star_total = 0.0;
					errorMsgs.add("累積檢舉評分填數字.");
				}
				
				Integer star_times = null;
				try {
					star_times = new Integer(req.getParameter("star_times").trim());
				} catch (NumberFormatException e) {
					star_times = 0;
					errorMsgs.add("累積總評價次數請填數字.");
				}
				
				Integer table_limit = null;
				try {
					table_limit = new Integer(req.getParameter("table_limit").trim());
				} catch (NumberFormatException e) {
					table_limit = 0;
					errorMsgs.add("桌位上限請填數字.");
				}
								
				
				byte rest_img2[] = null;
				try {					
					InputStream in =req.getPart("rest_img").getInputStream();
					rest_img2= new byte[in.available()];
					in.read(rest_img2);
					in.close();					
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("上傳圖片失敗");
					errorMsgs.add("沒有圖片");
				}
				
				
				 
				
				Store_MemVO  store_MemVO  = new Store_MemVO();
				
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
				store_MemVO.setRest_img(rest_img2);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("store_MemVO", store_MemVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/addStore_Mem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				Store_MemService store_MemSvc = new Store_MemService();
				store_MemVO = store_MemSvc.addStore_Mem(store_acct,store_pwd, store_name,addr, open_dates, email, s_category,store_info,upload_status,s_permission,sum_grade,blocked,star_total,star_times,table_limit,rest_img2);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-store-end/store/listAllStore_Mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/addStore_Mem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				
				String store_no = req.getParameter("store_no");
				
				/***************************2.開始刪除資料***************************************/
				Store_MemService store_MemSvc = new Store_MemService();
				store_MemSvc.deleteStore_Mem(store_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-store-end/store/listAllStore_Mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/listAllStore_Mem.jsp");
				failureView.forward(req, res);
				}
		}
			if("getOneImage".equals(action)) {
				res.setContentType("image/gif");
				ServletOutputStream out = res.getOutputStream();
			
			try {
			String store_no = req.getParameter("store_no");
			Store_MemService store_MemService = new Store_MemService();
			Store_MemVO store_MemVO = new Store_MemVO();
			store_MemVO = store_MemService.getOneStore_Mem(store_no);
			byte[] pic = store_MemVO.getRest_img();

			out.write(pic);

			
		}			
		
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		
		
		
		
		
		
		
			}
		}

	}
	
  }

