package com.gdinxiao.manager.dao;

import com.gdinxiao.manager.domain.News;
import com.gdinxiao.page.PageBean;
import com.gdinxiao.page.ShowPageBean;

import java.sql.SQLException;
import java.util.List;


public interface NewsDao {
	/**
	 * 增加文章
	 * @param news
	 * @return
	 * @throws SQLException
	 */
	int insert(News news) throws SQLException;
	 /**
	  * 获取所有文章--包含文章内容
	  * @return
	  * @throws SQLException
	  */
	 List<News> selectAll()throws SQLException;
	 /**
	  * 通过文章ID 获取文章--包含编辑者信息
	  * @param nid
	  * @return
	  * @throws SQLException
	  */
	 List<News> selectById(String nid) throws SQLException;
	 /**
	  * 更新文章标题,发布时间,分类,内容
	  * @param news
	  * @return
	  * @throws SQLException
	  */
	 int updateNews(News news) throws SQLException;
	 /**
	  * 通过ID删除文章
	  * @param nid
	  * @return
	  * @throws SQLException
	  */
	 int deleteNews(String nid) throws SQLException;
	 
	 /**
	  * 获取文章标题,分类信息--分页,包含基本信息(不包含文章内容,编辑者信息)
	  * @param pageBean
	  * @return
	  */
	 List<News> selectAllNews(PageBean<News> pageBean);
	
	/**
	 * 获取文章总数
	 * @return
	 */
	int selectCount();
	
	/**
	 * 按分类查找--包含基本信息(不包含文章内容,编辑者信息)
	 * @param showCategory
	 * @return
	 */
	List<News> selectByCname(String showCategory);
	/**
	 * 获取指定分类下的文章--分页,包含基本信息(不包含文章内容,编辑者信息)
	 * @param show
	 * @return
	 */
	List<News> selectListByCname(ShowPageBean<News> show);
	/**
	 * 获取指定分类下的文章数量
	 * @param showCategory
	 * @return
	 */
	int selectCountByCname(String showCategory);
	
	 
}
