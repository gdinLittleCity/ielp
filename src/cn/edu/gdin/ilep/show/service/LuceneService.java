package cn.edu.gdin.ilep.show.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.lucene.document.Document;

import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.manager.domain.Resources;
import cn.edu.gdin.ilep.page.PageBean;

public interface LuceneService {
	/**
	 * 将文章转成document写入索引库
	 * @param news
	 */
	public void newsToDocument(News news);
	/**
	 * 将资源转成document写入索引库
	 * @param resources
	 */
	public void resourceToDocument(Resources resources);
	/**
	 * 将document转成News对象
	 * @param document
	 * @return
	 */
	public News documentToNews(Document document);
	/**
	 * 将document转成Resources对象
	 * @param document
	 * @return
	 */
	public Resources documentToResource(Document document);
	/**
	 * 搜索文章
	 * @param comdition
	 * @return
	 */
	public PageBean<News>searchNews(String comdition,PageBean<News> pageBean); 
	/**
	 * 搜索资源
	 * @param comdition
	 * @return
	 */
	public PageBean<Resources> searchResource(String comdition,PageBean<Resources> pageBean);
	
	/**
	 * 更新索引--未使用
	 * @throws SQLException
	 */
	public void updateIndex()throws SQLException;
	/**
	 * 更新文章索引
	 * @param news
	 * @throws IOException
	 */
	public void updateNewsIndex(News news)throws IOException;
	/**
	 * 删除文章索引
	 * @param id
	 * @throws IOException
	 */
	public void deleteNewsIndex(String id)throws IOException;
	/**
	 * 更新资源索引
	 * @param resources
	 * @throws IOException
	 */
	public void updateResourceIndex(Resources resources)throws IOException;
	
	/**
	 * 删除资源索引
	 * @param id
	 * @throws IOException
	 */
	public void deleteResourceIndex(String id)throws IOException;
	
	
}
