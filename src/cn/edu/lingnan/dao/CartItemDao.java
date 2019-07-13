package cn.edu.lingnan.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import cn.edu.lingnan.dto.CartItem;

public interface CartItemDao {
	public void addCartItemToCar(CartItem cartItem) throws Exception;
	public List<CartItem> getALLCartItemByUid(String uid) throws Exception;
	public void removeCartItem(String pid) throws Exception;
	public CartItem getCarItemByPid(String pid) throws Exception;
	void updateCartItem(CartItem cartItem) throws Exception;
	public  void  removeAllCartItemByUid(String uid) throws Exception;
}
