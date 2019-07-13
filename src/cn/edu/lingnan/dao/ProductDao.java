package cn.edu.lingnan.dao;

import java.util.List;

import cn.edu.lingnan.dto.Product;

public interface ProductDao {

	List<Product> findHots() throws Exception;

	List<Product> findNews() throws Exception;

	Product findProductByPid(String pid) throws Exception;

	int findTotalRecords(String cid) throws Exception;

	List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception;

	int findTotalRecords() throws Exception;

	List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception;

	void saveProduct(Product product) throws Exception;
	
	void updateProduct(Product product) throws Exception;
	
	void deleteProduct(Product product) throws Exception;
	
	public  Product  findProductByPid(Product product) throws Exception;

}
