package cn.edu.gdin.ilep.show.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.gdin.ilep.util.VerifyCodeUtil;


@Controller
@RequestMapping("/show")
public class VerifyCodeAction {
	/**
	 * 验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getVerifyCode")
	@ResponseBody
	public String getVerifyCode(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		VerifyCodeUtil vc = new VerifyCodeUtil();
		BufferedImage image = vc.getImage();//获取一次性验证码图片
		// 该方法必须在getImage()方法之后来调用
		System.out.println(vc.getText());//获取图片上的文本
		VerifyCodeUtil.output(image, response.getOutputStream());//把图片写到指定流中
		
		// 把文本保存到session中，为LoginServlet验证做准备
		request.getSession().setAttribute("verifyCode", vc.getText());
		
		return null;
	}
	
}
