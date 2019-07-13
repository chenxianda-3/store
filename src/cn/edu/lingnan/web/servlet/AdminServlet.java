package cn.edu.lingnan.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dto.Admin;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.service.AdminService;
import cn.edu.lingnan.service.UserService;
import cn.edu.lingnan.serviceimp.AdminServiceImp;
import cn.edu.lingnan.serviceimp.UserServiceImp;
import cn.edu.lingnan.utils.MyBeanUtils;
import cn.edu.lingnan.utils.UUIDUtils;
import cn.edu.lingnan.web.base.BaseServlet;


public class AdminServlet extends BaseServlet {


	
	//adminLogin
	public String adminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Admin admin = new Admin();
		String parameter = request.getParameter("username");
		System.out.println(parameter);
		MyBeanUtils.populate(admin, request.getParameterMap());
		try {
			AdminService adminService=new AdminServiceImp();
			Admin adminLogin = adminService.adminLogin(admin);
			request.getSession().setAttribute("loginAdmin", adminLogin);
			response.sendRedirect(request.getContextPath()+"/admin/home.jsp");
			System.out.println("aaa");
				return null;
		} catch (Exception e) {
			String msg=e.getMessage();
			request.setAttribute("msg", msg);
			return "/admin/index.jsp";
		}
		
	}
}
	

