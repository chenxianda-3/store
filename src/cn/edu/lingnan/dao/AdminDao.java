package cn.edu.lingnan.dao;

import java.sql.SQLException;
import java.util.List;

import cn.edu.lingnan.dto.Admin;
import cn.edu.lingnan.dto.Category;
import cn.edu.lingnan.dto.User;


public interface AdminDao {


	Admin adminLogin(Admin admin) throws SQLException;
	


}
