package cn.edu.lingnan.serviceimp;

import java.util.List;

import cn.edu.lingnan.dao.CategoryDao;
import cn.edu.lingnan.dto.Category;
import cn.edu.lingnan.service.CategoryService;
import cn.edu.lingnan.utils.BeanFactory;


public class CategoryServiceImp implements CategoryService {

	

	CategoryDao CategoryDao=(CategoryDao)BeanFactory.createObject("CategoryDao");
	
	
	@Override
	public List<Category> getAllCats() throws Exception {
		return CategoryDao.getAllCats();
	}

	@Override
	public void addCategory(Category c) throws Exception {
		//本质是向MYSQL插入一条数据
		CategoryDao.addCategory(c);
	}
	@Override
	public void updateCategory(Category c) throws Exception {
		CategoryDao.updateCategory(c);
	}

	@Override
	public void deleteCategory(Category c) throws Exception {
		CategoryDao.deleteCategory(c);
	}

	@Override
	public Category findCategoryByCid(Category c) throws Exception {
		return CategoryDao.findCategoryByCid(c);
	}

}
