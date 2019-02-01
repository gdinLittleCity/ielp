package cn.edu.gdin.ilep.show.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.stereotype.Service;

import cn.edu.gdin.ilep.manager.dao.NewsDao;
import cn.edu.gdin.ilep.manager.dao.ResourceDao;
import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.manager.domain.Resources;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.show.service.LuceneService;
import cn.edu.gdin.ilep.util.Constant;
import cn.edu.gdin.ilep.util.LuceneUtils;
import cn.edu.gdin.ilep.util.StringTransferUtils;

@Service("luceneService")
public class LuceneServiceImpl implements LuceneService{
	@Resource(name="newsDao")
	private NewsDao newsDao;
	
	@Resource(name="resourceDao")
	private ResourceDao resourceDao;
	
	@Override
	public void newsToDocument(News news) {
		IndexWriter writer = null;
		Document doc = null;
		try {
			writer = LuceneUtils.getNewsIndexWriter();
			doc = new Document();
			StringField nid = new StringField("nid", news.getNid(), Store.YES);
			TextField title = new TextField("title", news.getTitle(), Store.YES);
			TextField content = new TextField("contentString", news.getContentString(), Store.YES);
			doc.add(nid);
			doc.add(title);
			doc.add(content);
			writer.addDocument(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void resourceToDocument(Resources resources) {
		IndexWriter writer = null;
		Document doc = null;
		try {
			writer = LuceneUtils.getResourceIndexWriter();
			doc = new Document();
			
			StringField sid = new StringField("sid", resources.getSid(), Store.YES);
			TextField title = new TextField("title", resources.getTitle(), Store.YES);
			
			doc.add(sid);
			doc.add(title);
			writer.addDocument(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public News documentToNews(Document document) {
		News news = new News();
		
		news.setNid(document.get("nid"));
		news.setTitle(document.get("title"));
		news.setContentString(document.get("contentString"));
		
		return news;
	}

	@Override
	public Resources documentToResource(Document document) {
		
		Resources res = new Resources();
		
		res.setSid(document.get("sid"));
		res.setTitle(document.get("title"));
		
		return res;
	}

	@Override
	public PageBean<News>searchNews(String comdition,PageBean<News> pageBean) {
		IndexSearcher  search = null;
		List<News> list = new ArrayList<News>();
		try {
			search = LuceneUtils.getNewsIndexSearcher();
			String  fields[]={"title","contentString"};
			QueryParser queryParser=new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),fields,LuceneUtils.getIKAnalyzer());
			Query query=queryParser.parse(comdition);
			
			Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter("<font color='red'>", "</font>"), new QueryScorer(query));//设置高亮格式,高亮词
			highlighter.setTextFragmenter(new SimpleFragmenter(20));//设置摘要长度
			
			ScoreDoc scoreDocs[]=search.search(query,Constant.SEARCHER_TOPSIZE).scoreDocs;
			pageBean.setPageSize(Constant.SEARCHER_PAGESIZE);
			
			pageBean.setPageTotal(scoreDocs.length % pageBean.getPageSize() == 0 ? scoreDocs.length
					/ pageBean.getPageSize() : 1 + scoreDocs.length / pageBean.getPageSize());
			
			int length = Math.min(pageBean.getTotal()+Constant.SEARCHER_PAGESIZE,scoreDocs.length);
			for(int i=pageBean.getTotal();i<length;i++){
				News news = documentToNews(search.doc(scoreDocs[i].doc));
				String title = highlighter.getBestFragment(LuceneUtils.getIKAnalyzer(), "title", search.doc(scoreDocs[i].doc).get("title"));
				
				String content = highlighter.getBestFragment(LuceneUtils.getIKAnalyzer(), "contentString", search.doc(scoreDocs[i].doc).get("contentString"));
				
				if(title!=null)
					news.setTitle(title);
				news.setContentString(content);
				
				list.add(news);
			}
			//转义特殊字符
//			if(null!=list&&0!=list.size()){
//				for(News news:list){
//					news.setTitle(StringTransferUtils.string2html(news.getTitle()));
//				}
//			}
			pageBean.setBeanList(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pageBean;
	}

	@Override
	public PageBean<Resources> searchResource(String comdition,PageBean<Resources> pageBean){
		
		IndexSearcher  search = null;
		List<Resources> list = new ArrayList<Resources>();
		try {
			search = LuceneUtils.getResourceIndexSearcher();
			String  fields[]={"title"};
			
			QueryParser queryParser=new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),fields,LuceneUtils.getIKAnalyzer());
			Query query=queryParser.parse(comdition);
			
			Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter("<font color='red'>", "</font>"), new QueryScorer(query));
			highlighter.setTextFragmenter(new SimpleFragmenter(20));
			
			ScoreDoc scoreDocs[]=search.search(query,Constant.SEARCHER_TOPSIZE).scoreDocs;
			
			pageBean.setPageSize(Constant.SEARCHER_PAGESIZE);
			pageBean.setPageTotal(scoreDocs.length % pageBean.getPageSize() == 0 ? scoreDocs.length
					/ pageBean.getPageSize() : 1 + scoreDocs.length / pageBean.getPageSize());
			
			int length = Math.min(pageBean.getTotal()+Constant.SEARCHER_PAGESIZE,scoreDocs.length);
			for(int i=pageBean.getTotal();i<length;i++){
				Resources res = documentToResource(search.doc(scoreDocs[i].doc));
				String title = highlighter.getBestFragment(LuceneUtils.getIKAnalyzer(), "title", search.doc(scoreDocs[i].doc).get("title"));
				if(title!=null)
					res.setTitle(title);
				
				list.add(res);
			}
			//转义特殊字符
//			if(null!=list&&0!=list.size()){
//				for(Resources res:list){
//					res.setTitle(StringTransferUtils.string2html(res.getTitle()));
//				}
//			}
			pageBean.setBeanList(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pageBean;
	}

	@Override
	public void updateIndex() throws SQLException {
		List<News> list = newsDao.selectAll();
		for(News news : list){
			newsToDocument(news);
		}
		
		List<Resources> resList = resourceDao.selectAll();
		
		for(Resources res : resList){
			resourceToDocument(res);
		}
	}

	@Override
	public void updateNewsIndex(News news) throws IOException {
		IndexWriter indexWriter=LuceneUtils.getNewsIndexWriter();
		Term term=new Term("nid",news.getNid());
		Document doc= new Document();
		
		doc.add(new StringField("nid", news.getNid(), Store.YES));
		doc.add(new StringField("title", news.getTitle(), Store.YES));
		doc.add(new TextField("contentString", news.getContentString(),Store.YES));
		indexWriter.updateDocument(term, doc);
		
		indexWriter.close();
		
	}

	@Override
	public void deleteNewsIndex(String id) throws IOException{
		IndexWriter indexWriter=LuceneUtils.getNewsIndexWriter();
		
		Term term=new Term("nid", id);
		
		indexWriter.deleteDocuments(term);
		
		indexWriter.close();
		
	}

	@Override
	public void updateResourceIndex(Resources resources) throws IOException {
		IndexWriter indexWriter=LuceneUtils.getNewsIndexWriter();
		Term term=new Term("sid",resources.getSid());
		Document doc= new Document();
		
		doc.add(new StringField("sid", resources.getSid(), Store.YES));
		doc.add(new StringField("title", resources.getTitle(), Store.YES));
		indexWriter.updateDocument(term, doc);
		
		indexWriter.close();
		
	}

	@Override
	public void deleteResourceIndex(String id) throws IOException {
		Term term = new Term("sid", id);
		
		IndexWriter indexWriter=LuceneUtils.getResourceIndexWriter();
		
		indexWriter.deleteDocuments(term);
		indexWriter.close();
		
	}
	
}
