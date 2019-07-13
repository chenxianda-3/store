package cn.edu.lingnan.service;

import java.util.List;

import cn.edu.lingnan.dto.PageModel;
import cn.edu.lingnan.dto.Product;


public interface ProductService {

	List<Product> findHots()throws Exception;

	List<Product> findNews()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception;

	PageModel findAllProductsWithPage(int curNum)throws Exception;

	void saveProduct(Product product)throws Exception;
	
	void updateProduct(Product product) throws Exception;
	
	void deleteProduct(Product product) throws Exception;
	
	Product  findProductByPid(Product product) throws Exception;

}
