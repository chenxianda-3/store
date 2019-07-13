package cn.edu.lingnan.service;

import java.sql.SQLException;
import java.util.List;

import cn.edu.lingnan.dto.User;

public interface UserService {
	List<User> getAllUsers()throws Exception;

	void userRegist(User user)throws SQLException ;

	boolean userActive(String code)throws SQLException ;

	User userLogin(User user)throws SQLException;

	User findByName(String username)throws SQLException;
	
	void addUser(User user)throws Exception;
	
	void deleteUser(User user) throws Exception;
	
	User  findUserByUid(User user) throws Exception;
	
	void updateUser(User user) throws SQLException;
	
}
