package cn.edu.lingnan.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.lingnan.dto.Category;
import cn.edu.lingnan.service.CategoryService;
import cn.edu.lingnan.serviceimp.CategoryServiceImp;
import cn.edu.lingnan.web.base.BaseServlet;
import net.sf.json.JSONArray;


public class CategoryServlet extends BaseServlet {

	// findAllCats
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// 调用业务层获取全部分类
		CategoryService CategoryService = new CategoryServiceImp();
		List<Category> list = CategoryService.getAllCats();
		// 将全部分类转换为JSON格式的数据
		String jsonStr = JSONArray.fromObject(list).toString();
		// 将全部分类信息响应到客户端
		// 告诉浏览器本次响应的数据是JSON格式的字符串
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(jsonStr);

		return null;
	}
}
