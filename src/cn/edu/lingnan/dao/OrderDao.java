package cn.edu.lingnan.dao;



import java.sql.Connection;
import java.util.List;

import cn.edu.lingnan.dto.Order;
import cn.edu.lingnan.dto.OrderItem;
import cn.edu.lingnan.dto.User;

public interface OrderDao {

	void saveOrder(String pids, Order order)throws Exception;


	int getTotalRecords(User user)throws Exception;

	List findMyOrdersWithPage(User user, int startIndex, int pageSize)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

	List<Order> findAllOrders()throws Exception;

	List<Order> findAllOrders(String st)throws Exception;
	
	void deleteOrder(Order order) throws Exception;

}
