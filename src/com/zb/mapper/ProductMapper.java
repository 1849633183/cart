package com.zb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.zb.pojo.Product;



public interface ProductMapper {
	  public int add(Product product);  
      
      
	    public void delete(int id);  
	       
	      
	    public Product get(int id);  
	     
	      
	    public int update(Product product);   
	    @Select("select * from products")   
	    @Results({
            @Result(id = true, property = "pid", column = "pid"),
            @Result(property = "pname", column = "pname"),
            @Result(property = "ptype", column = "ptype"),
            @Result(property = "poldprice", column = "poldprice"),
            @Result(property = "pnewprice", column = "pnewprice"),
            @Result(property = "pstarlevel", column = "pstarlevel"),
            @Result(property = "plabel", column = "plabel"),
            @Result(property = "pinfo", column = "pinfo"),
            @Result(property = "productImgs", javaType = List.class, column = "pid", many = @Many(select = "com.zb.mapper.ProductImgMapper.listbypid") )
    })
	    public List<Product> list();
	    
	    @Select(" select count(*) from products ") 
	    public int count();


		public List<Product> findAll(); 
		
		/*@Select("<script>" +
	            "select * " +
	            "form products " +
	            "where pid in " +
	            "<foreach collection='id' item='item' index='index' open='(' separator=',' close=')'>" +
	            "#{item}" +
	            "</foreach>" +
	            "</script>")*/
		 public List<Product> SearchByKey(List<String> id) throws Exception;

	    

}
