package cn.edu.gdin.ilep.manager.dao;

import java.sql.SQLException;
import java.util.List;

import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.page.ShowPageBean;

public interface NewsDao {
	/**
	 * 增加文章
	 * @param news
	 * @return
	 * @throws SQLException
	 */
	 public int insert(News news) throws SQLException;
	 /**
	  * 获取所有文章--包含文章内容
	  * @return
	  * @throws SQLException
	  */
	 public List<News> selectAll()throws SQLException;
	 /**
	  * 通过文章ID 获取文章--包含编辑者信息
	  * @param nid
	  * @return
	  * @throws SQLException
	  */
	 public List<News> selectById(String nid) throws SQLException;
	 /**
	  * 更新文章标题,发布时间,分类,内容
	  * @param news
	  * @return
	  * @throws SQLException
	  */
	 public int updateNews(News news) throws SQLException;
	 /**
	  * 通过ID删除文章
	  * @param nid
	  * @return
	  * @throws SQLException
	  */
	 public int deleteNews(String nid) throws SQLException;
	 
	 /**
	  * 获取文章标题,分类信息--分页,包含基本信息(不包含文章内容,编辑者信息)
	  * @param pageBean
	  * @return
	  */
	public List<News> selectAllNews(PageBean<News> pageBean);
	
	/**
	 * 获取文章总数
	 * @return
	 */
	public int selectCount();
	
	/**
	 * 按分类查找--包含基本信息(不包含文章内容,编辑者信息)
	 * @param showCategory
	 * @return
	 */
	public List<News> selectByCname(String showCategory);
	/**
	 * 获取指定分类下的文章--分页,包含基本信息(不包含文章内容,编辑者信息)
	 * @param show
	 * @return
	 */
	public List<News> selectListByCname(ShowPageBean<News> show);
	/**
	 * 获取指定分类下的文章数量
	 * @param showCategory
	 * @return
	 */
	public int selectCountByCname(String showCategory);
	
	 
}
