package cn.edu.gdin.ilep.manager.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import cn.edu.gdin.ilep.manager.domain.Category;
import cn.edu.gdin.ilep.manager.domain.Resources;
import cn.edu.gdin.ilep.manager.service.CategoryService;
import cn.edu.gdin.ilep.manager.service.ResourceService;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.util.Constant;

@RequestMapping("admin/resource")
@Controller
public class ResourceAction {
	private static final Logger log = Logger.getLogger(ResourceAction.class);
	
	@Resource(name = "categoryService")
	private CategoryService categoryService;

	@Resource(name = "resourceService")
	private ResourceService resourceService;

	/**
	 * 发布资源的预处理--将资源分类加载到页面中
	 * @param model
	 * @return
	 */
	@RequestMapping("/inputResourcePre")
	public String inputResourcePre(Model model) {

		List<Category> list = categoryService.getResourceCategory();

		model.addAttribute("categoryList", list);
		log.info("ResourceAction.inputResourcePre--发布资源预处理成功");
		return "admin/resource/resource-input";
	}
	/**
	 * 发布资源,将资源写入磁盘,将资源记录写入数据库
	 * @param file
	 * @param request
	 * @param model
	 * @param response
	 * @param resources
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadFile/do")
	public String uploadFile(@RequestParam("file")MultipartFile file,
			HttpServletRequest request, Model model,HttpServletResponse response,
			@ModelAttribute @Validated Resources resources, BindingResult result)
			throws Exception {
		//设置相应头,不缓存
		response.setHeader("Cache-Control", "no-cache");
		List<Category> list = categoryService.getResourceCategory();
		model.addAttribute("categoryList", list);
		log.info("ResourceAction.uploadFile--获取资源分类成功");
		
		if(null==resources.getCategory()){
			model.addAttribute("cidNone", Constant.UPLOAD_CID_NONE);
			log.info("ResourceAction.uploadFile--参数-资源分类为空");
			return "admin/resource/resource-input";
		}
		
		if(file.isEmpty()){
			model.addAttribute("fileNone", Constant.UPLOAD_NONE_FILE);
			log.info("ResourceAction.uploadFile--参数-文件流为空");
			return "admin/resource/resource-input";
		}
			
		if(file.getSize()/(1024*1024)>Constant.UPLOAD_FILE_SIZE){
			model.addAttribute("fileSize", Constant.UPLOAD_FILE_SIZE_ERRORMESSAGE);
			log.info("ResourceAction.uploadFile--参数-文件大小超过最大值");
			return "admin/resource/resource-input";
		}
		
		String fileName = file.getOriginalFilename();
		if(!Constant.UPLOAD_FILE_TYPE.contains(fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()))){
			model.addAttribute("fileType", Constant.UPLOAD_TYPE_ERRORMESSAGE);
			log.info("ResourceAction.uploadFile--参数-文件类型错误："+fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()));
			return "admin/resource/resource-input";
		}
		
		String message = resourceService.addResource(file,resources,request.getSession());
		model.addAttribute("message", message);
		log.info("ResourceAction.uploadFile--添加资源成功");
		return "admin/resource/resource-input";
	}

	

	/**
	 * 加载文件上传进度
	 * @param session
	 * @return
	 */
	@RequestMapping("/getProgress")
	@ResponseBody
	public String getProgress(HttpSession session){
		double pro = (Double) session.getAttribute("progress");
		System.out.println("progress %"+pro*100);
		return String.valueOf(Math.round(pro*100));
	
	}
	
	/**
	 * 资源列表
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping("/getResourceInfo")
	public String getResourceInfo(PageBean<Resources> pageBean,Model model){
		if(null==pageBean)
			pageBean = new PageBean<Resources>();
		
		pageBean = resourceService.getAllResourceInfo(pageBean);
		
		model.addAttribute("pageBean", pageBean);
		
		return "admin/resource/resource-item";	
		
	}
	
	/**
	 * 更新资源信息预处理
	 * @param resources
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateResourcePre")
	public String updateResourcePre(Resources resources,Model model){
		
		resources = resourceService.getResource(resources);
		if(null==resources){
			model.addAttribute("status", Constant.FAIL);
			return "admin/resource/resource-update";
		}
		
		model.addAttribute("status", Constant.SUCCESS);
		
		List<Category> list = categoryService.getResourceCategory();

		model.addAttribute("categoryList", list);
		
		model.addAttribute("resource", resources);
		return "admin/resource/resource-update";
	}
	/**
	 * 更新资源信息
	 * @param resources
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateResource")
	@ResponseBody
	public String updateResource(@Validated Resources resources,BindingResult result,HttpSession session){
		JsonObject gson = new JsonObject();
		List<FieldError> errors = result.getFieldErrors();
		if(null!=errors&&0!=errors.size()){
			for(FieldError error : errors)
				gson.addProperty(error.getField(), error.getDefaultMessage());
			
			if(null==resources.getCategory())
				gson.addProperty("cidNone", Constant.UPLOAD_CID_NONE);
			return gson.toString();
		}
		
		int count = resourceService.updateResource(resources,session);
		
		if(count < 0 )
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson.toString();
	}
	/**
	 * 删除资源--保留硬盘资源
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteResource")
	@ResponseBody
	public String deleteResource(String[] ids){
		JsonObject gson =new JsonObject();
		if(0==ids.length||null==ids){
			gson.addProperty("IDNone", Constant.RESOURCE_ID_NONE);
			return gson.toString();
		}
			
		
		int count = resourceService.deleteResource(ids);
		
		if(count<0)
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson.toString();
	}
	
}
