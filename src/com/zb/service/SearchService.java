package com.zb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zb.mapper.ProductMapper;
import com.zb.pojo.Product;
import com.zb.utill.ElasticSearch;

@Service
public class SearchService {
	@Autowired
	ElasticSearch elasticSearch;
	@Autowired
	ProductMapper mapper;
	
	
	public List<Product> SearchByKey (int page, int size,String key) throws Exception{
		
		
		 List<String> id=elasticSearch.searchProduct(key);
		 System.out.println(id+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		 if(id.isEmpty())
			 return null;
		 
		 PageHelper.startPage(page, size);
		 List<Product> products=mapper.SearchByKey(id);
		 System.out.println(products+"++++++++++++++++++++++++++++++++");
		return products;
	}

}
