package com.gdinxiao.show.web;

import javax.annotation.Resource;

import com.gdinxiao.manager.domain.News;
import com.gdinxiao.page.PageBean;
import com.gdinxiao.show.service.ShowActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/show/activity")
public class ShowActivityAction {
	
	@Resource(name="showActivityService")
	private ShowActivityService showActivityService;
	
	/**
	 * 获取科普活动列表
	 * @param model
	 * @param pageBean
	 * @return
	 */
	@RequestMapping("/showList")
	public String showList(Model model, PageBean<News> pageBean){
		if(pageBean==null)
			pageBean = new PageBean<News>();
		
		pageBean = showActivityService.getActivityList(pageBean);
		
		model.addAttribute("pageBean", pageBean);
		
		return "forward:/jsp/activity/activity.jsp";
	}
	
	
}
