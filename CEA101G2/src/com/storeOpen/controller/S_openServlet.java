package com.storeOpen.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.storeOpen.model.S_openService;
import com.storeOpen.model.S_openVO;

public class S_openServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;

 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doPost(request, response);
 }

 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  res.setCharacterEncoding("UTF-8");
  String action = req.getParameter("action");
  String url = "/front-store-end/store/listOneOpenHour.jsp";
  HttpSession session = req.getSession();
  String storeno = (String)session.getAttribute("store_no");
  System.out.println("instance"+storeno);
  
  // 輸入查詢帳密後查詢時段
  if ("getOne_For_Display".equals(action)) {
   List<String> errorMsgs = new LinkedList<String>();
   req.setAttribute("errorMsgs", errorMsgs);
   try {
    //String storeno1 = (String)session.getAttribute("store_no");
    System.out.println("getOneforDisplay"+storeno);
    S_openService openSvc = new S_openService();
    List<S_openVO> openlist = openSvc.getOneOpen(storeno);
    if (openlist == null) {
     errorMsgs.add("查無資料");
    }
    if (!errorMsgs.isEmpty()) {
     RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
     failureView.forward(req, res);
     return;
    }
    //模擬店家已登入狀態  
    req.setAttribute("openlist", openlist);
    
    RequestDispatcher success = req.getRequestDispatcher(url);
    success.forward(req, res);
    
   } catch (Exception e) {
    e.printStackTrace();
    errorMsgs.add("輸入失敗:" + e.getMessage());
    RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
    failureView.forward(req, res);
   }

  }

  // 刪除----------------------------------------------------------------------------------
  if ("delete".equals(action)) {
   List<String> errorMsgs = new LinkedList<String>();
   req.setAttribute("errorMsgs", errorMsgs);
   try {
    
    
    S_openService openSvc = new S_openService();
    
   System.out.println("delete"+storeno); 
    String timeperiod = req.getParameter("timeperiod");
    openSvc.deleteOpen(storeno, timeperiod);

    List<S_openVO> openlist = openSvc.getOneOpen(storeno);
    req.setAttribute("openlist", openlist);
    
    RequestDispatcher success = req.getRequestDispatcher(url);
    success.forward(req, res);
   } catch (Exception e) {
    errorMsgs.add("刪除資料失敗:" + e.getMessage());
    RequestDispatcher failureView = req
      .getRequestDispatcher("/front-store-end/store/listOneOpenHour.jsp");
    failureView.forward(req, res);
   }
  }

  // 新增--------------------------------------------------------------------------------------------
  if ("insert".equals(action)) {
   List<String> errorMsgs = new LinkedList<String>();
   req.setAttribute("errorMsgs", errorMsgs);

   try {
            
    StringBuilder timeperiod1 = new StringBuilder(req.getParameter("opentime")).append(" ~ ")
      .append(req.getParameter("closetime"));
    String timeperiod = timeperiod1.toString();

    System.out.println("insert"+storeno);
    S_openService openSvc = new S_openService();    
    openSvc.addOpen(storeno, timeperiod);
    
    List<S_openVO> openlist = openSvc.getOneOpen(storeno);
    req.setAttribute("openlist", openlist);
    
    RequestDispatcher success = req.getRequestDispatcher("/front-store-end/store/listOneOpenHour.jsp");
    success.forward(req, res);

   } catch (Exception e) {
    errorMsgs.add("時間不ok：" + e.getMessage());
    RequestDispatcher failureView = req
      .getRequestDispatcher("/front-store-end/store/listOneOpenHour.jsp");
    failureView.forward(req, res);
   }

  }

 }
}