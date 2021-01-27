package com.food_list.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.food_list.model.Food_ListService;
import com.food_list.model.Food_ListVO;

@MultipartConfig
/**
 * @WebServlet(name="Food_ListServlet" , urlPatterns =
 * {"/CEA101G2/front-store-end/foodlist/select_page.jsp"}) Servlet
 * implementation class Food_ListServlet
 */
//@WebServlet("/Food_ListServlet")
public class Food_ListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String food_no = req.getParameter("food_no");
				if (food_no == null || (food_no.trim()).length() == 0) {
					errorMsgs.add("請輸入餐點編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/foodlist/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Food_ListService food_ListSvc = new Food_ListService();
				Food_ListVO food_ListVO = food_ListSvc.getOneFood_List(food_no);
				if (food_ListVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/foodlist/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("food_ListVO", food_ListVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-store-end/foodlist/listOneFood_List.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/foodlist/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String food_no = req.getParameter("food_no");

				/*************************** 2.開始查詢資料 ****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
				Food_ListService foodListSvc = new Food_ListService();
				Food_ListVO food_ListVO = foodListSvc.getOneFood_List(food_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("food_ListVO", food_ListVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-store-end/store/update_food_List_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/listAllFood_List.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String food_no = req.getParameter("food_no");
				String store_no = req.getParameter("store_no");
				String store_noReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (store_no == null || store_no.trim().length() == 0) {
					errorMsgs.add("店家編號: 請勿空白");
				} else if (!store_no.trim().matches(store_noReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("店家編號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String food_name = req.getParameter("food_name").trim();
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("餐點名稱請勿空白");
				}

//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

				Integer food_price = null;
				try {
					food_price = new Integer(req.getParameter("food_price").trim());
				} catch (NumberFormatException e) {
					food_price = 0;
					errorMsgs.add("售價請填數字.");
				}

				Integer limit_ = null;
				try {
					limit_ = new Integer(req.getParameter("limit_").trim());
				} catch (NumberFormatException e) {
					limit_ = 0;
					errorMsgs.add("限制可修售數量請填數字.");
				}

				String food_info = req.getParameter("food_info").trim();
				if (food_info == null || food_name.trim().length() == 0) {
					errorMsgs.add("餐點簡介請勿空白");
				}
				
				byte food_img2[] = null;
			    try {
			     Part part = req.getPart("food_img2");
			     if(part.getContentType() != null ) {
			     InputStream in = req.getPart("food_img2").getInputStream();
			     food_img2 = new byte[in.available()];
			     in.read(food_img2);
			     in.close();
			     }else {
			      System.out.println("no upload");
			      Food_ListService food_listSvc = new Food_ListService();
			      Food_ListVO food_listVO = food_listSvc.getOneFood_List(food_no);
			      food_img2 = food_listVO.getFood_img();
			      System.out.println(food_img2);
			      
			     }
			    } catch (Exception e) {
			     e.printStackTrace();
			     System.out.println("上傳圖片失敗");
			     errorMsgs.add("沒有圖片");
			    }

				Integer food_status = new Integer(req.getParameter("food_status").trim());

				Food_ListVO food_ListVO = new Food_ListVO();
				food_ListVO.setFood_no(food_no);
				food_ListVO.setStore_no(store_no);
				food_ListVO.setFood_name(food_name);
				food_ListVO.setFood_price(food_price);
				food_ListVO.setLimit_(limit_);
				food_ListVO.setFood_info(food_info);
				food_ListVO.setFood_status(food_status);
				food_ListVO.setFood_img(food_img2);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("food_ListVO", food_ListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/update_food_List_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

//				/***************************2.開始修改資料*****************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
				Food_ListService foodListSvc = new Food_ListService();
			    food_ListVO = foodListSvc.update_food_List_input(food_no, store_no, food_name, food_price, limit_,
			      food_info, food_status,food_img2);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("food_ListVO", food_ListVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-store-end/store/listAllFood_List.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/update_food_List_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println(req.getParameter("store_no"));
			System.out.println(req.getParameter("food_name"));
			System.out.println(req.getParameter("limit_"));
			System.out.println(req.getParameter("food_info"));
			System.out.println(req.getParameter("food_price"));
			System.out.println(req.getParameter("food_status"));
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// (\u4e00-\u9fa5)只能中文

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String store_no = req.getParameter("store_no");
				String store_noReg = "^[(a-zA-Z0-9_)]{2,10}$";
				if (store_no == null || store_no.trim().length() == 0) {
					errorMsgs.add("店家編號: 請勿空白");
				} else if (!store_no.trim().matches(store_noReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("店家編號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String food_name = req.getParameter("food_name").trim();
				if (food_name == null || food_name.trim().length() == 0) {
					errorMsgs.add("餐點名稱請勿空白");
				}

//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

				Integer food_price = null;
				try {
					food_price = new Integer(req.getParameter("food_price").trim());
				} catch (NumberFormatException e) {
					food_price = 0;
					errorMsgs.add("售價請填數字.");
				}

				Integer limit_ = null;
				try {
					limit_ = new Integer(req.getParameter("limit_").trim());
				} catch (NumberFormatException e) {
					limit_ = 0;
					errorMsgs.add("限制可修售數量請填數字.");
				}

				String food_info = req.getParameter("food_info").trim();
				if (food_info == null || food_name.trim().length() == 0) {
					errorMsgs.add("餐點簡介請勿空白");
				}
				byte food_img2[] = null;
				try {
					InputStream in = req.getPart("food_img").getInputStream();
					food_img2 = new byte[in.available()];
					in.read(food_img2);
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("上傳圖片失敗");
					errorMsgs.add("沒有圖片");
				}

				Integer food_status = new Integer(req.getParameter("food_status").trim());

				Food_ListVO food_ListVO = new Food_ListVO();
				food_ListVO.setStore_no(store_no);
				food_ListVO.setFood_name(food_name);
				food_ListVO.setFood_price(food_price);
				food_ListVO.setLimit_(limit_);
				food_ListVO.setFood_info(food_info);
				food_ListVO.setFood_status(food_status);
				food_ListVO.setFood_img(food_img2);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("Food_ListVO", food_ListVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/store/addFood_List.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Food_ListService foodListSvc = new Food_ListService();
				food_ListVO = foodListSvc.addFood_List(store_no, food_name, food_price, limit_, food_info, food_status,
						food_img2);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String url = "/front-store-end/store/listAllFood_List.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/store/addFood_List.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/

				String food_no = req.getParameter("food_no");

				/*************************** 2.開始刪除資料 ***************************************/
				Food_ListService foodListSvc = new Food_ListService();
				foodListSvc.deleteFood_List(food_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-store-end/store/listAllFood_List.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/store/listAllFood_List.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneImage".equals(action)) {
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			try {
				String food_no = req.getParameter("food_no");
				Food_ListService food_ListService = new Food_ListService();
				Food_ListVO food_ListVO = new Food_ListVO();
				food_ListVO = food_ListService.getOneFood_List(food_no);
//				byte[] pic = food_ListVO.getFood_img();
				byte[] pic = null;


				if (!(food_ListVO.getFood_img() == null)) {
					pic = food_ListVO.getFood_img();
					out.write(pic);
				} else {
					InputStream in = getServletContext().getResourceAsStream("/front-store-end/store/img/logo_top.png");
					byte[] letsEat = new byte[in.available()];
					in.read(letsEat);
					out.write(letsEat);
				}

			}

			catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
		}

//		========================更新餐點上架狀態========================
		if ("update_food_status".equals(action)) {
			String food_no = req.getParameter("food_no");
			System.out.println(food_no);
			Integer food_status = new Integer((1));
			Food_ListService foodlistSvc = new Food_ListService();
			foodlistSvc.updateStatus(food_no, food_status);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("food_no", food_no); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/store/food_check_my.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
	}
}
