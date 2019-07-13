package cn.edu.lingnan.dao.daoimp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.lingnan.dao.ProductDao;
import cn.edu.lingnan.dto.Category;
import cn.edu.lingnan.dto.Product;
import cn.edu.lingnan.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {

	@Override
	public void saveProduct(Product product) throws Exception {
		String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
		qr.update(sql,params);
	}

	@Override
	public List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from product order by pdate desc limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
	}

	@Override
	public int findTotalRecords() throws Exception {
		String sql="select count(*) from product";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		String sql="select * from product where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	@Override
	public List<Product> findHots() throws Exception {
		String sql="SELECT * FROM product WHERE pflag=0 AND is_hot=1 ORDER BY pdate DESC LIMIT 0 ,9 ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findNews() throws Exception {
		String sql="SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0 , 9 ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
		
	}

	@Override
	public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		String sql="select * from product where cid=? limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
	}

	@Override
	public int findTotalRecords(String cid) throws Exception {
		String sql="select count(*) from product where cid =?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),cid);
		return num.intValue();
	}

	
/*	  `pid` VARCHAR(32) NOT NULL,
	  `pname` VARCHAR(50) DEFAULT NULL,		#商品名称
	  `market_price` DOUBLE DEFAULT NULL,	#市场价
	  `shop_price` DOUBLE DEFAULT NULL,		#商城价
	  `pimage` VARCHAR(200) DEFAULT NULL,	#商品图片路径
	  `pdate` DATE DEFAULT NULL,			#上架时间
	  `is_hot` INT(11) DEFAULT NULL,		#是否热门：0=不热门,1=热门
	  `pdesc` VARCHAR(255) DEFAULT NULL,	#商品描述
	  `pflag` INT(11) DEFAULT 0,			#商品标记：0=未下架(默认值),1=已经下架
	  `cid` VARCHAR(32) DEFAULT NULL,*/
	
	@Override
	public void updateProduct(Product product) throws Exception {
		String sql="UPDATE product SET pname=?,market_price=?, shop_price=?, pimage=?, pdate=?, is_hot=?, pdesc=?,pflag=?,cid=? WHERE pid=? ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),
				product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid(),product.getPid()};
		qr.update(sql,params);
	}

	@Override
	public void deleteProduct(Product product) throws Exception {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="delete from product where pid=?";
		qr.update(sql,product.getPid());
		
	}

	@Override
	public Product findProductByPid(Product product) throws Exception {
		String sql="select * from product where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<>(Product.class), product.getPid());
	}

	
	
/*	@Override
	public void updateCategory(Category c) throws Exception {
		String sql="UPDATE category SET cname=? WHERE  cid=? ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={c.getCname(),c.getCid()};
		qr.update(sql,params);
		
	}

	@Override
	public void deleteCategory(Category c) throws Exception {
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="delete from category where cid=?";
		qr.update(sql,c.getCid());
	}

	@Override
	public Category findCategoryByCid(Category c) throws Exception {
		String sql="select * from category where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<>(Category.class), c.getCid());
	}*/
	
	
	
}
