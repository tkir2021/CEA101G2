package com.shopping.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.Mem_DataService;
import com.mem.model.Mem_DataVO;
import com.orderdetail.model.OrderDetailVO;
import com.ordermaster.model.OrderMasterVO;

public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		@SuppressWarnings("unchecked")
		List<Food> buylist = (Vector<Food>) session.getAttribute("shoppingcart");
		String store_no = (String)session.getAttribute("store_no");
		
		String action = req.getParameter("action");
		

		if (!action.equals("CHECKOUT")) {
			
			// 刪除購物車中的書籍
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
			}
			// 新增書籍至購物車中
			else if (action.equals("ADD")) {
				// 取得後來新增的書籍
				Food afood = getFood(req);

				if (buylist == null) {
					buylist = new Vector<Food>();
					buylist.add(afood);
				} else {
					if (buylist.contains(afood)) {
						Food innerFood = buylist.get(buylist.indexOf(afood));
						innerFood.setQuantity(innerFood.getQuantity() + afood.getQuantity());
					} else {
						buylist.add(afood);
					}
				}
			}
			
//			session.setAttribute("store_no", store_no);
			
			session.setAttribute("shoppingcart", buylist);
			String url = "/front-customer-end/shopping/EShop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 結帳，計算購物車書籍價錢總數
		else if (action.equals("CHECKOUT")) {
			Integer total = 0;
			List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
			OrderDetailVO orderDetailVO = null;
			
			
			for (int i = 0; i < buylist.size(); i++) {
				orderDetailVO = new OrderDetailVO();
				Food order = buylist.get(i);
				Integer price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
				
				orderDetailVO.setFood_no(order.getFood_no());
				orderDetailVO.setFood_scale("無");
				orderDetailVO.setFood_price(order.getPrice());
				orderDetailVO.setQuantity(order.getQuantity());
				orderDetailVO.setTotal(price * quantity);
				
				list.add(orderDetailVO);
								
			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			
			/*********************購物車：insterData********************/
			//處理Mem_DataVO
			String mem_acct = (String)session.getAttribute("account");
			Mem_DataVO mem_dataVO = new Mem_DataService().getMemAcc(mem_acct);
			
			//處理OrderMasterVO
			//時間轉換
			Date dNow = new Date();
		    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		    java.sql.Date sDate = java.sql.Date.valueOf(ft.format(dNow));
		    
			OrderMasterVO orderMasterVO = new OrderMasterVO();
			orderMasterVO.setMem_no(mem_dataVO.getMem_no());
			orderMasterVO.setStore_no(store_no);
			orderMasterVO.setOrder_date(sDate);
			orderMasterVO.setPay_type(0);
			orderMasterVO.setOrder_total(total);
			orderMasterVO.setSale_percent(1.0f);
			orderMasterVO.setDiscount(1.0f);
			orderMasterVO.setOrder_status("1");
			orderMasterVO.setTake_status("1");
			orderMasterVO.setGive_star(0f);
			
			insterData(total, mem_dataVO, orderMasterVO, list, req);
			session.setAttribute("shoppingcheckout", session.getAttribute("shoppingcart"));
			session.removeAttribute("shoppingcart");
			/*********************購物車：insterData********************/
			
			String url = "/front-customer-end/shopping/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}
	}

	private Food getFood(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String name = req.getParameter("name");
//		String store_no = req.getParameter("store_no");
		String food_no = req.getParameter("food_no");
		String price = req.getParameter("price");

		Food book = new Food();
		
//		book.setStore_no(store_no);
		book.setFood_no(food_no);
		book.setName(name);
		book.setPrice(new Integer(price));
		book.setQuantity((new Integer(quantity)).intValue());
		return book;
	}
	
	
	
	private void insterData(Integer total, Mem_DataVO mem_dataVO, OrderMasterVO orderMasterVO, List<OrderDetailVO> list, HttpServletRequest req) {
		
		Integer cost = mem_dataVO.getDeposit() - total;
		Integer consume = mem_dataVO.getConsume_times() + total;
		
		//餘額不足
		if(cost < 0 ) {
			req.setAttribute("check", "fail");
		}
		else {
			//更新：會員儲值金、訂餐主檔、訂餐明細
			mem_dataVO.setDeposit(cost);
			mem_dataVO.setConsume_times(consume);
			Mem_DataService mem_dataSvc = new Mem_DataService();
			mem_dataSvc.updateDeposit_ByShopping(mem_dataVO, orderMasterVO, list);
			
			req.setAttribute("check", "success");
		}
		
			
	}
	
}