//package com.foodlist.controller;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.foodlist.model.Food_ListService;
//
///**
// * Servlet implementation class Food_ListPhotoReader
// */
//@WebServlet("/Food_ListPhotoReader")
//public class Food_ListPhotoReader extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//    	res.setContentType("image/gif");
//    	  ServletOutputStream out = res.getOutputStream();
//    	  try {
//    		   String memPhone = req.getParameter("memPhone");
//    		   Food_ListService food_ListSvc = new Food_ListService();
//    		  // byte a1[]  = food_ListSvc.getOneMem(memPhone).getMemPhoto();
//    		   byte a1[]  = food_ListSvc.addFood_List(store_no, food_name, food_price, limit_, food_info, food_status)
//    		   out.write();
//    
//    
//    }
//
//	
//
//
//}
