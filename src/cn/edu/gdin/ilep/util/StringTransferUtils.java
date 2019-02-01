package cn.edu.gdin.ilep.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;



public class StringTransferUtils {
	private static String html = null;
	
	/**
	 * 将',\",\n,\t,空格,<,>转义成html语言中的格式
	 * @param content
	 * @return
	 */
	public static String string2html(String content){
		if(StringUtils.isEmpty(content)) return "";
	    html = content;
	    html = StringUtils.replace(html, "'", "&apos;");
	    html = StringUtils.replace(html, "\"", "&quot;");
	    html = StringUtils.replace(html, "\n", "");
	    html = StringUtils.replace(html, "\t", "&nbsp;&nbsp;");// 替换跳格
	    html = StringUtils.replace(html, " ", "&nbsp;");// 替换空格
	    html = StringUtils.replace(html, "<", "&lt;");
	    html = StringUtils.replace(html, ">", "&gt;");
	    return html;
		
	}
	/**
	 * 字符串转字节数组,默认采用utf-8编码
	 * @param contentString
	 * @param charsetName
	 * @return
	 * @throws IOException
	 */
	public static byte[] string2byte(String contentString,String charsetName) throws IOException{
		if(StringUtils.isEmpty(charsetName))
			return contentString.getBytes("UTF-8");
		else
			return contentString.getBytes(charsetName);
	}
	/**
	 * 字节数组转字符串,默认采用utf-8编码
	 * @param content
	 * @param charsetName
	 * @return
	 * @throws IOException
	 */
	public static String byte2string(byte[] content,String charsetName) throws IOException{
		if(StringUtils.isEmpty(charsetName))
			return new String(content,"UTF-8");
		else
			return new String(content,charsetName);
	}
	
}
