package cn.edu.gdin.ilep.manager.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import cn.edu.gdin.ilep.manager.dao.NewsDao;
import cn.edu.gdin.ilep.manager.domain.Administor;
import cn.edu.gdin.ilep.manager.domain.News;
import cn.edu.gdin.ilep.manager.service.NewsService;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.show.service.LuceneService;
import cn.edu.gdin.ilep.util.CommonUtils;
import cn.edu.gdin.ilep.util.Constant;
import cn.edu.gdin.ilep.util.StringTransferUtils;
import cn.edu.gdin.ilep.util.TimeUtils;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
	@Resource(name = "newsDao")
	private NewsDao newsDao;
	
	@Resource(name="luceneService")
	private LuceneService luceneService;
	
	@Override
	public int addNews(News news,HttpSession session) {

		int count = -1;
		news = setttingNews(news,session);

		try {
			news.setNid(CommonUtils.uuid());
			count = newsDao.insert(news);
			
			news.setTitle(StringTransferUtils.string2html(news.getTitle()));
			luceneService.newsToDocument(news);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	private News setttingNews(News news,HttpSession session) {
		
		// 解析得到图片地址
		Document document = Jsoup.parse(news.getContentString());
		Elements els = document.select("img[src]");
		String image_b = null;
		if (null != els && 0 != els.size())
			image_b = els.get(0).attr("src");
		if(null!=session){
			
			Administor admin = (Administor) session.getAttribute(Constant.ADMIN_USER);
			
			news.setUser(admin);
		}
		
		try {
			// 字符串-->字节流
			news.setContent(StringTransferUtils
					.string2byte(news.getContentString(), null));
			// 设置发布时间
			news.setPublish_time(TimeUtils
					.getCurrentTime(TimeUtils.Time_FORMAT_YYYY_MM_DD));
			// 设置图片地址
			news.setImage_b(image_b);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return news;
	}

	@Override
	public PageBean<News> getAllNews(PageBean<News> pageBean) {
		// 得到数据
		List<News> listNews = newsDao.selectAllNews(pageBean);
		//转义特殊字符
		for(News news:listNews){
			news.setTitle(StringTransferUtils.string2html(news.getTitle()));
		}
		
		// 文章总数
		int count = newsDao.selectCount();
		// 设置单页数据
		if (null != listNews && 0 != listNews.size())
			pageBean.setBeanList(listNews);
		else
			pageBean.setBeanList(null);
		// 设置总页数
		pageBean.setPageTotal(count % pageBean.getPageSize() == 0 ? count
				/ pageBean.getPageSize() : 1 + count / pageBean.getPageSize());

		return pageBean;
	}

	@Override
	public int deleteNews(String[] ids) {
		int count = -1;
		if (0 == ids.length || ids == null)
			count = -1;
		
		try {
			for (String id : ids){
				newsDao.deleteNews(id);
				luceneService.deleteNewsIndex(id);
				count++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	
	@Override
	public int updateNews(News news,HttpSession session) {
		int count = -1;
		try {
			news = setttingNews(news,session);
			count = newsDao.updateNews(news);
			
			news.setTitle(StringTransferUtils.string2html(news.getTitle()));
			luceneService.updateNewsIndex(news);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public News selectNewsByID(String nid) {
		News news = null;
		try {
			List<News> list = newsDao.selectById(nid);
			if(null!=list&&0!=list.size()){
				news = list.get(0);
				//转义特殊字符
				news.setTitle(StringTransferUtils.string2html(news.getTitle()));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return news;
	}
}
