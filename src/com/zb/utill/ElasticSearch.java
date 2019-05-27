package com.zb.utill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zb.mapper.ProductMapper;
import com.zb.pojo.Product;

@Component
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration(locations = {"classpath:applicationContext.xml"}) //加载配置文件  
public class ElasticSearch {
	@Autowired
	ProductMapper productMapper;
	
	private  RestHighLevelClient client = new RestHighLevelClient(
	        RestClient.builder(
	                new HttpHost("localhost", 9200, "http")
	        ));
	private  String indexName = "zb";
	
    @Test
	public void  testA() { 
		
		
		String keyword = "p";
		int start = 0;
		int count = 10;
		
		SearchHits hits;
		try {
			hits = search(keyword, start, count);
		
		SearchHit[] searchHits = hits.getHits();
		for (SearchHit hit : searchHits) {
			System.out.println(hit.getId());
			System.out.println(hit.getSourceAsString());
		}
		
		client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public  List<String> searchProduct(String keyword)throws IOException{
		int start = 0;
		int count = 10;
		List<String> id=searchProductByPage(keyword, start, count);
		return id;
		
		
	}
   public  List<String> searchProductByPage(String keyword,int start,int count) throws IOException{
	   SearchHits hits = search(keyword, start, count);
		
		SearchHit[] searchHits = hits.getHits();
		List<String> id=new ArrayList<String>();
		for (SearchHit hit : searchHits) {
			System.out.println(hit.getId());
			System.out.println(hit.getSourceAsString());
			id.add(hit.getId());
		}
		
		/*client.close();*/
		return id;
		
	}

   public SearchHits search(String keyword, int start, int count) throws IOException {
		SearchRequest searchRequest = new SearchRequest(indexName); 
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		//关键字匹配
		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("content",keyword ); 
		//模糊匹配
		matchQueryBuilder.fuzziness(Fuzziness.AUTO);
		sourceBuilder.query(matchQueryBuilder); 
		//第几页
		sourceBuilder.from(start); 
		//第几条
		sourceBuilder.size(count); 

		
		searchRequest.source(sourceBuilder);
		//匹配度从高到低
		sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
		
		SearchResponse searchResponse = client.search(searchRequest);
		
		
		SearchHits hits = searchResponse.getHits();
		return hits;
	}

	//批量插入
	@Test
	public  void batchInsert() throws IOException {
		// TODO Auto-generated method stub
		/*BulkRequest request = new BulkRequest(); 
		
		for (Product product : products) {
			Map<String,Object> m  = product.toMap();
			IndexRequest indexRequest= new IndexRequest(indexName, "product", String.valueOf(product.getId())).source(m);
			request.add(indexRequest);
		}
		
		client.bulk(request);
		System.out.println("批量插入完成");*/
		List<Product> products = productMapper.list();
		BulkRequest request = new BulkRequest(); 
		for (Product p : products) {
			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("content", "pname:"+p.getPname()+",ptype:"+p.getPtype()+",pnewprice:"+String.valueOf(p.getPnewprice())+",pstarlevel:"+String.valueOf(p.getPstarlevel())+",pinfo:"+p.getPinfo());
			IndexRequest indexRequest= new IndexRequest(indexName, "product", p.getPid()).source(jsonMap);
			request.add(indexRequest);
		}
		client.bulk(request);
		System.out.println("批量插入完成");
	}

	public  void deleteDocument(String pid) throws IOException {
		DeleteRequest  deleteRequest = new DeleteRequest (indexName,"product", pid);
		client.delete(deleteRequest);
		System.out.println("已经从ElasticSearch服务器上删除id="+pid+"的文档");
	}

	
	public  void updateDocument(Product p) throws IOException {
	
		UpdateRequest  updateRequest = new UpdateRequest (indexName, "product", p.getPid())
				.doc("content","pname:"+p.getPname()+",ptype:"+p.getPtype()+",pnewprice:"+String.valueOf(p.getPnewprice())+",pstarlevel:"+String.valueOf(p.getPstarlevel())+",pinfo:"+p.getPinfo());
		        
		client.update(updateRequest);
		System.out.println("已经在ElasticSearch服务器修改产品为："+p);
		
	}

	private  void getDocument(String pid) throws IOException {
		// TODO Auto-generated method stub
		GetRequest request = new GetRequest(
		        indexName, 
		        "product",  
		        pid);
		
		GetResponse response = client.get(request);
		
		if(!response.isExists()){
			System.out.println("检查到服务器上 "+"pid="+pid+ "的文档不存在");
		}
		else{
			String source = response.getSourceAsString();
			System.out.print("获取到服务器上 "+"pid="+pid+ "的文档内容是：");

			System.out.println(source);
			
		}
		

		
		
	}

	private  void addDocument(Product p) throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("content", "pname:"+p.getPname()+",ptype:"+p.getPtype()+",pnewprice:"+String.valueOf(p.getPnewprice())+",pstarlevel:"+String.valueOf(p.getPstarlevel())+",pinfo:"+p.getPinfo());
		IndexRequest indexRequest = new IndexRequest(indexName, "product", p.getPid())
		        .source(jsonMap); 
		client.index(indexRequest);
		System.out.println("已经向ElasticSearch服务器增加产品："+p);
	}

	public  boolean checkExistIndex(String indexName) throws IOException {
		boolean result =true;
		try {

	        OpenIndexRequest openIndexRequest = new OpenIndexRequest(indexName);
	        client.indices().open(openIndexRequest).isAcknowledged();

	    } catch (ElasticsearchStatusException ex) {
	        String m = "Elasticsearch exception [type=index_not_found_exception, reason=no such index]";
	        if (m.equals(ex.getMessage())) {
	        	result = false;
	        }
	    }
		if(result)
			System.out.println("索引:" +indexName + " 是存在的");
		else
			System.out.println("索引:" +indexName + " 不存在");
		
		return result;
		
	}

	@Test
	public void deleteIndex() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest(indexName);
		client.indices().delete(request);
		System.out.println("删除了索引："+indexName);

		
	}
	
	@Test
	public void createIndex() throws IOException {
		// TODO Auto-generated method stub
		CreateIndexRequest request = new CreateIndexRequest(indexName);
		client.indices().create(request);
		System.out.println("创建了索引："+indexName);
	}
     
}
