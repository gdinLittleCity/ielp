package com.gdinxiao.manager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.gdinxiao.common.util.CommonUtils;
import com.gdinxiao.common.util.Constant;
import com.gdinxiao.common.util.StringTransferUtils;
import com.gdinxiao.manager.dao.CategoryDao;
import com.gdinxiao.manager.domain.Category;
import com.gdinxiao.manager.service.CategoryService;
import com.gdinxiao.page.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.google.gson.JsonObject;


@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource(name = "categoryDao")
    private CategoryDao categoryDao;

    @Override
    public List<Category> selectAllCategory() {
        List<Category> list = categoryDao.selectAllCategory();
        //转义特殊字符
        for (Category category : list)
            category.setDesc(StringTransferUtils.string2html(category.getDesc()));
        return list;
    }

    @Override
    public PageBean<Category> selectAll(PageBean<Category> pageBean) {
        List<Category> list = categoryDao.selectAll(pageBean);
        //转义特殊字符
        for (Category category : list)
            category.setDesc(StringTransferUtils.string2html(category.getDesc()));

        int total = categoryDao.selectCount() / pageBean.getPageSize() < 1 ? 1 : categoryDao.selectCount() / pageBean.getPageSize() + 1;

        pageBean.setPageTotal(total);
        if (null == list || 0 == list.size())
            pageBean.setBeanList(null);
        pageBean.setBeanList(list);
        return pageBean;
    }

    @Override
    public Category selectOneByCid(String cid) {
        List<Category> list = categoryDao.selectByCid(cid);

        if (null != list && 0 != list.size()) {
            Category category = list.get(0);
            //转义特殊字符
            category.setDesc(StringTransferUtils.string2html(category.getDesc()));
            return category;
        }

        return null;
    }

    @Override
    public List<Category> selectParentCategory() {
        List<Category> list = categoryDao.selectParentCategory();

        if (null != list && 0 != list.size()) {
            //转义特殊字符
            for (Category category : list)
                category.setDesc(StringTransferUtils.string2html(category.getDesc()));
            return list;
        }

        return null;
    }

    @Override
    public int updateCategory(Category category) {
        int count = categoryDao.updateCategory(category);
        if (count <= 0)
            return 0;
        return count;
    }

    @Override
    public int deleteCategory(String[] cid) {
        int count = 0;
        Category category = null;
        for (String id : cid) {
            //一级分类不能直接删除，需要先删除一级分类
            List<Category> list = categoryDao.selectByCidWithChild(id);

            if (null == list || 0 == list.size()) {
                category = new Category();
                category.setCid(id);
                if (categoryDao.deleteCategory(category) > 0)
                    count++;
                category = null;
                continue;
            }

            if (0 != list.get(0).getChild().size())
                return Constant.CATEGORY_DELETE_CHILD_NOT_NULL;

        }
        return count;
    }

    @Override
    public int saveParentCategory(Category category) {

        category.setCid(CommonUtils.uuid());
        int count = categoryDao.insertCategory(category);

        if (count <= 0)
            return 0;

        return count;
    }

    @Override
    public void saveHandle(Category category, BindingResult result,
                           JsonObject gson) {
        //分类字段检验后的错误信息处理
        if (result.hasErrors()) {
            //错误信息转换成json
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors)
                gson.addProperty(error.getField(), error.getDefaultMessage());
            return;
        }

        int count = saveParentCategory(category);
        //添加数据成功status -- 1
        //失败 status -- 0
        if (count > 0)
            gson.addProperty("status", Constant.SUCCESS);
        else
            gson.addProperty("status", Constant.FAIL);
    }

    @Override
    public List<Category> getResourceCategory() {
        List<Category> list = categoryDao.selectResourceCategory();
        if (null != list && 0 != list.size()) {
            //转义特殊字符
            for (Category category : list)
                category.setDesc(StringTransferUtils.string2html(category.getDesc()));
            return list;
        } else
            return null;
    }

    @Override
    public boolean ajaxCname(String cname) {

        Category category = categoryDao.selectByCname(cname);

        if (null != category)
            return false;
        else
            return true;
    }

    @Override
    public boolean ajaxUpdateCname(String cname, String cid) {

        Category category = categoryDao.selectByCname(cname);

        if (null == category)
            return true;
        if (null != category && category.getCid().equals(cid))
            return true;

        return false;
    }


}
