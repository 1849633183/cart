package com.zb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zb.pojo.ProductImg;

public interface ProductImgMapper {
	
	 @Select(" select * from pimg where pmid = #{pid}")
	 public List<ProductImg> listbypid(String pid);

}
