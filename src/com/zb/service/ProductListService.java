package com.zb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.zb.dao.ProductsDao;
import com.zb.mapper.ProductMapper;
import com.zb.pojo.Product;

import dao.ProductDAO;

public class ProductListService {
	List<Product> products;
	ProductsDao productsDao=new ProductsDao();
	 @Autowired
	 ProductMapper mapper;
	

	public List<Product> list(int index,int count, String type) {
		// TODO Auto-generated method stub
		products=productsDao.list(index,count, type);
		return products;
	}


	public int getTotal(String type) {
		// TODO Auto-generated method stub
		return productsDao.getTotal(type);
	}
	
	public List<Product> findAll(){
		
		return mapper.findAll();
		
	}

	
	

}
