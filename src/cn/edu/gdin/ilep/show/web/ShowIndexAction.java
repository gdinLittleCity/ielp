package cn.edu.gdin.ilep.show.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.show.service.ShowNewsService;


@Controller
@RequestMapping("/show")
public class ShowIndexAction {
	@Resource(name="showNewsService")
	private ShowNewsService showNewsService;
	
	/**
	 * 前台首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model){
		//新闻动态列表 标题,时间
		List<News> newsList = showNewsService.newsList();
		//信息素质列表 标题,时间
		List<News> qualityList = showNewsService.qualityList();
		//信息获取&甄别 标题,时间
		List<News> obtainList = showNewsService.obtainList();
		List<News> identifyList  = showNewsService.identifyList();
		//科普活动报道 标题,时间,图片路径
		List<News> activityList  = showNewsService.activityList();
		
		model.addAttribute("newsList", newsList);
		model.addAttribute("qualityList", qualityList);
		model.addAttribute("obtainList", obtainList);
		model.addAttribute("identifyList", identifyList);
		model.addAttribute("activityList", activityList);
		return "forward:/jsp/index.jsp";
	}
}
