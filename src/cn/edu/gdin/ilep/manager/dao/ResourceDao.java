package cn.edu.gdin.ilep.manager.dao;

import java.sql.SQLException;
import java.util.List;

import cn.edu.gdin.ilep.manager.domain.Resources;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.page.ShowPageBean;

public interface ResourceDao {
	/**
	 * 增加资源项
	 * @param resources
	 * @return
	 * @throws SQLException
	 */
	public int insertResource(Resources resources) throws SQLException;
	/**
	 * 获取所有的资源信息
	 * @return
	 * @throws SQLException
	 */
	public List<Resources> selectAllResource(PageBean<Resources> pageBean) throws SQLException;
	/**
	 * 获取资源记录数
	 * @return
	 * @throws SQLException
	 */
	public int selectResourceCount()throws SQLException;
	
	/**
	 * 通过ID获取资源信息
	 * @param sid
	 * @return
	 * @throws SQLException
	 */
	public  List<Resources> selectResourceByID(String sid) throws SQLException;
	/**
	 * 通过标题获取资源信息
	 * @param title
	 * @return
	 * @throws SQLException
	 */
	public  List<Resources> selectResourceByTitle(String title) throws SQLException;
	/**
	 * 修改资源信息
	 * @param resources
	 * @return
	 * @throws SQLException
	 */
	public  int updateResource(Resources resources) throws SQLException;
	/**
	 * 删除资源信息
	 * @param sid
	 * @return
	 * @throws SQLException
	 */
	public  int deleteResourceByID(String sid) throws SQLException;
	/**
	 * 指定分类的资源列表--分页
	 * @param show
	 * @return
	 */
	public List<Resources> selectListByCname(ShowPageBean<Resources> show);
	
	/**
	 * 指定分类的资源数量
	 * @param showCategory
	 * @return
	 */
	public int selectCountByCname(String cname);
	
	/**
	 * 获取所有资源信息
	 * @return
	 */
	public List<Resources> selectAll();
	
}
