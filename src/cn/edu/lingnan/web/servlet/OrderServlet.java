package cn.edu.lingnan.web.servlet;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dto.Order;
import cn.edu.lingnan.dto.PageModel;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.service.OrderService;
import cn.edu.lingnan.serviceimp.OrderServiceImp;
import cn.edu.lingnan.utils.JsonUtil;
import cn.edu.lingnan.web.base.BaseServlet;


public class OrderServlet extends BaseServlet {
	// saveOrder  将购物车中的信息以订单的形式保存
	public String saveOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//确认用户登录状态
		User user=(User)req.getSession().getAttribute("loginUser");
		if(null==user){
			req.setAttribute("msg", "请登录之后在下单");
			return "/jsp/info.jsp";
		}
		//创建订单对象,为订单对象赋值  CALL saveOrder("15@34@11@","373eb242933b4f5ca3bd43503c34668b");
		Order order=new Order();
		order.setUser(user);
		String[] pids = req.getParameterValues("checked");
		if(pids.length==0){
			return null;
		}
		//遍历购物车中选中的选项的pid，从购物车中取出来购物项，然后在放到订单中，同时去掉购物车中同样的product
		StringBuffer stringBuffer=new StringBuffer();
		for (int i=0;i<=pids.length-1;i++) {
			stringBuffer.append(pids[i]+"@");
		}
		//调用业务层功能:保存订单
	String string = stringBuffer.toString();
		OrderService OrderService=new OrderServiceImp();
		OrderService.saveOrder(string,order);
		//跳转到我的订单
		resp.sendRedirect(req.getContextPath()+"/OrderServlet?method=findMyOrdersWithPage&num=1");
		return null;
		/*return "OrderServlet?method=findMyOrdersWithPage&num=1";*/
	}
	
//findMyOrdersWithPage
	public String findMyOrdersWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取用户信息
		User user=(User)req.getSession().getAttribute("loginUser");
		//获取当前页
		int curNum=Integer.parseInt(req.getParameter("num"));
		//调用业务层功能:查询当前用户订单信息,返回PageModel
		OrderService OrderService=new OrderServiceImp();
		//SELECT * FROM orders WHERE uid=? limit ? , ? 
		//PageModel:1_分页参数 2_url  3_当前用户的当前页的订单(集合) ,每笔订单上对应的订单项,以及订单项对应的商品信息
		PageModel pm=OrderService.findMyOrdersWithPage(user,curNum);
		//将PageModel放入request
		req.setAttribute("page", pm);
		//转发到/jsp/order_list.jsp
		return "/jsp/order_list.jsp";
		
	}
	
	
	//findOrderByOid
	public String findOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取到订单oid
		String oid=req.getParameter("oid");
		//调用业务层功能:根据订单编号查询订单信息
		OrderService OrderService=new OrderServiceImp();
		Order order=OrderService.findOrderByOid(oid);
		// 将订单放入request
		req.setAttribute("order", order);
		// 转发到/jsp/order_info.jsp
		return "/jsp/order_info.jsp";
	}
	

	
	//payOrder
	public String payOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//确认用户登录状态
				User user=(User)req.getSession().getAttribute("loginUser");
				if(null==user){
					req.setAttribute("msg", "请登录之后在下单");
					return "/jsp/info.jsp";
				}
		//获取订单oid,收货人地址,姓名,电话,银行
		String oid=req.getParameter("oid");
		String address=req.getParameter("address");
		String name=req.getParameter("name");
		String telephone=req.getParameter("telephone");
		//更新订单上收货人的地址,姓名,电话
		OrderService OrderService=new OrderServiceImp();
		Order order=OrderService.findOrderByOid(oid);
		order.setState(2);
		order.setName(name);
		order.setTelephone(telephone);
		order.setAddress(address);
		OrderService.updateOrder(order);
		req.setAttribute("msg", "订单付款成功，马上给你发货");
		req.getRequestDispatcher("/jsp/info.jsp").forward(req, resp);
		return null;
	}
	
	public  void deleteOrder (HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String oid = req.getParameter("oid");
		Order order = new Order();
		order.setOid(oid);
		OrderService OrderService=new OrderServiceImp();
		OrderService.deleteOrder(order);
		resp.sendRedirect(req.getContextPath()+"/OrderServlet?method=findMyOrdersWithPage&num=1"); 
		
	}
	
}
