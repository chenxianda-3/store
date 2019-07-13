package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.dto.Order;
import cn.edu.lingnan.dto.PageModel;
import cn.edu.lingnan.dto.User;

public interface OrderService {

	void saveOrder(String pids,Order order)throws Exception;

	PageModel findMyOrdersWithPage(User user, int curNum)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

	List<Order> findAllOrders()throws Exception;

	List<Order> findAllOrders(String st)throws Exception; 
	
	void deleteOrder(Order order) throws Exception;

}
