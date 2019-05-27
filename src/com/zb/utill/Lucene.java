package com.zb.utill;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.zb.mapper.ProductMapper;
import com.zb.pojo.Product;



@Component
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration(locations = {"classpath:applicationContext.xml"}) //加载配置文件  
public class Lucene {
	@Autowired
	ProductMapper productMapper;
	
	IKAnalyzer analyzer = new IKAnalyzer();
	Directory index;
	
	public Lucene() {			
				/*try {
				index = createIndex(analyzer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

	}

			
	@Test
	public  void test() throws Exception {
		
		// 1. 准备中文分词器
					IKAnalyzer analyzer = new IKAnalyzer();
					// 2. 索引
					Directory index = createIndex(analyzer);

		
		// 3. 查询器
		
//        //删除id=51173的数据
//        IndexWriterConfig config = new IndexWriterConfig(analyzer);
//        IndexWriter indexWriter = new IndexWriter(index, config);
//        indexWriter.deleteDocuments(new Term("id", "51173"));
//        indexWriter.commit();
//        indexWriter.close();
        
		// 更新索引
      /*  IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(index, config);
		Document doc = new Document();
		doc.add(new TextField("id", "51173", Field.Store.YES));
		doc.add(new TextField("name", "神鞭，鞭没了，神还在", Field.Store.YES));
		doc.add(new TextField("category", "道具", Field.Store.YES));
		doc.add(new TextField("price", "998", Field.Store.YES));
		doc.add(new TextField("place", "南海群岛", Field.Store.YES));
		doc.add(new TextField("code", "888888", Field.Store.YES));
		
		indexWriter.updateDocument(new Term("id", "51173"), doc );
		indexWriter.commit();
		indexWriter.close();*/
        

        Scanner s = new Scanner(System.in);
        while(true){
        	System.out.print("请输入查询关键字：");
            String keyword = s.nextLine();
            System.out.println("当前关键字是："+keyword);
    		Query query = new QueryParser( "content", analyzer).parse(keyword);

    		// 4. 搜索
    		IndexReader reader = DirectoryReader.open(index);
    		IndexSearcher searcher=new IndexSearcher(reader);
    		int numberPerPage = 10;
    		ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;
    		
    		for (ScoreDoc doc:hits){
                //取文档id
                int docId = doc.doc;
                //根据id取文档对象
                Document document = searcher.doc(docId);
                System.out.println(document.get("pid"));
                System.out.println(document.get("content"));
                
                System.out.println("-----------------寂寞的分割线");
            }
    		
    		// 5. 显示查询结果
    		/*showSearchResults(searcher, hits,query,analyzer);*/
    		// 6. 关闭查询
    		reader.close();
        }
        
        
		
		
		
		

	}
	
	public List<String> Lucence2Find(String keyword) {
		List<String> pid=new ArrayList<String>();
		Query query;
		IndexReader reader;
		try {
			query = new QueryParser( "content", analyzer).parse(keyword);
			reader = DirectoryReader.open(index);
		IndexSearcher searcher=new IndexSearcher(reader);
		int numberPerPage = 10;
		ScoreDoc[] hits = searcher.search(query, numberPerPage).scoreDocs;
		
		for (ScoreDoc doc:hits){
            //取文档id
            int docId = doc.doc;
            //根据id取文档对象
            Document document = searcher.doc(docId);
            pid.add(document.get("pid"));
            System.out.println(document.get("pid"));
            System.out.println(document.get("content"));
            
            System.out.println("-----------------寂寞的分割线");
        }
		
		
		// 关闭查询
		reader.close();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pid;
		
	}
	public void Lucence2Add() {
		
		
	}
    public void Lucence2Del() {
		
		
	}

	private  void showSearchResults(IndexSearcher searcher, ScoreDoc[] hits, Query query, IKAnalyzer analyzer) throws Exception {
		System.out.println("找到 " + hits.length + " 个命中.");

        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));

        
        System.out.println("找到 " + hits.length + " 个命中.");
        System.out.println("序号\t匹配度得分\t结果");
		for (int i = 0; i < hits.length; ++i) {
			ScoreDoc scoreDoc= hits[i];
			int docId = scoreDoc.doc;
			Document d = searcher.doc(docId);
			List<IndexableField> fields= d.getFields();
			System.out.print((i + 1) );
			System.out.print("\t" + scoreDoc.score);
			for (IndexableField f : fields) {

				
				
				if("name".equals(f.name())){
		            TokenStream tokenStream = analyzer.tokenStream(f.name(), new StringReader(d.get(f.name())));
		            String fieldContent = highlighter.getBestFragment(tokenStream, d.get(f.name()));
					System.out.print("\t"+fieldContent);
				}
				else{
					System.out.print("\t"+d.get(f.name()));
				}
			}
			System.out.println("<br>");
		}
	}

	private   Directory createIndex(IKAnalyzer analyzer) throws IOException {
		
		
		 //1、创建一个Director对象，指定索引库保存的位置。
        //把索引库保存在内存中
        Directory directory = new RAMDirectory();
        //把索引库保存在磁盘
        //Directory directory = FSDirectory.open(new File("C:\\temp\\index").toPath());
        //2、基于Directory对象创建一个IndexWriter对象
        IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
        IndexWriter writer = new IndexWriter(directory, config);
        		
		List<Product> products = productMapper.list();
		int total = products.size();
		int count = 0;
		int per = 0;
		int oldPer =0;
		for (Product p : products) {
			addDoc(writer, p);
			count++;
			per = count*100/total;
			if(per!=oldPer){
				oldPer = per;
				System.out.printf("索引中，总共要添加 %d 条记录，当前添加进度是： %d%% %n",total,per);
			}
			
		}
		writer.close();
		return directory;
	}

	private  void addDoc(IndexWriter w, Product p) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("pid", p.getPid(), Field.Store.YES));
		/*doc.add(new TextField("pname", p.getPname(), Field.Store.YES));
		doc.add(new TextField("ptype", p.getPtype(), Field.Store.YES));
		doc.add(new TextField("pnewprice", String.valueOf(p.getPnewprice()), Field.Store.YES));
		doc.add(new TextField("pstarlevel",  String.valueOf(p.getPstarlevel()), Field.Store.YES));
		doc.add(new TextField("pinfo", p.getPinfo(), Field.Store.YES));*/
		doc.add(new TextField("content", "pname:"+p.getPname()+",ptype:"+p.getPtype()+",pnewprice:"+String.valueOf(p.getPnewprice())+",pstarlevel:"+String.valueOf(p.getPstarlevel())+",pinfo:"+p.getPinfo(), Field.Store.YES));
		w.addDocument(doc);
	}
}
