package cn.edu.lingnan.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.serviceimp.UserServiceImp;
import cn.edu.lingnan.utils.CookUtils;

public class AutoLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 强转成HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 如果是登陆页面直接放行
		String servletPath = req.getServletPath();
		if (servletPath.startsWith("/UserServlet")) {
			String method = req.getParameter("method");
			if ("loginUI".equals(method)) {
				chain.doFilter(req, resp);
				return;
			}
		}
		// 用户登录信息
		User user = (User) req.getSession().getAttribute("loginUser");
		// 如果已经登录，放行，不需要再登录
		if (user != null) {
			chain.doFilter(req, resp);
			return;
		}
		Cookie usercookie = CookUtils.getCookieByName("autologin", req.getCookies());
		// 如果没有cookie不需要自动
		if (usercookie == null || usercookie.getValue()=="") {
			chain.doFilter(req, resp);
			return;
		}

		String[] split = usercookie.getValue().split("@");
		String username = split[0];
		String password = split[1];
		User user2 = new User();
		user2.setUsername(username);
		user2.setPassword(password);
		try {
			UserServiceImp userServiceImp = new UserServiceImp();
			User userLogin = userServiceImp.userLogin(user2);
			if (userLogin == null) {
				chain.doFilter(req, resp);
				return;
			}
			// 自动登录
			req.getSession().setAttribute("loginUser", userLogin);
			chain.doFilter(req, resp);
		} catch (Exception e) {
			System.out.println("aaa");
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
