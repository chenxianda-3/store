package cn.edu.lingnan.dao.daoimp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.edu.lingnan.dao.CartItemDao;
import cn.edu.lingnan.dto.CartItem;
import cn.edu.lingnan.dto.Order;
import cn.edu.lingnan.dto.Product;
import cn.edu.lingnan.utils.JDBCUtils;

public class CartItemDaoImp implements CartItemDao {
/*	   `cartitemid` VARCHAR(32) NOT NULL,
  `quantity` INT(11) DEFAULT NULL,			#购买数量
  `total` DOUBLE DEFAULT NULL,	
  `addtime` DATETIME DEFAULT NULL,		#小计
  `pid` VARCHAR(32) DEFAULT NULL,		#购买商品的id
  `uid` VARCHAR(32) DEFAULT NULL,		#订单项所在订单id
  */

	@Override
	public void addCartItemToCar(CartItem cartItem) throws Exception {
		String sql="INSERT INTO cartitem VALUES(?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={cartItem.getCartItemId(),cartItem.getQuantity(),cartItem.getTotal(),cartItem.getAddTime(),cartItem.getProduct().getPid(),cartItem.getUser().getUid()};
		qr.update(sql, params);
		
	}

	@Override
	public List<CartItem> getALLCartItemByUid(String uid) throws Exception {
		String sql="select * from cartitem c,product p where c.pid=p.pid and uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		 List<Map<String, Object>> query = qr.query(sql,new MapListHandler() , uid);
		 List<CartItem> list=new ArrayList<>();
		 for (Map<String, Object> map : query) {
			CartItem cartItem = new CartItem();
			Product product = new Product();
			DateConverter dt = new DateConverter();
			// 2_设置转换的格式
			dt.setPattern("yyyy-MM-dd HH:mm:ss");
			// 3_注册转换器
			ConvertUtils.register(dt, java.util.Date.class);
			BeanUtils.populate(cartItem, map);
			BeanUtils.populate(product, map);
			cartItem.setProduct(product);
			list.add(cartItem);
		}
			return list;
	}

	@Override
	public void removeCartItem(String pid)  throws Exception{
		String sql="delete from cartitem where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,pid);
		
	}

	@Override
	public CartItem getCarItemByPid(String pid)  throws Exception{
		String sql="select * from cartitem where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<CartItem>(CartItem.class),pid);
	}
/*	
	  `cartitemid` VARCHAR(32) NOT NULL,
	  `quantity` INT(11) DEFAULT NULL,			#购买数量
	  `total` DOUBLE DEFAULT NULL,			#小计
	  `pid` VARCHAR(32) DEFAULT NULL,		#购买商品的id
	  `uid` VARCHAR(32) DEFAULT NULL,	*/
	@Override
	public void updateCartItem(CartItem cartItem) throws Exception {
		String sql="UPDATE cartitem SET quantity=? ,total= ? WHERE  pid=? ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={cartItem.getQuantity(),cartItem.getTotal(),cartItem.getProduct().getPid()};
		qr.update(sql,params);
		
	}

	@Override
	public void removeAllCartItemByUid(String uid) throws Exception {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="delete from cartitem where uid=?";
		qr.update(sql,uid);
		
	}

	
}
