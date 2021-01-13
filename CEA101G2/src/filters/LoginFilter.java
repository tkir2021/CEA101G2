package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginFilter implements Filter{
	
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object account = session.getAttribute("account");
		if (account == null) {
			//將請求的頁面存入location
			session.setAttribute("location", req.getRequestURI());
			//重導回登入頁面
			res.sendRedirect(req.getContextPath() + "/front-customer-end/member/memLogin.jsp");
			return;
		} else {
//			System.out.println("======================");
//			System.out.println(req.getRequestURI());
//			System.out.println("======================");
			chain.doFilter(request, response);
		}
	}
}
