package com.shopping.controller;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.shopping.controller.Food;

public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		@SuppressWarnings("unchecked")
		List<Food> buylist = (Vector<Food>) session.getAttribute("shoppingcart");
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
				Food abook = getFood(req);

				if (buylist == null) {
					buylist = new Vector<Food>();
					buylist.add(abook);
				} else {
					if (buylist.contains(abook)) {
						Food innerFood = buylist.get(buylist.indexOf(abook));
						innerFood.setQuantity(innerFood.getQuantity() + abook.getQuantity());
					} else {
						buylist.add(abook);
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/front-customer-end/shopping/EShop.jsp?store_no=SM00000001";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 結帳，計算購物車書籍價錢總數
		else if (action.equals("CHECKOUT")) {
			double total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				Food order = buylist.get(i);
				Integer price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/front-customer-end/shopping/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private Food getFood(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String name = req.getParameter("name");
		String store_no = req.getParameter("store_no");
		String food_no = req.getParameter("food_no");
		String price = req.getParameter("price");

		Food book = new Food();
		
		book.setStore_no(store_no);
		book.setFood_no(food_no);
		book.setName(name);
		book.setPrice(new Integer(price));
		book.setQuantity((new Integer(quantity)).intValue());
		return book;
	}
}