package com.gdinxiao.show.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.gdinxiao.common.util.Constant;
import com.gdinxiao.manager.domain.News;
import com.gdinxiao.page.PageBean;
import com.gdinxiao.show.service.ShowNewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/show")
public class ShowNewsAction {
	
	@Resource(name="showNewsService")
	private ShowNewsService showNewsService;
	
	/**
	 * 新闻动态
	 * @param pageBean
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/showList")
	public String showList(PageBean<News> pageBean, HttpServletRequest request, Model model){
		if(pageBean==null)
			pageBean = new PageBean<News>();
		pageBean = showNewsService.getNewsList(pageBean, Constant.SHOW_CATEGORY_NEWS);
		
		model.addAttribute("pageBean", pageBean);
		return "forward:/jsp/news/newsList.jsp";
	}
	
	/**
	 * 信息质量
	 * @param pageBean
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/quantityList")
	public String quantityList(PageBean<News> pageBean,HttpServletRequest request,Model model){
		if(pageBean==null)
			pageBean = new PageBean<News>();
		pageBean = showNewsService.getNewsList(pageBean,Constant.SHOW_CATEGORY_QUALITY);
		
		model.addAttribute("pageBean", pageBean);
		return "forward:/jsp/quantity/quatityList.jsp";
	}
	/**
	 * 信息获取
	 * @param pageBean
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/obtainList")
	public String obtainList(PageBean<News> pageBean,HttpServletRequest request,Model model){
		if(pageBean==null)
			pageBean = new PageBean<News>();
		pageBean = showNewsService.getNewsList(pageBean,Constant.SHOW_CATEGORY_OBTAIN);
		
		model.addAttribute("pageBean", pageBean);
		return "forward:/jsp/obtain/obtainList.jsp";
	}
	
	/**
	 * 信息甄别
	 * @param pageBean
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/identifyList")
	public String identifyList(PageBean<News> pageBean,HttpServletRequest request,Model model){
		if(pageBean==null)
			pageBean = new PageBean<News>();
		pageBean = showNewsService.getNewsList(pageBean,Constant.SHOW_CATEGORY_IDENTIFY);
		
		model.addAttribute("pageBean", pageBean);
		return "forward:/jsp/identify/identifyList.jsp";
	}
	
	/**
	 * 法律法规
	 * @param pageBean
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/moralToLaw")
	public String moralToLaw(PageBean<News> pageBean,HttpServletRequest request,Model model){
		if(pageBean==null)
			pageBean = new PageBean<News>();
		pageBean = showNewsService.getNewsList(pageBean,Constant.SHOW_CATEGORY_LAW);
		
		PageBean<News> newsPage = new PageBean<News>();
		
		newsPage = showNewsService.getNewsList(newsPage,Constant.SHOW_CATEGORY_NEWS);
		
		model.addAttribute("newsPage", newsPage);
		model.addAttribute("pageBean", pageBean);
		return "forward:/jsp/morals/law.jsp";
	}
	
	/**
	 * 案例
	 * @param pageBean
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/moralToCase")
	public String moralToCase(PageBean<News> pageBean,HttpServletRequest request,Model model){
		if(pageBean==null)
			pageBean = new PageBean<News>();
		pageBean = showNewsService.getNewsList(pageBean,Constant.SHOW_CATEGORY_CASE);
		
		PageBean<News> newsPage = new PageBean<News>();
		
		newsPage = showNewsService.getNewsList(newsPage,Constant.SHOW_CATEGORY_NEWS);
		
		model.addAttribute("newsPage", newsPage);
		model.addAttribute("pageBean", pageBean);
		
		return "forward:/jsp/morals/case.jsp";
	}
}
