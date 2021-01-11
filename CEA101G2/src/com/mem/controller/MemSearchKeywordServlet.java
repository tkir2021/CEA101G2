package com.mem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food_list.model.*;


public class MemSearchKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemSearchKeywordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String keyword = req.getParameter("keyword");
//		System.out.println(keyword);
		List<String> errorMsgs = null;

		if ("search".equals(action)) {
			if (keyword == "") {
				
				String url = "/front-customer-end/SearchResult/allRestaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}
			Food_ListService fdSvc = new Food_ListService();
			List<Food_ListVO> fdVO = fdSvc.searchKeyword(keyword);
			System.out.println(fdVO.toString());
//			System.out.println(keyword);
			
			
//			if(fdVO == null) {
//				errorMsgs.add("��鞈��");
//			}
			
			req.setAttribute("fdVO", fdVO);
			String url = "/front-customer-end/SearchResult/resultpage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

	}

}
