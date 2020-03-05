package com.gdinxiao.manager.dao;

import com.gdinxiao.manager.domain.Resources;
import com.gdinxiao.page.PageBean;
import com.gdinxiao.page.ShowPageBean;

import java.sql.SQLException;
import java.util.List;



public interface ResourceDao {
	/**
	 * 增加资源项
	 * @param resources
	 * @return
	 * @throws SQLException
	 */
	int insertResource(Resources resources) throws SQLException;
	/**
	 * 获取所有的资源信息
	 * @return
	 * @throws SQLException
	 */
	List<Resources> selectAllResource(PageBean<Resources> pageBean) throws SQLException;
	/**
	 * 获取资源记录数
	 * @return
	 * @throws SQLException
	 */
	int selectResourceCount()throws SQLException;
	
	/**
	 * 通过ID获取资源信息
	 * @param sid
	 * @return
	 * @throws SQLException
	 */
	List<Resources> selectResourceByID(String sid) throws SQLException;
	/**
	 * 通过标题获取资源信息
	 * @param title
	 * @return
	 * @throws SQLException
	 */
	List<Resources> selectResourceByTitle(String title) throws SQLException;
	/**
	 * 修改资源信息
	 * @param resources
	 * @return
	 * @throws SQLException
	 */
	int updateResource(Resources resources) throws SQLException;
	/**
	 * 删除资源信息
	 * @param sid
	 * @return
	 * @throws SQLException
	 */
	int deleteResourceByID(String sid) throws SQLException;
	/**
	 * 指定分类的资源列表--分页
	 * @param show
	 * @return
	 */
	List<Resources> selectListByCname(ShowPageBean<Resources> show);
	
	/**
	 * 指定分类的资源数量
	 * @param
	 * @return
	 */
	int selectCountByCname(String cname);
	
	/**
	 * 获取所有资源信息
	 * @return
	 */
	List<Resources> selectAll();
	
}
