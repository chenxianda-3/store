package cn.edu.lingnan.web.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.edu.lingnan.dto.PageModel;
import cn.edu.lingnan.dto.Product;
import cn.edu.lingnan.service.ProductService;
import cn.edu.lingnan.serviceimp.ProductServiceImp;
import cn.edu.lingnan.utils.JsonUtil;
import cn.edu.lingnan.utils.UUIDUtils;
import cn.edu.lingnan.utils.UploadUtils;
import cn.edu.lingnan.web.base.BaseServlet;

public class AdminProductServlet extends BaseServlet {
	int curpage = 1;

	// findAllProductsWithPage
	public void findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 调用业务层查全部商品信息返回PageModel

		ProductService ProductService = new ProductServiceImp();
		String page = req.getParameter("page");
		if (page != null) {
			curpage = Integer.parseInt(page);
		}
		PageModel pm = ProductService.findAllProductsWithPage(curpage);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pm.getTotalRecords());
		map.put("rows", pm.getList());
		String map2json = JsonUtil.map2json(map);
		resp.setContentType("text/html; charset=utf-8");
		resp.getWriter().println(map2json);
	}

	public void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String pid = req.getParameter("pid");
		Product product = new Product();
		product.setPid(pid);
		ProductService ProductService = new ProductServiceImp();
		try {
			ProductService.deleteProduct(product);
			map.put("msg", "刪除成功!");
		} catch (Exception e) {
			map.put("msg", "刪除失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map);
		resp.getWriter().println(map2json);
	}

	// addProduct
	public void addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List types = Arrays.asList("jpg", "png");
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Product product = new Product();
		try {
			map = uploadFileDeal(req, resp, types, map, map2);

			// 7_利用BeanUtils将MAP中的数据填充到Product对象上
			BeanUtils.populate(product, map);
			product.setPid(UUIDUtils.getId());
			product.setPdate(new Date());
			product.setPflag(0);

			// 8_调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
			ProductService ProductService = new ProductServiceImp();
			ProductService.saveProduct(product);
			map2.put("msg", "添加成功!");

		} catch (Exception e) {
			map2.put("msg", "添加失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map2);
		resp.getWriter().println(map2json);
	}

	private Map<String, String> uploadFileDeal(HttpServletRequest req, HttpServletResponse resp, List types, Map<String, String> map,
			Map<String, String> map2)
			throws FileUploadException, UnsupportedEncodingException, IOException, FileNotFoundException {
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		List<FileItem> list = upload.parseRequest(req);
		for (FileItem item : list) {
			if (item.isFormField()) {
				map.put(item.getFieldName(), item.getString("utf-8"));
			} else {
				String oldFileName = item.getName();
				String ext = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
				
				if (!types.contains(ext)) {
					map2.put("msg", "添加失败！本系统不支持" + ext + "这种类型");
					String map2json = JsonUtil.map2json(map2);
					resp.getWriter().println(map2json);
					return null;
				} else {

					// 获取到要保存文件的名称 1222.doc 123421342143214.doc
					String newFileName = UploadUtils.getUUIDName(oldFileName);
					InputStream is = item.getInputStream();
					String realPath = getServletContext().getRealPath("/products/3/");
					String dir = UploadUtils.getDir(newFileName); // /f/e/d/c/4/9/8/4
					String path = realPath + dir; // D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3/f/e/d/c/4/9/8/4
					// 内存中声明一个目录
					File newDir = new File(path);
					if (!newDir.exists()) {
						newDir.mkdirs();
					}
					// 在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
					File finalFile = new File(newDir, newFileName);
					if (!finalFile.exists()) {
						finalFile.createNewFile();
					}
					// 建立和空文件对应的输出流
					OutputStream os = new FileOutputStream(finalFile);
					// 将输入流中的数据刷到输出流中
					IOUtils.copy(is, os);
					// 释放资源
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(os);
					map.put("pimage", "/products/3/" + dir + "/" + newFileName);
				}
			}
		}
		return map;
	}

	
	public void findProductByPid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String pid = req.getParameter("pid");
		Product product = new Product();
		product.setPid(pid);
		ProductService ProductService = new ProductServiceImp();
		Product findProductByCid = ProductService.findProductByPid(product);
		String object2json = JsonUtil.object2json(findProductByCid);
		resp.getWriter().println(object2json);
	}

	public void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List types = Arrays.asList("jpg", "png");
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Product product = new Product();
		try {
			map = uploadFileDeal(req, resp, types, map, map2);

			// 7_利用BeanUtils将MAP中的数据填充到Product对象上
			BeanUtils.populate(product, map);
			product.setPdate(new Date());

			// 8_调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
			ProductService ProductService = new ProductServiceImp();
			ProductService.updateProduct(product);
			map2.put("msg", "修改成功!");

		} catch (Exception e) {
			map2.put("msg", "修改失败!");
			e.printStackTrace();
		}
		String map2json = JsonUtil.map2json(map2);
		resp.getWriter().println(map2json);

	}

}
