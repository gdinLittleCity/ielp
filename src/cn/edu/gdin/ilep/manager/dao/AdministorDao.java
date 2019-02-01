package cn.edu.gdin.ilep.manager.dao;

import java.sql.SQLException;
import java.util.List;

import cn.edu.gdin.ilep.manager.domain.Administor;
import cn.edu.gdin.ilep.page.PageBean;

public interface AdministorDao {
	/**
	 * 登录--通过用户名和密码查找
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	public Administor selectByNameAndPassword(Administor administor) throws SQLException;
	/**
	 * 添加管理员
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	public int insertAdministor(Administor administor)throws SQLException;
	/**
	 * 通过手机号码和用户名查找
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	public Administor selectByPhone(Administor administor)throws SQLException;
	/**
	 * 通过ID查找管理员
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	public Administor selectByID(Administor administor)throws SQLException;
	/**
	 * 修改管理员信息
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	public int updateAdministor(Administor administor)throws SQLException;
	/**
	 * 删除管理员
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	public int deleteAdministor(String aid)throws SQLException;
	/**
	 * 查找该管理员名相应的数量
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public Administor selectByName(String name)throws SQLException;
	/**
	 * 获取管理员列表
	 * @param pageBean
	 * @return
	 */
	public List<Administor> selectAll(PageBean<Administor> pageBean)throws SQLException;
	
	/**
	 * 获取管理员的数量
	 * @return
	 * @throws SQLException
	 */
	public int selectAdministorCount()throws SQLException;
	
	/**
	 * 更新密码
	 * @param administor
	 * @return
	 */
	public int updatePassword(Administor administor);
	
	/**
	 * 获取管理员信息--包含密码
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	public Administor selectByIDWithPassword(Administor administor)throws SQLException;
	
	/**
	 * 更新个人信息--名称,号码
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	public int updateUser(Administor administor)throws SQLException;
	
	
}
