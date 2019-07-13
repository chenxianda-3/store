package cn.edu.lingnan.serviceimp;

import java.sql.SQLException;
import java.util.List;

import cn.edu.lingnan.dao.AdminDao;
import cn.edu.lingnan.dao.UserDao;
import cn.edu.lingnan.dto.Admin;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.service.AdminService;
import cn.edu.lingnan.service.UserService;
import cn.edu.lingnan.utils.BeanFactory;

public class AdminServiceImp implements AdminService {
	AdminDao adminDao=(AdminDao)BeanFactory.createObject("AdminDao");
	




	@Override
	public Admin adminLogin(Admin admin) throws SQLException {
		Admin adminLogin = adminDao.adminLogin(admin);
		if(adminLogin==null){
			throw new RuntimeException("密码有误!");
		}else{
			return adminLogin;
		}
	}
	
	


}
