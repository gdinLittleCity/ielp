package cn.edu.gdin.ilep.show.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.show.service.ShowNewsService;

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
