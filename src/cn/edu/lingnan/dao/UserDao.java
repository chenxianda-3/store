package cn.edu.lingnan.dao;

import java.sql.SQLException;
import java.util.List;

import cn.edu.lingnan.dto.Category;
import cn.edu.lingnan.dto.User;


public interface UserDao {
	List<User> getAllUsers()throws Exception;

	void userRegist(User user) throws SQLException;

	User userActive(String code) throws SQLException;

	void updateUser(User user) throws SQLException;

	User userLogin(User user) throws SQLException;
	
	User findByName(String username) throws SQLException;
	
	void addUser(User user)throws Exception;
	
	void deleteUser(User user) throws Exception;
	
	User  findUserByUid(User user) throws Exception;

}
