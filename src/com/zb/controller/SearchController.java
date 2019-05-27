package com.zb.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zb.pojo.Product;
import com.zb.service.SearchService;
import com.zb.utill.ElasticSearch;

@Controller
@RequestMapping("")
public class SearchController{
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value="/products",method=RequestMethod.POST)
    public ModelAndView searchCategory(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, @RequestParam(value = "size", required = true, defaultValue = "4") Integer size,@RequestParam(value = "key", required = true, defaultValue = "pname")String key)throws Exception{
     
      ModelAndView mav = new ModelAndView();
      System.out.println(key+"ooooooooooooooooooo");
        /*List<Category> cs= categoryService.list(page);
        int total = categoryService.total();
         
        page.caculateLast(total);
               
        mav.addObject("cs", cs);*/
      List<Product> products= searchService.SearchByKey(page,size,key);
      PageInfo pageInfo = new PageInfo(products);
      mav.setViewName("search");
      if(null==products)
    	  return mav;
       mav.addObject("pageInfo", pageInfo);
       mav.addObject("key", key);
        return mav;
    }
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
    public ModelAndView listCategory(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, @RequestParam(value = "size", required = true, defaultValue = "4") Integer size,@RequestParam(value = "key", required = true, defaultValue = "pname")String key)throws Exception{
     
      ModelAndView mav = new ModelAndView();
      System.out.println(key+"ooooooooooooooooooo");    
      List<Product> products= searchService.SearchByKey(page,size,key);
      PageInfo pageInfo = new PageInfo(products);
      mav.setViewName("search");
      if(null==products)
    	  return mav;
       mav.addObject("pageInfo", pageInfo);
       mav.addObject("key", key);
        return mav;
    }

}
