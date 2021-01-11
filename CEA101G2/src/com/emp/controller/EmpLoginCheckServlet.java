package com.emp.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.emp.model.EmpService;

@WebServlet("/EmpLoginCheckServlet")
public class EmpLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpLoginCheckServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession httpSession = req.getSession();
		String action = req.getParameter("action");
		String emp_no = req.getParameter("emp_no");
		String emp_pwd = req.getParameter("emp_pwd");
		
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			EmpService empSvc = new EmpService();
			
		
		try {
			if(emp_no.isEmpty() || emp_pwd.isEmpty()) {
				errorMsgs.add("帳號或密碼不得為空白");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/back_login.jsp");
				failureView.forward(req, res);
				return;
			}
			if(!empSvc.searchEmp(emp_no, emp_pwd)) {
				errorMsgs.add("帳號或密碼錯誤");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/back_login.jsp");
				failureView.forward(req, res);
				return;
			}
						
			httpSession.setAttribute("emp_no", emp_no); 
			String url = "/back-end/emp/back_emplist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
//			System.out.println();

		} catch (Exception e) {
			errorMsgs.add("無法核對" +e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/back_login.jsp");
			failureView.forward(req, res);

		}
		}
	}
}
