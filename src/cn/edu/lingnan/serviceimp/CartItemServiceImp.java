package cn.edu.lingnan.serviceimp;

import java.util.List;

import cn.edu.lingnan.dao.CartItemDao;
import cn.edu.lingnan.dto.CartItem;
import cn.edu.lingnan.service.CartItemService;
import cn.edu.lingnan.utils.BeanFactory;

public class CartItemServiceImp implements CartItemService  {
	CartItemDao CartItemDao=(CartItemDao)BeanFactory.createObject("CartItemDao");
	@Override
	public void addCartItemToCar(CartItem cartItem) throws Exception {
		CartItem cartItem2 = CartItemDao.getCarItemByPid(cartItem.getProduct().getPid());
		if (cartItem2 != null) {
			cartItem2.setQuantity(cartItem.getQuantity()+cartItem2.getQuantity());
			cartItem2.setProduct(cartItem.getProduct());
			CartItemDao.updateCartItem(cartItem2);
		}else{
			
			CartItemDao.addCartItemToCar(cartItem);
		}
		
	}

	@Override
	public List<CartItem> getALLCartItemByUid(String uid)  throws Exception{
		// TODO 自动生成的方法存根
		return CartItemDao.getALLCartItemByUid(uid);
		
	}

	@Override
	public void removeCartItem(String pid) throws Exception {
		// TODO 自动生成的方法存根
		CartItemDao.removeCartItem(pid);
	}

	@Override
	public void cleanCartItemByUid(String uid) throws Exception {
		CartItemDao.removeAllCartItemByUid(uid);
		
	}

	@Override
	public CartItem getCarItemByPid(String pid)  throws Exception{
		// TODO 自动生成的方法存根
		return CartItemDao.getCarItemByPid(pid);
	}

	@Override
	public void updateCartItem(CartItem cartItem) throws Exception {
		CartItemDao.updateCartItem(cartItem);
	}

	
}
