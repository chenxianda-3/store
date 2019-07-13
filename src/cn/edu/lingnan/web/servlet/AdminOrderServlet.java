package cn.edu.lingnan.web.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dto.Order;
import cn.edu.lingnan.service.OrderService;
import cn.edu.lingnan.serviceimp.OrderServiceImp;
import cn.edu.lingnan.utils.JsonUtil;
import cn.edu.lingnan.utils.MyBeanUtils;
import cn.edu.lingnan.web.base.BaseServlet;

public class AdminOrderServlet extends BaseServlet {

	
	public String findOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		OrderService OrderService=new OrderServiceImp();
		List<Order> list = OrderService.findAllOrders();
		String list2json = JsonUtil.list2json(list);
		resp.getWriter().println(list2json);
		return null;
	}
	
	//findOrderByOidWithAjax
	public void findOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String oid = req.getParameter("oid");
		Order order = new Order();
		order.setOid(oid);
		OrderService OrderService=new OrderServiceImp();
		Order findOrderByOid = OrderService.findOrderByOid(oid);
		String object2json = JsonUtil.object2json(findOrderByOid);
		resp.getWriter().println(object2json);
	}
	
	//updateOrderByOid
	public void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Order order = new Order();
		MyBeanUtils.populate(order, req.getParameterMap());
		OrderService OrderService=new OrderServiceImp();
		try {
			OrderService.updateOrder(order);
			map.put("msg", "修改成功!");
		} catch (Exception e) {
			map.put("msg", "修改失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map);
		resp.getWriter().println(map2json);
	}	
	
	public  void deleteOrder (HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String oid = req.getParameter("oid");
		Order order = new Order();
		order.setOid(oid);
		OrderService OrderService=new OrderServiceImp();
		try {
			OrderService.deleteOrder(order);
			map.put("msg", "刪除成功!");
		} catch (Exception e) {
			map.put("msg", "刪除失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map);
		resp.getWriter().println(map2json);
	}
	
}
