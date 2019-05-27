package com.zb.utill;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.UtilLoggingLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zb.dao.ProductsDao;
import com.zb.mapper.ProductMapper;
import com.zb.mapper.UserMapper;
import com.zb.pojo.Userinfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import servlet.ProductListServlet;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration(locations = {"classpath:applicationContext.xml"}) //加载配置文件  
public class test {
	@Autowired
	ProductMapper productMapper;
	@Autowired
	UserMapper mapper;
	/*@Autowired
	Lucene lucene;*/
	 static Logger logger = Logger.getLogger(test.class);
	 
	 @Test
	 public void test1() {
		 try {
			 Userinfo userinfo=mapper.findByUsername("1");
			System.out.println(userinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	/*	 System.out.println(productMapper.list());*/
		/* System.out.println(lucene.Lucence2Find("手机"));*/
		
	}
	public static void main(String[] args) {
	
		
		/*// TODO Auto-generated method stub
		  //json合并 相同的字段会被后一个覆盖
		

        String json = "[{'name':'zhang san'},{'name':'uuu','age':'88'}]";
        JSONArray jsonObject = JSONArray.fromObject(json);


        JSONObject oldjson2=new JSONObject();
        String json2 = "[{'name':'zhang san'}]";
        JSONObject jsonObject2 = JSONObject.fromObject(json2);
        
      

       jsonObject.add(jsonObject2);

        System.out.println(jsonObject);
        String json2 = "[{'name':'dee san'},{'name':'fff','age':'8558'}]";
        JSONArray jsonObject3 = JSONArray.fromObject(json2);
        jsonObject.add(jsonObject3);
        System.out.println(jsonObject.size());
        
        String json4 = "{'name':'zhang san'}";
        JSONObject jsonObject4 = JSONObject.fromObject(json4);

        System.out.println(Util.joinJSONArray(jsonObject,jsonObject3));
        System.out.println(Util.isExistInJSONArray(jsonObject,jsonObject4));
	        for (int i = 0; i < 2; i++) {
	            logger.trace("跟踪信息");
	            logger.debug("调试信息");
	            logger.info("输出信息");
	            logger.warn("警告信息");
	            logger.error("错误信息");
	            logger.fatal("致命信息");
	        }*/
	
/*Scanner sc = new Scanner(System.in);
		String str;
		while(true) {
			str = sc.next();
			str = str.replace("吗？", "");
			str = str.replace("？", "！");
			str = str.replace("?", "!");
			System.out.println(str);
		}*/
		 String json = "[{'name':'zhang san','product':{'id':'1','dd':'dddd'}},{'name':'uuu','age':'88','product':{'id':'2','dd':'dddd'}}]";
	        JSONArray jsonObject = JSONArray.fromObject(json);
	      for(int i=0;i<jsonObject.size();i++)
	      {
	    	  if(((JSONObject) jsonObject.get(i)).getString("name")=="zhang san");
	    	  jsonObject.remove(i);
	      }
	        System.out.println(jsonObject);
	       
		JSONObject object=new JSONObject();  
		object.put("aaa",111);  
		object.put("bbb",222);  
		object.put("ccc",333);	
		object.put("ddd",444); 
		object.put("aaa",9999); 

		
		
		
	
	}

}
