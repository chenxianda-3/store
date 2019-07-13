package cn.edu.lingnan.dao.daoimp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.lingnan.dao.AdminDao;
import cn.edu.lingnan.dao.UserDao;
import cn.edu.lingnan.dto.Admin;
import cn.edu.lingnan.dto.Category;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.utils.JDBCUtils;


public class AdminDaoImp implements AdminDao {


	@Override
	public Admin adminLogin(Admin admin) throws SQLException {
		String sql="select * from admin where username=?  and password= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),admin.getUsername(),admin.getPassword());
	}
	
	



}
