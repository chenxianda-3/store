package cn.edu.lingnan.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dto.CartItem;
import cn.edu.lingnan.dto.Product;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.service.CartItemService;
import cn.edu.lingnan.service.ProductService;
import cn.edu.lingnan.serviceimp.CartItemServiceImp;
import cn.edu.lingnan.serviceimp.ProductServiceImp;
import cn.edu.lingnan.utils.UUIDUtils;
import cn.edu.lingnan.web.base.BaseServlet;

public class CartServlet extends BaseServlet {

	// 添加购物项到购物车
	public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String jsonData = "{\"flag\":false}";

		User user = (User) req.getSession().getAttribute("loginUser");
		// 确认用户登录状态
		if (null == user) {
			resp.getWriter().print(jsonData);
		} else {

			String pid = req.getParameter("pid");
			int num = Integer.parseInt(req.getParameter("quantity"));
			// 通过商品id查询都商品对象
			ProductService ProductService = new ProductServiceImp();
			Product product = ProductService.findProductByPid(pid);
			// 获取到待购买的购物项
			CartItem cartItem = new CartItem();
			cartItem.setCartItemId(UUIDUtils.getId());
			cartItem.setQuantity(num);
			// 添加当前时间
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addTime = simpleDateFormat.format(new Date());
			cartItem.setAddTime(simpleDateFormat.parse(addTime));
			cartItem.setProduct(product);
			cartItem.setUser(user);
			// 调用购物车上的方法,将商品添加到购物车表中
			CartItemService cartItemService = new CartItemServiceImp();
			try {
				cartItemService.addCartItemToCar(cartItem);
				jsonData = "{\"flag\":true}";
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} finally {
				resp.getWriter().print(jsonData);
			}
		}

		return null;
	}
	
	public String getALLCartItemByUid(HttpServletRequest req, HttpServletResponse resp)  {
		User user=(User)req.getSession().getAttribute("loginUser");
		if(null==user){
			req.setAttribute("msg", "请登录之后再添加商品到购物车");
			return "/jsp/info.jsp";
		}
		CartItemService cartItemService = new CartItemServiceImp();
		try {
			List<CartItem> cart = cartItemService.getALLCartItemByUid(user.getUid());
			req.setAttribute("cart",cart );
			return "/jsp/cart.jsp";
			
		} catch (Exception e) {
			req.setAttribute("msg","服务器忙，请稍后重试" );
			return "/jsp/info.jsp";
		}
	}
	
	
	public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String pid = req.getParameter("id");
		CartItemService cartItemService=new CartItemServiceImp();
		cartItemService.removeCartItem(pid);
		resp.sendRedirect(req.getContextPath()+"/CartServlet?method=getALLCartItemByUid"); 
		
		return null;
	}
	
	public String updateCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String jsonData = "{\"flag\":false}";
		String pid = req.getParameter("pid");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(quantity);
		ProductService ProductService = new ProductServiceImp();
		CartItemService cartItemService=new CartItemServiceImp();
		try {
			Product product = ProductService.findProductByPid(pid);
			cartItem.setProduct(product);
			System.out.println(cartItem.toString());
			cartItemService.updateCartItem(cartItem);
			jsonData = "{\"flag\":true}";
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			resp.getWriter().print(jsonData);
		}
		
		return null;
	}
	
	public String cleanCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		User user=(User)req.getSession().getAttribute("loginUser");
		if(null==user){
			req.setAttribute("msg", "请登录之后再操作！");
			return "/jsp/info.jsp";
		}
		CartItemService cartItemService = new CartItemServiceImp();
		cartItemService.cleanCartItemByUid(user.getUid());
		resp.sendRedirect(req.getContextPath()+"/CartServlet?method=getALLCartItemByUid"); 
		return null;
	}

	
}
