package filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.Filter;

public class storeLoginFilter implements Filter {
	
	private FilterConfig config;
	
    public void init(FilterConfig config) {
        this.config = config;
    }
    public void destroy() {
    	config=null;
    }
    
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req =(HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
	//判斷是否已登入過
		HttpSession session = req.getSession();
		String username =(String)session.getAttribute("username");
		System.out.println(username);
	//如果尚未登入，則記住來源頁並導向登入	
		if(username==null) {
			session.setAttribute("location", req.getRequestURI()+"?"+req.getQueryString());
			res.sendRedirect(req.getContextPath()+"/front-store-end/store/store_Login.jsp");
		}else {
			chain.doFilter(request, response);
		}
		
	}

}
