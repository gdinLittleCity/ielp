package cn.edu.gdin.ilep.manager.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import cn.edu.gdin.ilep.manager.domain.Category;
import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.manager.service.CategoryService;
import cn.edu.gdin.ilep.manager.service.NewsService;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.util.Constant;


@Controller
@RequestMapping("/admin/news")
public class NewsAction {
	@Resource(name="newsService")
	private NewsService newsService;
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	/**
	 * 添加文章预处理--分类信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/newsInputPre")
	public String newsInput(Model model){
		List<Category> list = categoryService.selectAllCategory();
		
		model.addAttribute("categoryList", list);
		
		return "admin/news/news-input";
	}
	/**
	 * 添加文章
	 * @param news
	 * @param result
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertNews")
	@ResponseBody
	public String insertNews(@Validated @ModelAttribute News news,BindingResult result,HttpSession session)throws Exception{
		JsonObject gson = new JsonObject();
		List<FieldError> errors =  result.getFieldErrors();
		
		if(null!=errors&&0!=errors.size()){
			for(FieldError error:errors)
				gson.addProperty(error.getField(), error.getDefaultMessage());
				
			return gson.toString();
		}
		
		int count = newsService.addNews(news,session);
		
		if(count<=0)
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson.toString();
		
	}
	
	/**
	 * 文章列表
	 * @param model
	 * @param pageBean
	 * @return
	 */
	@RequestMapping("/getAllNews")
	public String getAllNews(Model model,PageBean<News> pageBean){
		if(null==pageBean)
			pageBean = new PageBean<News>();
		
		pageBean  = newsService.getAllNews(pageBean);
		
		model.addAttribute("page", pageBean);
		
		return "admin/news/news-item";
	}
	
	/**
	 * 删除文章
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteNews")
	@ResponseBody
	public String deleteNews(String[] ids){
		JsonObject gson = new JsonObject();
		
		int count = newsService.deleteNews(ids);
		
		if(count<0)
			gson.addProperty("status",Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		
		return gson.toString();
	}
	
	/**
	 * 更新文章
	 * @param news
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateNews")
	@ResponseBody
	public String updateNews(@Validated @ModelAttribute News news,BindingResult result,HttpSession session){
		
		JsonObject gson = new JsonObject();
		List<FieldError> errors =  result.getFieldErrors();
		
		if(null==news.getCategory()||null==news.getCategory().getCid()){
			gson.addProperty("cid", "分类不能为空");
			return gson.toString();
		}
		
		if(null!=errors&&0!=errors.size()){
			for(FieldError error:errors)
				gson.addProperty(error.getField(), error.getDefaultMessage());
			
			return gson.toString();
		}
		
		int count = newsService.updateNews(news,session);
		
		if(count<=0)
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson.toString();
	}
	
	
	@RequestMapping("/updatePre")
	public String updatePre(Model model,String nid){
		
		News news = newsService.selectNewsByID(nid);
		
		if(news==null){
			model.addAttribute("status", Constant.FAIL);
			return "admin/news/news-update";
		}
		
		List<Category> list = categoryService.selectAllCategory();
		
		model.addAttribute("categoryList", list);
		model.addAttribute("news", news);
		return "admin/news/news-update";
		
	}
	
}
