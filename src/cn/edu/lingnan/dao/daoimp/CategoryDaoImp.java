package cn.edu.lingnan.dao.daoimp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.lingnan.dao.CategoryDao;
import cn.edu.lingnan.dto.Category;
import cn.edu.lingnan.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {

	@Override
	public List<Category> getAllCats() throws Exception {
		String sql="select * from category";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
		
	}

	@Override
	public void addCategory(Category c) throws Exception {
		String sql="insert into category values (? ,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,c.getCid(),c.getCname());
	}



	@Override
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
	}
	
	
	
}
