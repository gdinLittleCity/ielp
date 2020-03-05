package com.gdinxiao.show.service;

import com.gdinxiao.manager.domain.News;
import com.gdinxiao.manager.domain.Resources;
import com.gdinxiao.page.PageBean;
import org.apache.lucene.document.Document;

import java.io.IOException;
import java.sql.SQLException;

public interface LuceneService {
	/**
	 * 将文章转成document写入索引库
	 * @param news
	 */
	void newsToDocument(News news);
	/**
	 * 将资源转成document写入索引库
	 * @param resources
	 */
	void resourceToDocument(Resources resources);
	/**
	 * 将document转成News对象
	 * @param document
	 * @return
	 */
	News documentToNews(Document document);
	/**
	 * 将document转成Resources对象
	 * @param document
	 * @return
	 */
	Resources documentToResource(Document document);
	/**
	 * 搜索文章
	 * @param comdition
	 * @return
	 */
	PageBean<News> searchNews(String comdition, PageBean<News> pageBean);
	/**
	 * 搜索资源
	 * @param comdition
	 * @return
	 */
	PageBean<Resources> searchResource(String comdition, PageBean<Resources> pageBean);
	
	/**
	 * 更新索引--未使用
	 * @throws SQLException
	 */
	void updateIndex()throws SQLException;
	/**
	 * 更新文章索引
	 * @param news
	 * @throws IOException
	 */
	void updateNewsIndex(News news)throws IOException;
	/**
	 * 删除文章索引
	 * @param id
	 * @throws IOException
	 */
	void deleteNewsIndex(String id)throws IOException;
	/**
	 * 更新资源索引
	 * @param resources
	 * @throws IOException
	 */
	void updateResourceIndex(Resources resources)throws IOException;
	
	/**
	 * 删除资源索引
	 * @param id
	 * @throws IOException
	 */
	void deleteResourceIndex(String id)throws IOException;
	
	
}
