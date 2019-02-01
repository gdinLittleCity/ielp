package cn.edu.gdin.ilep.manager.dao;

import java.util.List;

import cn.edu.gdin.ilep.manager.domain.Category;
import cn.edu.gdin.ilep.page.PageBean;
/**
 * 
 * @author gdinxiao
 *
 */
public interface CategoryDao {
	/**
	 * 查询得到一级分类并将二级分类关联到一级分类
	 * @return
	 */
	public List<Category> selectAllCategory();
	/**
	 * 简单得到所有分类
	 * @return
	 */
	public List<Category> selectAll(PageBean pageBean);
	
	
	/**
	 * 获取分类总数
	 */
	public int selectCount();
	
	/**
	 * 获取单个分类的信息--通过分类ID
	 * @param cid
	 * @return
	 */
	public List<Category> selectByCid(String cid);
	/**
	 * 简单获取所有一级分类
	 * @return
	 */
	public List<Category> selectParentCategory();
	
	/**
	 * 修改分类信息
	 * @param category
	 * @return
	 */
	public int updateCategory(Category category);
	
	/**
	 * 增添分类
	 * @param category
	 * @return
	 */
	public int insertCategory(Category category);
	/**
	 * 删除分类信息
	 * @param category
	 * @return
	 */
	public int deleteCategory(Category category);
	/**
	 * 获取分类并关联该分类的下属分类
	 * @param cid
	 * @return
	 */
	public List<Category> selectByCidWithChild(String cid);
	/**
	 * 获取资源分类
	 * @return
	 */
	public List<Category> selectResourceCategory();
	/**
	 * 通过分类名获取简单分类信息
	 * @param cname
	 * @return
	 */
	public Category selectByCname(String cname);
	
}
