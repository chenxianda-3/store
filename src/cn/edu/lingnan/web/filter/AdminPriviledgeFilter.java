package cn.edu.lingnan.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.edu.lingnan.dto.Admin;
import cn.edu.lingnan.dto.User;

public class AdminPriviledgeFilter implements Filter {

	public AdminPriviledgeFilter() {
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest myReq = (HttpServletRequest) request;
		// 判断当前的session中是否存在已经登录成功的用户
		Admin admin = (Admin) myReq.getSession().getAttribute("loginAdmin");
		if (null != admin) {
			// 如果存在,放行
			chain.doFilter(request, response);
		}else{
			
			// 如果不存在,转入到提示页面
			myReq.setAttribute("msg", "需要管理员权限，请先登录！");
			// 转入到提示页面
			myReq.getRequestDispatcher("/admin/index.jsp").forward(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
