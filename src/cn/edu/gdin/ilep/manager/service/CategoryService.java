package cn.edu.gdin.ilep.manager.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.google.gson.JsonObject;

import cn.edu.gdin.ilep.manager.domain.Category;
import cn.edu.gdin.ilep.page.PageBean;

public interface CategoryService {
	/**
	 * 获取所有一级分类并将二级分类关联到一级分类
	 * @return
	 */
	public  List<Category> selectAllCategory();
	
	/**
	 * 简单获取所有分类 --分页
	 * @param pageBean
	 * @return
	 */
	public  PageBean<Category> selectAll(PageBean<Category> pageBean);

	/**
	 * 获取单个分类的信息
	 * @param cid
	 * @return
	 */
	public Category selectOneByCid(String cid);

	/**
	 * 获取所有一级分类
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
	 * 删除分类
	 * @param cid
	 * @return
	 */
	public int deleteCategory(String[] cid);
	/**
	 * 添加分类信息
	 * @param category
	 * @return
	 */
	public int saveParentCategory(Category category);
	
	public void saveHandle(Category category, BindingResult result,
			JsonObject gson);
	/**
	 * 获取资源分类
	 * @return
	 */
	public List<Category> getResourceCategory();
	/**
	 * 校验分类名是否可用
	 * @param cname
	 * @return 可用--不重复--true
	 * 不可用--重复--false
	 */
	public boolean ajaxCname(String cname);
	/**
	 * 修改分类信息,校验分类名是否可用
	 * @param cname
	 * @return 可用--不重复--true
	 * 不可用--重复--false
	 */
	public boolean ajaxUpdateCname(String cname,String cid);
}
