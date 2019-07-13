package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.dto.Category;

public interface CategoryService {

	List<Category> getAllCats()throws Exception;

	void addCategory(Category c)throws Exception;
	public  void updateCategory(Category c) throws Exception;
	public  void deleteCategory(Category c) throws Exception;
	public  Category  findCategoryByCid(Category c) throws Exception;

}
