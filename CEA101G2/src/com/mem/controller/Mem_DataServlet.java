package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mem.model.Mem_DataService;
import com.mem.model.Mem_DataVO;
import com.store.model.Food_ListService;
import com.store.model.Food_ListVO;

import mail.MailService;
import mail.MailVerify;

@MultipartConfig
public class Mem_DataServlet extends HttpServlet {

//	protected boolean allowUser(String account, String password) {
//
//		Mem_DataService mem_dataSvc = new Mem_DataService();
//		Mem_DataVO mem_dataVO = mem_dataSvc.getAccNoImg(account);
//
//		if (mem_dataVO != null) {
//			if (mem_dataVO.getMem_acct().equals(account) && mem_dataVO.getMem_pwd().equals(password))
//				return true;
//			else
//				return false;
//		} else
//			return false;
//	}
	
	
	protected String allowUser(String account, String password) {

		Mem_DataService mem_dataSvc = new Mem_DataService();
		Mem_DataVO mem_dataVO = mem_dataSvc.getAccNoImg(account);

		if (mem_dataVO != null) {
			if (mem_dataVO.getMem_acct().equals(account) && mem_dataVO.getMem_pwd().equals(password))
				if(mem_dataVO.getMem_auth() == 2)
					return "OK";
				else
					return "verifyNG";
			else
				return "NG";
		} else
			return "NG";
	}
	

