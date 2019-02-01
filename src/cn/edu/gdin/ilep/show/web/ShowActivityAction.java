package cn.edu.gdin.ilep.show.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.show.service.ShowActivityService;


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
	public String showList(Model model,PageBean<News> pageBean){
		if(pageBean==null)
			pageBean = new PageBean<News>();
		
		pageBean = showActivityService.getActivityList(pageBean);
		
		model.addAttribute("pageBean", pageBean);
		
		return "forward:/jsp/activity/activity.jsp";
	}
	
	
}
