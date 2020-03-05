package com.gdinxiao.manager.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdinxiao.common.util.Constant;
import com.gdinxiao.manager.domain.Category;
import com.gdinxiao.manager.service.CategoryService;
import com.gdinxiao.page.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;




@Controller("categoryAction")
@RequestMapping("/admin/category")
public class CategoryAction {
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	/**
	 * 分类列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAllCategory")
	public String getAllCategory(Model model){
		
		List<Category> list = categoryService.selectAllCategory();
		
		if(null==list||0==list.size()){
			model.addAttribute("emptyMsg", "没有分类");
			return "admin/category/category-item";
		}
		model.addAttribute("categoryList", list);
		return "admin/category/category-item";
	}
	
	/**
	 * 分类列表--分页
	 * @param model
	 * @param pageBean
	 * @return
	 */
	@RequestMapping("/getAll")
	public String getAll(Model model, PageBean<Category> pageBean){
		if(pageBean==null)
			pageBean = new PageBean<Category>();
		pageBean = categoryService.selectAll(pageBean);
		
		if(null==pageBean){
			model.addAttribute("emptyMsg", "没有分类");
			return "admin/category/category-item";
		}
		model.addAttribute("page", pageBean);
		return "admin/category/category-item";
	}
	
	/**
	 * 获取指定ID的分类
	 * @param model
	 * @param cid
	 * @return
	 */
	@RequestMapping("/getOneByCid")
	public String getOneByCid(Model model,String cid){
		
		Category category = categoryService.selectOneByCid(cid);
		
		if(null==category){
			model.addAttribute("emptyMsg", "没有分类");
			return "admin/category/category-update";
		}
		
		if(null!=category.getParent()){
			List<Category> parents = categoryService.selectParentCategory();
			if(null!=parents){
				model.addAttribute("parents", parents);
			}
		}
		
		model.addAttribute("category", category);
		return "admin/category/category-update";
		
	}
	/**
	 * 更新分类信息--更新需谨慎,更新后前台相应分类的文章,资源将不能显示
	 * 若要前台显示,请修改Constant.java文件中的相应分类属性或者联系开发者
	 * @param model
	 * @param category
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateCategory")
	@ResponseBody
	public String updateCategory(Model model,
			@Validated @ModelAttribute @RequestBody Category category,
			BindingResult bindingResult) throws Exception{
		
		Map<String,String> map = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
		if(bindingResult.hasErrors()){
			List<FieldError> listError =  bindingResult.getFieldErrors();
			for(FieldError error:listError)
				map.put(error.getField(),error.getDefaultMessage());
			return mapper.writeValueAsString(map);
		}
		
		if(categoryService.updateCategory(category)>0){
			map.put("successMsg", "修改分类成功");
		}
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 删除分类信息--删除需谨慎,更新后前台相应分类的文章,资源将不能显示
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteCategory")
	@ResponseBody
	public String deleteCategory(String[] cid) throws Exception{
		
		int count = categoryService.deleteCategory(cid);
		JsonObject gson = new JsonObject();
		if(count== Constant.CATEGORY_DELETE_CHILD_NOT_NULL)
			gson.addProperty("status",Constant.CATEGORY_DELETE_CHILD_NOT_NULL);
		else if(count<=0)
				gson.addProperty("failMsg", Constant.CATEGORY_DELETE_FAILED);
			 else
				gson.addProperty("successMsg", Constant.CATEGORY_DELETE_SUCCESS);
		
		return gson.toString();
	}
	
	
	@RequestMapping("/saveCategoryPre")
	public String saveCategoryPre(Model model, String path)throws Exception{
		
		if(null==path){
			List<Category> parents = categoryService.selectParentCategory();
			
			model.addAttribute("parents", parents);
			
			return "forward:/WEB-INF/admin/category/category-input-second.jsp";
		}
		
		return "forward:/WEB-INF/admin/category/category-input-parent.jsp";
		
		
	
	}
	/**
	 * 校验分类名是否可用
	 * @param cname
	 * @return
	 */
	@RequestMapping("/ajaxCname")
	@ResponseBody
	public String ajaxCname(String cname){
		
		boolean valite=categoryService.ajaxCname(cname);
		if(valite)
			return "true";
		return "false";
		
	}
	
	/**
	 * 更新分类时,分类名是否可用
	 * @param cname
	 * @param cid
	 * @return
	 */
	@RequestMapping("/ajaxUpdateCname")
	@ResponseBody
	public String ajaxUpdateCname(String cname,String cid){
		
		boolean valite=categoryService.ajaxUpdateCname(cname,cid);
		if(valite)
			return "true";
		return "false";
		
	}
	
	/**
	 * 添加分类
	 * @param category
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveCategory")
	@ResponseBody
	public String saveCategory(@ModelAttribute @RequestBody @Validated Category category,
			BindingResult result)throws Exception{
		JsonObject gson = new JsonObject();
		
		categoryService.saveHandle(category, result, gson);
		
		return gson.toString();
	}
	
	
	/**
	 * 添加微机二级分类
	 * @param category
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveSecondCategory")
	@ResponseBody
	public String saveSecondCategory(
			@ModelAttribute @RequestBody @Validated Category category,
			BindingResult result)throws Exception{
		JsonObject gson = new JsonObject();

		if(null==category.getParent()){
			gson.addProperty("status",Constant.CATEGORY_SAVE_PARENT_NULL);
			return gson.toString();
		}
		
		categoryService.saveHandle(category, result, gson);
		
		return gson.toString();
	}
	
	
}
