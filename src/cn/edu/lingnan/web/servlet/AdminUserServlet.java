package cn.edu.lingnan.web.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dto.User;
import cn.edu.lingnan.service.UserService;
import cn.edu.lingnan.serviceimp.UserServiceImp;
import cn.edu.lingnan.utils.JsonUtil;
import cn.edu.lingnan.utils.MyBeanUtils;
import cn.edu.lingnan.utils.UUIDUtils;
import cn.edu.lingnan.web.base.BaseServlet;

public class AdminUserServlet extends BaseServlet {
	// findAllCats
	public String findAllUsers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		UserService userService = new UserServiceImp();
		List<User> list = userService.getAllUsers();
		String list2json = JsonUtil.list2json(list);
		resp.getWriter().println(list2json);
		return null;
	}



	// addCategory
	public void adduser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String[]> map2 = req.getParameterMap();
		User user = new User();
		MyBeanUtils.populate(user, map2);
		// 为用户的其他属性赋值
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		UserService userService = new UserServiceImp();
		try {
			userService.addUser(user);
			map.put("msg", "添加成功!");
		} catch (Exception e) {
			map.put("msg", "添加失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map);
		resp.getWriter().println(map2json);
	}

	public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String uid = req.getParameter("uid");
		User user = new User();
		user.setUid(uid);
		UserService userService = new UserServiceImp();
		try {
			userService.deleteUser(user);
			map.put("msg", "刪除成功!");
		} catch (Exception e) {
			map.put("msg", "刪除失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map);
		resp.getWriter().println(map2json);
	}

	public void findUserByUid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String uid = req.getParameter("uid");
		User user = new User();
		user.setUid(uid);
		UserService userService = new UserServiceImp();
		User findUserByUid = userService.findUserByUid(user);
		String object2json = JsonUtil.object2json(findUserByUid);
		resp.getWriter().println(object2json);
	}

	public void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		User user = new User();
		MyBeanUtils.populate(user, req.getParameterMap());
		UserService userService = new UserServiceImp();
		try {
			userService.updateUser(user);
			map.put("msg", "修改成功!");
		} catch (Exception e) {
			map.put("msg", "修改失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map);
		resp.getWriter().println(map2json);
	}

}
