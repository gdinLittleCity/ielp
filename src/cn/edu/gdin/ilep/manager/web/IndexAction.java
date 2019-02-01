package cn.edu.gdin.ilep.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("indexAction")
@RequestMapping("/admin")
public class IndexAction {
	
	@RequestMapping("/index")
	public String index()throws Exception{
		
		return "admin/index";
	}
	
}
