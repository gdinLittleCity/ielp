package com.gdinxiao.common.exception;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import cn.edu.gdin.ilep.util.Constant;

public class UploadException implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = null;  
        // 根据不同错误转向不同页面  
        if(ex instanceof MaxUploadSizeExceededException) {
        	mv = new ModelAndView();
        	mv.setViewName("admin/resource/resource-input");
        	mv.addObject("fileSize", Constant.UPLOAD_FILE_SIZE);
        }
        if(ex instanceof java.io.FileNotFoundException) {
        	mv = new ModelAndView();
        	
        	mv.setViewName("forward:/jsp/errorMessage.jsp");
        	mv.addObject("errorMessage","请求下载的文件不存在");
        }
        return mv;
	}

}
