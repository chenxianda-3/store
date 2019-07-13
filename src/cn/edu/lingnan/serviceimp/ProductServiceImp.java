package cn.edu.lingnan.serviceimp;

import java.util.List;

import cn.edu.lingnan.dao.ProductDao;
import cn.edu.lingnan.dto.PageModel;
import cn.edu.lingnan.dto.Product;
import cn.edu.lingnan.service.ProductService;
import cn.edu.lingnan.utils.BeanFactory;

public class ProductServiceImp implements ProductService {
	
	ProductDao ProductDao=(ProductDao)BeanFactory.createObject("ProductDao");

	@Override
	public void saveProduct(Product product) throws Exception {
		ProductDao.saveProduct(product);
		
	}

	
	@Override
	public Product findProductByPid(String pid) throws Exception {
		return ProductDao.findProductByPid(pid);
		
	}

	@Override
	public List<Product> findHots() throws Exception {
		return ProductDao.findHots();
	}

	@Override
	public List<Product> findNews() throws Exception {
		return ProductDao.findNews();
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {
		//1_创建PageModel对象 目的:计算分页参数
		//统计当前分类下商品个数  select count(*) from product where cid=?
		int totalRecords=ProductDao.findTotalRecords(cid);
		PageModel pm=new PageModel(curNum,totalRecords,12);
		//2_关联集合 select * from product where cid =? limit ? ,?
		List list=ProductDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	}

	@Override
	public PageModel findAllProductsWithPage(int curNum) throws Exception {
		//1_创建对象
		int totalRecords=ProductDao.findTotalRecords();
		PageModel pm=new PageModel(curNum,totalRecords,10);
		//2_关联集合 select * from product limit ? , ?
		List<Product> list=ProductDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pm;
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		ProductDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(Product product) throws Exception {
		ProductDao.deleteProduct(product);
	}

	@Override
	public Product findProductByPid(Product product) throws Exception {
		return ProductDao.findProductByPid(product);
	}

}
