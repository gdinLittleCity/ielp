package com.gdinxiao.show.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.gdinxiao.common.util.Constant;
import com.gdinxiao.manager.domain.Category;
import com.gdinxiao.manager.domain.News;
import com.gdinxiao.manager.domain.Resources;
import com.gdinxiao.page.PageBean;
import com.gdinxiao.show.service.ShowNewsService;
import com.gdinxiao.show.service.ShowResourceService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/show")
public class ShowResourceAction {
//	private static final Logger log = Logger.getLogger(ShowResourceAction.class);
	@Resource(name="showResourceService")
	private ShowResourceService showResourceService;
	
	@Resource(name="showNewsService")
	private ShowNewsService showNewsService;
	/**
	 * PPT下载列表
	 * @param pageBean
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/pptList")
	public String pptList(PageBean<Resources> pageBean, HttpServletRequest request, Model model){
		if(pageBean==null)
			pageBean = new PageBean<Resources>();
		
		setModel(pageBean, model, Constant.SHOW_CATEGORY_PPT);
		return "forward:/jsp/resource/ppt-down.jsp";
	}
	/**
	 * 批判性思维下载列表
	 * @param pageBean
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("criticalMuke")
	public String criticalMuke(PageBean<Resources> pageBean,HttpServletRequest request,Model model){
		if(pageBean==null)
			pageBean = new PageBean<Resources>();
		
		setModel(pageBean, model,Constant.SHOW_CATEGORY_CRITICAL);
		return "forward:/jsp/resource/critical-muke.jsp";
	}
	/**
	 * 信息检索下载列表
	 * @param pageBean
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("searchMuke")
	public String searchMuke(PageBean<Resources> pageBean,HttpServletRequest request,Model model){
		if(pageBean==null)
			pageBean = new PageBean<Resources>();
		setModel(pageBean, model,Constant.SHOW_CATEGORY_SEARCH);
		return "forward:/jsp/resource/search-muke.jsp";
	}
	

	private void setModel(PageBean<Resources> pageBean, Model model,String cname) {
		PageBean<News> newsPage = new PageBean<News>();
		newsPage = showNewsService.getNewsList(newsPage,Constant.SHOW_CATEGORY_NEWS);
		
		pageBean = showResourceService.getResourceList(pageBean,cname);
		
//		List<Category> resCategoryList = showResourceService.getResCategoryList();
//		
//		model.addAttribute("resCategoryList", resCategoryList);
//		model.addAttribute("currentCategory", cname);
		
		model.addAttribute("newsPage", newsPage);
		model.addAttribute("pageBean", pageBean);
	}
	
	
	/**
	 * 下载资源
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/downResource")
	public ResponseEntity<byte[]> downResource(HttpServletRequest request) throws IOException {
		String sid = request.getParameter("sid");
		if(sid==null)
			return new ResponseEntity<byte[]>(new String("请求错误").getBytes("utf-8"),new HttpHeaders(),HttpStatus.BAD_REQUEST);
	    
		Resources res = showResourceService.getResourceInfo(sid);
		if(null==res)
			return new ResponseEntity<byte[]>(new String("请求错误").getBytes("utf-8"),new HttpHeaders(),HttpStatus.BAD_REQUEST);
		HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    String fileName=  res.getPath().substring(res.getPath().lastIndexOf("/"),res.getPath().length());
	    
	    headers.setContentDispositionFormData("attachment",new String(fileName.getBytes("utf-8"),"iso-8859-1"));  
	    
//	    log.info("下载资源文件成功");
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(showResourceService.getFile(res)),  
	                                      headers, HttpStatus.CREATED);  
	}
	
	@RequestMapping("/showAllResource")
	public String showAllResource(PageBean<Resources> pageBean,HttpServletRequest request,Model model){
		if(pageBean==null)
			pageBean = new PageBean<Resources>();
		String cname = (String) request.getParameter("cname");
		if(StringUtils.isEmpty(cname)){//cname默认为Constant.SHOW_CATEGORY_PPT
			setAllResource(pageBean, model,Constant.SHOW_CATEGORY_PPT);
//			log.info("获取默认分类-讲座PPT下载信息成功");
		}else{
			setAllResource(pageBean, model,cname);
//			log.info("获取指定分类"+cname+"信息成功");
		}
		
		return "forward:/jsp/resource/resource.jsp";
	}
	
	/**
	 * 设置showAllResource中的model
	 * @param pageBean
	 * @param model
	 * @param cname
	 */
	private void setAllResource(PageBean<Resources> pageBean, Model model,String cname) {
		PageBean<News> newsPage = new PageBean<News>();
		newsPage = showNewsService.getNewsList(newsPage,Constant.SHOW_CATEGORY_NEWS);
		
		pageBean = showResourceService.getResourceList(pageBean,cname);
		
		List<Category> resCategoryList = showResourceService.getResCategoryList();
		
		model.addAttribute("resCategoryList", resCategoryList);
		model.addAttribute("currentCategory", cname);
		model.addAttribute("newsPage", newsPage);
		model.addAttribute("pageBean", pageBean);
	}
	
}
