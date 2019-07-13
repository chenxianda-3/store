package cn.edu.lingnan.service;

import java.sql.SQLException;
import java.util.List;

import cn.edu.lingnan.dto.Admin;
import cn.edu.lingnan.dto.User;

public interface AdminService {
	Admin adminLogin(Admin admin) throws SQLException;
	
}
