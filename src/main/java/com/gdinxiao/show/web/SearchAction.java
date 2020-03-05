package com.gdinxiao.show.web;

import javax.annotation.Resource;

import com.gdinxiao.common.util.Constant;
import com.gdinxiao.manager.domain.News;
import com.gdinxiao.manager.domain.Resources;
import com.gdinxiao.page.PageBean;
import com.gdinxiao.show.service.LuceneService;
import com.gdinxiao.show.service.ShowNewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/show")
public class SearchAction {
	@Resource(name="luceneService")
	private LuceneService luceneService;
	
	@Resource(name="showNewsService")
	private ShowNewsService showNewsService;
	
	/**
	 * 搜索
	 * @param condition
	 * @param category
	 * @param pageNum
	 * @param model
	 * @return
	 */
	@RequestMapping("/search")
	public String search(String condition,int category,int pageNum,Model model){
		
		PageBean<News> newsPage = new PageBean<News>();
		newsPage = showNewsService.getNewsList(newsPage, Constant.SHOW_CATEGORY_NEWS);
		
		model.addAttribute("newsPage", newsPage);
		model.addAttribute("condition", condition);
		model.addAttribute("category", category);
		
		if(category==1){
			PageBean<News> pageBean = new PageBean<News>();
			pageBean.setPageNum(pageNum);
			pageBean = luceneService.searchNews(condition, pageBean);
			model.addAttribute("pageBean", pageBean);
			return "forward:/jsp/news-result.jsp";
		}else{
			PageBean<Resources> pageBean = new PageBean<Resources>();
			pageBean.setPageNum(pageNum);
			pageBean = luceneService.searchResource(condition, pageBean);
			model.addAttribute("pageBean", pageBean);
			return "forward:/jsp/resource-result.jsp";
		}
		
	}
	
	
}
