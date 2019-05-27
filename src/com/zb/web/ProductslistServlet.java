package com.zb.web;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zb.pojo.Product;
import com.zb.utill.Page;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ProductslistServlet
 */
@WebServlet("/ProductslistServlet")
public class ProductslistServlet extends PageBaseBackServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductslistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		List<Product> products=productListService.list(page.getStart(),page.getCount(),page.getType());
		page.setTotal(productListService.getTotal(page.getType()));
		request.setAttribute("products", products);
		request.setAttribute("page", page);			
		
		return "user/allproductlist.jsp";
	}
	
	public String ajaxlist(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		List<Product> products=productListService.list(page.getStart(),page.getCount(),page.getType());
		page.setTotal(productListService.getTotal(page.getType()));
		JSONArray jsonArray=JSONArray.fromObject(products);
		logger.info(jsonArray.toString());	
		return "%"+jsonArray.toString();
		
	}

}
