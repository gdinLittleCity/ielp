package com.gdinxiao.manager.service;

import java.util.List;

import com.gdinxiao.manager.domain.Category;
import com.gdinxiao.page.PageBean;
import org.springframework.validation.BindingResult;

import com.google.gson.JsonObject;


public interface CategoryService {
    /**
     * 获取所有一级分类并将二级分类关联到一级分类
     *
     * @return
     */
    List<Category> selectAllCategory();

    /**
     * 简单获取所有分类 --分页
     *
     * @param pageBean
     * @return
     */
    PageBean<Category> selectAll(PageBean<Category> pageBean);

    /**
     * 获取单个分类的信息
     *
     * @param cid
     * @return
     */
    Category selectOneByCid(String cid);

    /**
     * 获取所有一级分类
     *
     * @return
     */
    List<Category> selectParentCategory();

    /**
     * 修改分类信息
     *
     * @param category
     * @return
     */
    int updateCategory(Category category);

    /**
     * 删除分类
     *
     * @param cid
     * @return
     */
    int deleteCategory(String[] cid);

    /**
     * 添加分类信息
     *
     * @param category
     * @return
     */
    int saveParentCategory(Category category);

    void saveHandle(Category category, BindingResult result,
                    JsonObject gson);

    /**
     * 获取资源分类
     *
     * @return
     */
    List<Category> getResourceCategory();

    /**
     * 校验分类名是否可用
     *
     * @param cname
     * @return 可用--不重复--true
     * 不可用--重复--false
     */
    boolean ajaxCname(String cname);

    /**
     * 修改分类信息,校验分类名是否可用
     *
     * @param cname
     * @return 可用--不重复--true
     * 不可用--重复--false
     */
    boolean ajaxUpdateCname(String cname, String cid);
}
