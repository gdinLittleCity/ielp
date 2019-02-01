package cn.edu.gdin.ilep.manager.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.gdin.ilep.util.StringTransferUtils;
/**
 * image_b 大图的路径,即上传文章内容的第一张图片的路径,默认为null
 * image_s 小图的路径,暂未使用的字段 默认为null
 * @author gdinxiao
 *
 */
public class News {
	private String nid;
	@Size(max=100,min=2,message="{news.title.length}")
	@NotBlank(message="{news.title.empty}")
	private String title;
	private String image_b=null;
	private String image_s=null;
	private byte[] content;
	private String publish_time;
	private Category category;
	//转义
	private String htmlString;
	
	//不转义
	@NotEmpty(message="{news.content.empty}")
	@NotNull(message="{news.content.empty}")
	private String contentString;
	
	
	private Administor user;
	
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage_b() {
		return image_b;
	}
	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}
	public String getImage_s() {
		return image_s;
	}
	public void setImage_s(String image_s) {
		this.image_s = image_s;
	}
	
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getHtmlString() {
		if(StringUtils.isEmpty(htmlString))
			htmlString = contentString;
		htmlString = StringTransferUtils.string2html(htmlString);
		return htmlString;
	}
	
	public String getContentString() {
		return contentString;
	}
	public void setContentString(String contentString) {
		this.contentString = contentString;
	}
	public Administor getUser() {
		return user;
	}
	public void setUser(Administor user) {
		this.user = user;
	}
	
	
	
}
