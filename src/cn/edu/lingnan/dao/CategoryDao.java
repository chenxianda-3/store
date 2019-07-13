package cn.edu.lingnan.dao;

import java.util.List;

import cn.edu.lingnan.dto.Category;

public interface CategoryDao {

	List<Category> getAllCats()throws Exception;

	void addCategory(Category c)throws Exception;
	public  void updateCategory(Category c) throws Exception;
	public  void deleteCategory(Category c) throws Exception;
	public  Category  findCategoryByCid(Category c) throws Exception;
}
