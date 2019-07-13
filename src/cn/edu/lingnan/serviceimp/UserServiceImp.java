package cn.edu.lingnan.serviceimp;

import java.sql.SQLException;
import java.util.List;

import cn.edu.lingnan.dao.UserDao;
import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.service.UserService;
import cn.edu.lingnan.utils.BeanFactory;

public class UserServiceImp implements UserService {
	UserDao UserDao=(UserDao)BeanFactory.createObject("UserDao");
	
	
	@Override
	public void userRegist(User user) throws SQLException {
		//实现注册功能
		
		UserDao.userRegist(user);
		
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		return UserDao.getAllUsers();
	}

	@Override
	public boolean userActive(String code) throws SQLException {
		//实现注册功能
		
		///对DB发送select * from user where code=?
		User user=UserDao.userActive(code);
		
		if(null!=user){
			//可以根据激活码查询到一个用户
			//修改用户的状态,清除激活码
			user.setState(1);
			user.setCode(null);
			//对数据库执行一次真实的更新操作  update user set state=1 , code=null where uid=?
			//update user set username=? , password=? ,name =? ,email=?, telephone =? ,birthday =? ,sex=? ,state=? ,code= ? where uid=?
			UserDao.updateUser(user);
			return  true;
		}else{
			//不可以根据激活码查询到一个用户
			return false;
		}
	}

	@Override
	public User userLogin(User user) throws SQLException {
		//此处:可以利用异常在模块之间传递数据
		//select * from user where username=? and password=?
		User uu=UserDao.userLogin(user);
		if(null==uu){
			throw new RuntimeException("密码有误!");
		}else if(uu.getState()==0){
			throw new RuntimeException("用户未激活!");
		}else{
			return uu;
		}
	}

	@Override
	public User findByName(String username) throws SQLException {
		return UserDao.findByName(username);
	}

	@Override
	public void addUser(User user) throws Exception {
		UserDao.addUser(user);
	}

	@Override
	public void deleteUser(User user) throws Exception {
		UserDao.deleteUser(user);
	}

	@Override
	public User findUserByUid(User user) throws Exception {

		return UserDao.findUserByUid(user);
	}

	@Override
	public void updateUser(User user) throws SQLException {

		UserDao.updateUser(user);
	}
	
	
	
	
	

}
