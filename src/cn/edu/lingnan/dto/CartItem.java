package cn.edu.lingnan.dto;

import java.util.Date;

public class CartItem {
	private String cartItemId;
	private int quantity;//当前类别商品数量
	private double total;//小计
	private Date addTime;
	private  User user;
	private Product product;//目的携带购物项3种参数(图片路径,商品名称,商品价格)
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}



	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getTotal() {
		return product.getShop_price()*quantity;
	}


	public void setTotal(double total) {
		this.total = total;
	}
	public String getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	
}