	protected boolean allowAccount(String account) {

		Mem_DataService mem_dataSvc = new Mem_DataService();
		Mem_DataVO mem_dataVO = mem_dataSvc.getAccNoImg(account);

		if (mem_dataVO != null)
			return true;
		else
			return false;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");

		String action = req.getParameter("action");
		
		if ("loginCheck".equals(action)) {

			String account = req.getParameter("account");
			String password = req.getParameter("password");
			
			PrintWriter out = res.getWriter();

			
			String check = allowUser(account, password);
			
			if (check == "NG") { // 【帳號 , 密碼無效時】
				out.print("NG");
				out.flush();

			} else if(check == "verifyNG"){
				out.print("verifyNG");
				out.flush();
			}else { // 【帳號 , 密碼有效時, 才做以下工作】

				HttpSession session = req.getSession();
				session.setAttribute("account", account); // *工作1: 才在session內做已經登入過的標識

				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						System.out.println(location);
						res.sendRedirect(location);
						return;
					}
					// 若沒來源網頁，改成回到首頁
					res.sendRedirect(req.getContextPath() + "/front-customer-end/member/memUpdate.jsp"); // *工作3:// (-->如無來源網頁:則重導至login_success.jsp)
				} catch (Exception ignored) {
					
				} finally {
					if (out != null) {
						out.close();
					}
				}
				
			}
		}

		if ("accCheck".equals(action)) {
			String account = req.getParameter("account");

			
			if (allowAccount(account)) {
				res.setContentType("text; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.print("NG");
				out.flush();
				out.close();
			}

		}
		
		if("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			//改轉回首頁
			res.sendRedirect(req.getContextPath() + "/front-customer-end/member/index.jsp");
		}
		

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String mem_no = null;
				try {
					mem_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Mem_DataService mem_dataSvc = new Mem_DataService();
				Mem_DataVO mem_dataVO = mem_dataSvc.getOneMem(mem_no);
				if (mem_dataVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("mem_dataVO", mem_dataVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-customer-end/member/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mem_no = req.getParameter("mem_no");

				/*************************** 2.開始查詢資料 ****************************************/
				Mem_DataService mem_dataSvc = new Mem_DataService();
				Mem_DataVO mem_dataVO = mem_dataSvc.getOneMem(mem_no);

//				System.out.println(mem_dataVO.getMem_no());
//				System.out.println(mem_dataVO.getMem_grade());
//				System.out.println(mem_dataVO.getMem_acct());
//				System.out.println(mem_dataVO.getMem_pwd());
//				System.out.println(mem_dataVO.getMem_pwd());
//				System.out.println(mem_dataVO.getMen_phone());
//				System.out.println(mem_dataVO.getMem_mail());
//				System.out.println(mem_dataVO.getMem_img());
//				System.out.println(mem_dataVO.getMem_auth());
//				System.out.println(mem_dataVO.getReport_count());
//				System.out.println(mem_dataVO.getConsume_times());
//				System.out.println(mem_dataVO.getDeposit());

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("mem_dataVO", mem_dataVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-customer-end/member/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_mem_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_mem_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mem_no = req.getParameter("mem_no").trim();

				String mem_pwd = req.getParameter("mem_pwd");
				if (mem_pwd == null || mem_pwd.trim().length() == 0) {
					errorMsgs.add("會員密碼：請勿空白");
				}

				String mem_name = req.getParameter("mem_name");
				String mNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,6}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("會員姓名：請勿空白！");
				} else if (!mem_name.trim().matches(mNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到6之間");
				}

				String men_phone = req.getParameter("men_phone");
				String mPhoneReg = "^09[0-9]{8}$";
				if (!men_phone.trim().matches(mPhoneReg)) {
					
					errorMsgs.add(men_phone.trim()+"手機號碼：格式錯誤！");
				}

				try {
					Integer phone = new Integer(req.getParameter("men_phone").trim());

				} catch (NumberFormatException e) {
					men_phone = "";
					errorMsgs.add("手機號碼：請填寫正確格式！");
				}

				String mem_mail = req.getParameter("mem_mail");
				if (mem_mail == null || mem_mail.trim().length() == 0) {
					errorMsgs.add("E-MAIL：請勿空白");
				}

				byte[] mem_img = null;
				try {
					Part part = req.getPart("mem_img");
					if (part.getContentType() != null && part.getContentType().indexOf("image") >= 0) {
						InputStream in = req.getPart("mem_img").getInputStream();
						mem_img = new byte[in.available()];
						in.read(mem_img);
						in.close();
					} else {
						Mem_DataService mem_dataSvc = new Mem_DataService();
						Mem_DataVO mem_dataVO = mem_dataSvc.getOneMem(mem_no);
						mem_img = mem_dataVO.getMem_img();
					}
				} catch (Exception e) {
					errorMsgs.add("圖片上傳失敗！");
				}

				Mem_DataVO mem_dataVO = new Mem_DataVO();
				mem_dataVO.setMem_no(mem_no);
				mem_dataVO.setMem_pwd(mem_pwd);
				mem_dataVO.setMem_name(mem_name);
				mem_dataVO.setMen_phone(men_phone);
				mem_dataVO.setMem_mail(mem_mail);
				mem_dataVO.setMem_img(mem_img);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mem_dataVO", mem_dataVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/memUpdate.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Mem_DataService mem_dataSvc = new Mem_DataService();
				mem_dataVO = mem_dataSvc.updateMem(mem_no, mem_pwd, mem_name, men_phone, mem_mail, mem_img);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("mem_dataVO", mem_dataVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-customer-end/member/memUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/memUpdate.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自memLogin.jsp的請求
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text; charset=utf-8");
			

			
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

//				Integer mem_grade;
//				String men_phone;
//				Integer report_count;
//				Integer consume_times;
//				Integer deposit;
//				Integer mem_auth;
//				byte[] mem_img = null;
//
//				try {
//					mem_grade = new Integer(req.getParameter("mem_grade").trim());
//				} catch (NumberFormatException e) {
//					mem_grade = null;
//					errorMsgs.add("等級編號請填數字.");
//				}
//
//				String mem_acct = req.getParameter("mem_acct");
//				if (mem_acct == null || mem_acct.trim().length() == 0) {
//					errorMsgs.add("會員帳號: 請勿空白");
//				}
//
//				String mem_pwd = req.getParameter("mem_pwd");
//				if (mem_pwd == null || mem_pwd.trim().length() == 0) {
//					errorMsgs.add("會員密碼: 請勿空白");
//				}
//
//				String mem_name = req.getParameter("mem_name");
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("會員姓名: 請勿空白");
//				}
//
//				try {
//					men_phone = req.getParameter("men_phone").trim();
//				} catch (NumberFormatException e) {
//					men_phone = null;
//					errorMsgs.add("電話請填數字.");
//				}
//
//				String mem_mail = req.getParameter("mem_mail");
//				if (mem_mail == null || mem_mail.trim().length() == 0) {
//					errorMsgs.add("E-MAIL: 請勿空白");
//				}
//
//				try {
//					mem_auth = new Integer(req.getParameter("mem_auth").trim());
//				} catch (NumberFormatException e) {
//					mem_auth = null;
//					errorMsgs.add("使用權限狀態請填數字.");
//				}
//
//				try {
//					report_count = new Integer(req.getParameter("report_count").trim());
//				} catch (NumberFormatException e) {
//					report_count = null;
//					errorMsgs.add("累計被檢舉次數請填數字.");
//				}
//
//				try {
//					consume_times = new Integer(req.getParameter("consume_times").trim());
//				} catch (NumberFormatException e) {
//					consume_times = null;
//					errorMsgs.add("累計消費請填數字.");
//				}
//
//				try {
//					deposit = new Integer(req.getParameter("deposit").trim());
//				} catch (NumberFormatException e) {
//					deposit = null;
//					errorMsgs.add("儲值金額請填數字.");
//				}
//
//				try {
//					InputStream in = req.getPart("mem_img").getInputStream();
//					mem_img = new byte[in.available()];
//					in.read(mem_img);
//					in.close();
//				} catch (Exception e) {
//					mem_img = null;
//					errorMsgs.add("圖片上傳失敗.");
//				}

				Integer mem_grade = 1;
				String mem_acct = req.getParameter("mem_acct");
				String mem_pwd = req.getParameter("mem_pwd");
				String mem_name = req.getParameter("mem_name");
				String men_phone = req.getParameter("men_phone");
				String mem_mail = req.getParameter("mem_mail");
				byte[] mem_img = null;
				Integer mem_auth = 1; //1：未驗證  2：正常
				Integer report_count = 0;
				Integer consume_times = 0;
				Integer deposit = 0;
				
				
				//將使用者輸入的值存入VO
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

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("mem_dataVO", mem_dataVO); // 含有輸入格式錯誤的empVO物件，也存入req，讓錯誤返回時可以拿到已輸入過的值
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/memLogin.jsp");
//					failureView.forward(req, res);
//					return;
//				}

				/*************************** 2.開始新增資料 ***************************************/
				Mem_DataService empSvc = new Mem_DataService();
				mem_dataVO = empSvc.addMem(mem_grade, mem_acct, mem_pwd, mem_name, men_phone, mem_mail, mem_img,
						mem_auth, report_count, consume_times, deposit);

				/*************************** 2.5.寄送驗證mail ***************************************/
				MailService mail = new MailService(); //發送驗證郵件
				MailVerify verify = new MailVerify();
				String mailMsg = "點擊以下連結啟用帳號：http://localhost:8081/CEA101G2/member/member.do?action=verify&verifyCode=" + verify.insertCode(mem_acct) + "&mem_acct=" + mem_acct;
				mail.sendMail(mem_mail, "會員帳號啟用驗證", mailMsg);
				req.setAttribute("status","success");
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-customer-end/member/memLogin.jsp";
				HttpSession session = req.getSession();
				session.setAttribute("account", mem_dataVO.getMem_acct()); // *工作1: 才在session內做已經登入過的標識
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("status", "fail");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/memLogin.jsp");
				failureView.forward(req, res);
			} 
		}

		if ("delete".equals(action)) { // 來自listAllMem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String mem_no = req.getParameter("mem_no");

				/*************************** 2.開始刪除資料 ***************************************/
				Mem_DataService mem_dataSvc = new Mem_DataService();
				mem_dataSvc.deleteMem(mem_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-customer-end/member/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/member/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneImage".equals(action)) {

			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			try {
				String mem_no = req.getParameter("mem_no");
				Mem_DataService mem_DateService = new Mem_DataService();
				Mem_DataVO mem_DateVO = new Mem_DataVO();
				mem_DateVO = mem_DateService.getOneMem(mem_no);
				byte[] pic = null;

				if (!(mem_DateVO.getMem_img() == null)) {
					pic = mem_DateVO.getMem_img();
					out.write(pic);
					out.flush();
				} else {
					InputStream in = getServletContext().getResourceAsStream("/front-customer-end/member/img/logo_top.png");
					byte[] letsEat = new byte[in.available()];
					in.read(letsEat);
					out.write(letsEat);
					out.flush();
				}
			}

			catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
		}
		
		
		if ("verify".equals(action)) {
			String code = req.getParameter("verifyCode");
			String mem_acct = req.getParameter("mem_acct");
			MailVerify verify = new MailVerify();
			try {
				if (verify.verifyCode(mem_acct, code)) {
					Mem_DataService mem_dataSvc = new Mem_DataService();
					mem_dataSvc.updateStatus(mem_acct, 2);
				}
				res.sendRedirect(req.getContextPath() + "/front-customer-end/member/memLogin.jsp");
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		//----------------------------------20200107by Bella-------------------------------------------------
		  if ("updateAuth".equals(action)) { // 來自update_mem_input.jsp的請求

		   List<String> errorMsgs = new LinkedList<String>();
		   // Store this set in the request scope, in case we need to
		   // send the ErrorPage view.
		   req.setAttribute("errorMsgs", errorMsgs);

		   try {
		    /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		    String mem_no = req.getParameter("mem_no").trim();
		    Integer mem_auth = new Integer(req.getParameter("mem_auth"));
		    System.out.println(mem_no);
		    System.out.println(mem_auth);

		    Mem_DataVO mem_dataVO = new Mem_DataVO();
		    mem_dataVO.setMem_no(mem_no);    
		    mem_dataVO.setMem_auth(mem_auth);

		    /*************************** 2.開始修改資料 *****************************************/
		    Mem_DataService mem_dateSvc = new Mem_DataService();
		    mem_dataVO = mem_dateSvc.updateMemAuth(mem_no, mem_auth);

		    /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
		    req.setAttribute("mem_dataVO", mem_dataVO); // 資料庫update成功後,正確的的empVO物件,存入req
		    String url = "/back-end/member/mem_back.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
		    successView.forward(req, res);

		    /*************************** 其他可能的錯誤處理 *************************************/
		   } catch (Exception e) {
		    errorMsgs.add("修改資料失敗:" + e.getMessage());
		    RequestDispatcher failureView = req
		      .getRequestDispatcher("/back-end/member/mem_back.jsp");
		    failureView.forward(req, res);
		   }
		  }
		
		  
		

		//----------------------------------首頁搜尋 by mama-------------------------------------------------
//		if ("search".equals(action)) {
//			List<String> errorMsgs = null;
//			String keyword = req.getParameter("keyword");
//			if (keyword == "") {
//				
//				String url = "/front-customer-end/SearchResult/allRestaurant.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//				return;
//			}
//			Food_ListService fdSvc = new Food_ListService();
//			List<Food_ListVO> fdVO = fdSvc.searchKeyword(keyword); //找媽媽要資料
////			System.out.println(fdVO.toString());
//			System.out.println(keyword);
//			
//			
////			if(fdVO == null) {
////				errorMsgs.add("查無資料");
////			}
//			
//			req.setAttribute("fdVO", fdVO);
//			String url = "/front-customer-end/SearchResult/resultpage.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
////			System.out.println();
//		}
	}
}
