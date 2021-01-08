package com.mem.controller;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mem.model.MailService;
import com.mem.model.Mem_DataService;
import com.mem.model.Mem_DataVO;

import javax.servlet.annotation.WebServlet;

@WebServlet
public class Mem_DataLoginForgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 【檢查使用者輸入的帳號(mem_acct) 信箱(mem_mail)是否有效】
	// 【實際上應至資料庫搜尋比對】
	Mem_DataService mem_dataSvc = new Mem_DataService();
	List<Mem_DataVO> mem_dataVO = mem_dataSvc.getAll();

//	protected boolean allowUser(String mem_acct, String mem_mail) {
//    if ("111".equals(mem_acct) && "222".equals(mem_mail))
//      return true;
//    else
//      return false;
//  }

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
//		String action = req.getParameter("action");
//		if ("sendMail".equals(action)) {
//			Mem_DataService mem_dataSvc = new Mem_DataService();
//			Mem_DataVO mem_dataVO = mem_dataSvc.getOneAcct(action);
			
//			// 【取得使用者 帳號(mem_acct) 信箱(mem_mail)】
//			String mem_acct = req.getParameter("mem_acct");
//			String mem_mail = req.getParameter("mem_mail");
			
//			
//			Mem_DataVO memDataVO = mem_dataSvc.getOneAcct(mem_acct);
//			memDataVO.getMem_pwd();
			
//			  String to = "alesayanson@gmail.com";
//		      
//		      String subject = "密碼通知";
//		      
//		      String ch_name = "peter1";
//		      String passRandom = "111";
//		      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)";
//		      MailService ms = new MailService();
//			  ms.sendMail(to, subject, messageText);
//		}
		// 【取得使用者 帳號(mem_acct) 信箱(mem_mail)】
					String mem_acct = req.getParameter("mem_acct");
					String mem_mail = req.getParameter("mem_mail");

		// 【檢查該帳號 , 信箱是否有效】
		for (Mem_DataVO a : mem_dataVO) {
			if (!(mem_acct.equals(a.getMem_acct()) && mem_mail.equals(a.getMem_mail()))) { // 【帳號 , 信箱無效時】
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>你的帳號 , 信箱輸入錯誤!<BR>");
				out.println("請按此重新驗證 <A HREF=" + req.getContextPath() + "/front-customer-end/member/forget.jsp>重新驗證</A>");
				out.println("</BODY></HTML>");
			} else { // 【帳號 , 信箱有效時, 才做以下工作】
				res.sendRedirect(req.getContextPath() + "/front-customer-end/member/memLogin.jsp");
				
				String action = req.getParameter("action");
				
				if ("sendMail".equals(action)) {
					Mem_DataService mem_dataSvc = new Mem_DataService();
					Mem_DataVO mem_dataVO2 = mem_dataSvc.getMemAcc(mem_acct);
					String mem_pwdm =mem_dataVO2.getMem_pwd();
					String mem_name =mem_dataVO2.getMem_name();
//					// 【取得使用者 帳號(mem_acct) 信箱(mem_mail)】
//					String mem_acct = req.getParameter("mem_acct");
//					String mem_mail = req.getParameter("mem_mail");
					
					 // 【帳號 , 信箱無效時】
					
						String to = mem_mail;
					      
					      String subject = "密碼通知";
					      
					      String ch_name = mem_name;
					      String passRandom = mem_pwdm;
					      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)";
					      MailService ms = new MailService();
						  ms.sendMail(to, subject, messageText);	
					}
//					
//					Mem_DataVO memDataVO = mem_dataSvc.getOneAcct(mem_acct);
//					memDataVO.getMem_pwd();
					
//					  String to = "alesayanson@gmail.com";
//				      
//				      String subject = "密碼通知";
//				      
//				      String ch_name = "peter1";
//				      String passRandom = "111";
//				      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)";
//				      MailService ms = new MailService();
//					  ms.sendMail(to, subject, messageText);
				}
				
				
//				HttpSession session = req.getSession();
//				session.setAttribute("mem_acct", mem_acct);
//				try {
//					String location = (String) session.getAttribute("location");
//					if (location != null) {
//						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
//						res.sendRedirect(location);
//						return;
					}
//				} catch (Exception ignored) {
//				}
//				System.out.println(req.getContextPath() + "/front-customer-end/member/listAllMem.jsp");
//				res.sendRedirect(req.getContextPath() + "/front-customer-end/member/listAllMem.jsp"); 
				// *工作3:
			    // (-->如無來源網頁:則重導至login_success.jsp)
				
				
			}
		}
	      
	
//}