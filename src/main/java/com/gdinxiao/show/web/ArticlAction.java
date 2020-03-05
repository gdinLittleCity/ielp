package com.gdinxiao.show.web;

import javax.annotation.Resource;

import com.gdinxiao.manager.domain.News;
import com.gdinxiao.show.service.ShowNewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/show")
public class ArticlAction {
	@Resource(name="showNewsService")
	private ShowNewsService showNewsService;
	
	/**
	 * 获取文章详情
	 * @param model
	 * @param nid
	 * @return
	 */
	@RequestMapping("/articleInfo")
	public String articlInfo(Model model,String nid){
		
		News articl= showNewsService.getArticlInfo(nid);
		
		model.addAttribute("article", articl);
		return "forward:/jsp/article.jsp";
	}
}
