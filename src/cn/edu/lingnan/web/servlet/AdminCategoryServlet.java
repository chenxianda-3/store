package cn.edu.lingnan.web.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dto.Category;
import cn.edu.lingnan.service.CategoryService;
import cn.edu.lingnan.serviceimp.CategoryServiceImp;
import cn.edu.lingnan.utils.JsonUtil;
import cn.edu.lingnan.utils.UUIDUtils;
import cn.edu.lingnan.web.base.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	// findAllCats
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 获取全部分类信息
		CategoryService CategoryService = new CategoryServiceImp();
		List<Category> list = CategoryService.getAllCats();
		String list2json = JsonUtil.list2json(list);
		resp.getWriter().println(list2json);
		return null;
	}



	// addCategory
	public void addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 获取分类名称
		String cname = req.getParameter("cname");
		// 创建分类ID
		String id = UUIDUtils.getId();
		Category c = new Category();
		c.setCid(id);
		c.setCname(cname);
		// 调用业务层添加分类功能
		CategoryService CategoryService = new CategoryServiceImp();
		try {
			CategoryService.addCategory(c);
			map.put("msg", "添加成功!");
		} catch (Exception e) {
			map.put("msg", "添加失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map);
		resp.getWriter().println(map2json);
	}

	public void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String cid = req.getParameter("cid");
		Category c = new Category();
		c.setCid(cid);
		CategoryService CategoryService = new CategoryServiceImp();
		try {
			CategoryService.deleteCategory(c);
			map.put("msg", "刪除成功!");
		} catch (Exception e) {
			map.put("msg", "刪除失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map);
		resp.getWriter().println(map2json);
	}

	public void findCategoryByCid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cid = req.getParameter("cid");
		Category c = new Category();
		c.setCid(cid);
		CategoryService CategoryService = new CategoryServiceImp();
		Category findCategoryByCid = CategoryService.findCategoryByCid(c);
		String object2json = JsonUtil.object2json(findCategoryByCid);
		resp.getWriter().println(object2json);
	}

	public void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 获取分类名称
		String cid = req.getParameter("cid");
		String cname = req.getParameter("cname");
		// 创建分类ID
		Category c = new Category();
		c.setCid(cid);
		c.setCname(cname);
		// 调用业务层添加分类功能
		CategoryService CategoryService = new CategoryServiceImp();
		try {
			CategoryService.updateCategory(c);
			map.put("msg", "修改成功!");
		} catch (Exception e) {
			map.put("msg", "修改失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map);
		resp.getWriter().println(map2json);
	}

}
