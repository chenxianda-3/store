package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.dto.CartItem;

public interface CartItemService {
	public void addCartItemToCar(CartItem cartItem) throws Exception;
	public List<CartItem> getALLCartItemByUid(String uid) throws Exception;
	public void removeCartItem(String pid) throws Exception;
	public CartItem getCarItemByPid(String pid) throws Exception;
	public void updateCartItem(CartItem cartItem) throws Exception;
	public  void cleanCartItemByUid(String uid) throws Exception;
}
