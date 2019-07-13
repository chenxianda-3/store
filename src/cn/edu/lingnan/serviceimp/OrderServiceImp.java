package cn.edu.lingnan.serviceimp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.edu.lingnan.dao.OrderDao;
import cn.edu.lingnan.dao.daoimp.OrderDaoImp;
import cn.edu.lingnan.dto.Order;
import cn.edu.lingnan.dto.OrderItem;
import cn.edu.lingnan.dto.PageModel;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.service.OrderService;
import cn.edu.lingnan.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {
	
	OrderDao orderDao=new OrderDaoImp();

	@Override
	public List<Order> findAllOrders() throws Exception {
		return orderDao.findAllOrders();
	}

	@Override
	public List<Order> findAllOrders(String st) throws Exception {
		return orderDao.findAllOrders(st);
	}

	@Override
	public void saveOrder(String pids,Order order) throws Exception {
			orderDao.saveOrder(pids,order);
	}

	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
		//1_创建PageModel对象,目的:计算并且携带分页参数
		//select count(*) from orders where uid=?
		int totalRecords=orderDao.getTotalRecords(user);
		PageModel pm=new PageModel(curNum, totalRecords, 3);
		//2_关联集合  select * from orders where uid=? limit ? ,?
		List list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		return orderDao.findOrderByOid(oid);
		
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		orderDao.updateOrder(order);
		
	}

	@Override
	public void deleteOrder(Order order) throws Exception {
		orderDao.deleteOrder(order);
	}
	
	
	
}