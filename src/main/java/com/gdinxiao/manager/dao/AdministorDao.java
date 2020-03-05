package com.gdinxiao.manager.dao;

import com.gdinxiao.manager.domain.Administor;
import com.gdinxiao.page.PageBean;

import java.sql.SQLException;
import java.util.List;


public interface AdministorDao {
	/**
	 * 登录--通过用户名和密码查找
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	Administor selectByNameAndPassword(Administor administor) throws SQLException;
	/**
	 * 添加管理员
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	int insertAdministor(Administor administor)throws SQLException;
	/**
	 * 通过手机号码和用户名查找
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	Administor selectByPhone(Administor administor)throws SQLException;
	/**
	 * 通过ID查找管理员
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	Administor selectByID(Administor administor)throws SQLException;
	/**
	 * 修改管理员信息
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	int updateAdministor(Administor administor)throws SQLException;
	/**
	 * 删除管理员
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
	int deleteAdministor(String aid)throws SQLException;
	/**
	 * 查找该管理员名相应的数量
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	Administor selectByName(String name)throws SQLException;
	/**
	 * 获取管理员列表
	 * @param pageBean
	 * @return
	 */
	List<Administor> selectAll(PageBean<Administor> pageBean)throws SQLException;
	
	/**
	 * 获取管理员的数量
	 * @return
	 * @throws SQLException
	 */
	int selectAdministorCount()throws SQLException;
	
	/**
	 * 更新密码
	 * @param administor
	 * @return
	 */
	int updatePassword(Administor administor);
	
	/**
	 * 获取管理员信息--包含密码
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	Administor selectByIDWithPassword(Administor administor)throws SQLException;
	
	/**
	 * 更新个人信息--名称,号码
	 * @param administor
	 * @return
	 * @throws SQLException
	 */
	int updateUser(Administor administor)throws SQLException;
	
	
}
