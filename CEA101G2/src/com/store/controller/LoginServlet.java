package com.store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.store.model.LoginBean;
import com.store.model.LoginDao;
import com.store.model.Store_MemService;
import com.store.model.Store_MemVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns={"/store/login.do"}
//			initParams= {
//					@WebInitParam(name="SUCCESS_PATH", value="request.getContextPath()+/front-store-end/store/select_page.jsp" ),
//					@WebInitParam(name="FORM_PATH", value="/CEA101G2/front-store-end/store/empty.jsp" )
//			}
		)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
//		request.getRequestDispatcher(getInitParameter("FORM_PATH"))
//		.forward(request, response) ;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDao loginDao = new LoginDao();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		
//		Store_MemService storemem = new Store_MemService();
//		   List<Store_MemVO> storeVO = new ArrayList<>();
//		   storeVO=storemem.getAll().stream()
//		     .filter(d -> d.getStore_acct().equals(username))
//		     .collect(Collectors.toList());
//		   String store_no = storeVO.get(0).getStore_no();
		
		
		String store_no = null;
		try {
			Store_MemService storemem = new Store_MemService();
			   List<Store_MemVO> storeVO = new ArrayList<>();
			   storeVO=storemem.getAll().stream()
			     .filter(d -> d.getStore_acct().equals(username))
			     .collect(Collectors.toList());
			   store_no = storeVO.get(0).getStore_no();
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			response.sendRedirect(request.getContextPath()+"/front-store-end/store/error.jsp");
		}
		
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		System.out.println("Username"+username+"Password"+password);
		HttpSession session = request.getSession();
		
		if (loginDao.validate(loginBean)){
			System.out.println("login");
		    session.setAttribute("store_no", store_no);
		    request.setAttribute("action1", "getOne_For_Store");
		    request.setAttribute("action3", "getALL");
		    String url = "/store/store.do";
		    RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/store/store.do");//放修改資料
//			  try {      
//			         String location = (String) session.getAttribute("location");
//			         System.out.println("location"+location);
//			         if (location != null) {
//			           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
//			           response.sendRedirect(location);            
//			           return;
//			         }
//			         
//			       }catch (Exception ignored) {
//			    	   }			  
		}else{
			System.out.println("logout");
			//HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher logout = request.getRequestDispatcher(request.getContextPath()+"/index/index.jsp");			
		}
	}
}
