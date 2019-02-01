package cn.edu.gdin.ilep.manager.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import cn.edu.gdin.ilep.manager.dao.ResourceDao;
import cn.edu.gdin.ilep.manager.domain.Administor;
import cn.edu.gdin.ilep.manager.domain.Resources;
import cn.edu.gdin.ilep.manager.service.ResourceService;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.show.service.LuceneService;
import cn.edu.gdin.ilep.util.CommonUtils;
import cn.edu.gdin.ilep.util.Constant;
import cn.edu.gdin.ilep.util.StringTransferUtils;
import cn.edu.gdin.ilep.util.TimeUtils;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	private static final Logger log = Logger
			.getLogger(ResourceServiceImpl.class);

	@Resource(name = "resourceDao")
	private ResourceDao resourceDao;

	@Resource(name = "luceneService")
	private LuceneService luceneService;

	@Override
	public String addResource(MultipartFile file, Resources resources,
			HttpSession session) throws Exception {
		String path = Constant.UPLOAD_PATH + "/" + file.getOriginalFilename();

		File targetFile = new File(path);

		if (!targetFile.getParentFile().exists())
			targetFile.getParentFile().mkdirs();
		// 设置资源的路径和发布时间
		resources.setPath(path);
		resources.setPublish_time(TimeUtils
				.getCurrentTime(TimeUtils.Time_FORMAT_YYYY_MM_DD));

		// 设置上传者
		Administor admin = (Administor) session
				.getAttribute(Constant.ADMIN_USER);
		resources.setUname(admin.getName());

		try {
			file.transferTo(targetFile);
			log.info("ResourceServiceImpl.addResource--资源写入磁盘成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("ResourceServiceImpl.addResource--资源写入磁盘失败");
			e.printStackTrace();
			return Constant.UPLOAD_WRITE_ERROR;
		}
		if (insertResource(resources) > 0) {
			log.info("ResourceServiceImpl.addResource--资源信息写入数据库成功");
			return Constant.RESOURCE_INSERT_SUCCESS;
		}

		else {
			log.info("ResourceServiceImpl.addResource--资源信息写入数据库失败");
			return Constant.RESOURCE_INSERT_ERROR;
		}

	}

	/**
	 * 向数据库插入资源记录
	 * 
	 * @param resources
	 * @return
	 * @throws SQLException
	 */
	private int insertResource(Resources resources) throws Exception {
		resources.setSid(CommonUtils.uuid());
		int count = resourceDao.insertResource(resources);

		resources
				.setTitle(StringTransferUtils.string2html(resources.getTitle()));
		luceneService.resourceToDocument(resources);
		if (count <= 0)
			return -1;
		else
			return count;
	}

	@Override
	public PageBean<Resources> getAllResourceInfo(PageBean<Resources> pageBean) {
		List<Resources> list = null;
		try {
			list = resourceDao.selectAllResource(pageBean);
			if (null != list && 0 != list.size()) {
				// 转义特殊字符
				for (Resources res : list)
					res.setTitle(StringTransferUtils.string2html(res.getTitle()));
			}
			pageBean.setBeanList(list);

			int count = resourceDao.selectResourceCount();
			pageBean.setPageTotal(count % pageBean.getPageSize() == 0 ? count
					/ pageBean.getPageSize() : 1 + count
					/ pageBean.getPageSize());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pageBean;
	}

	@Override
	public Resources getResource(Resources resources) {
		List<Resources> list = null;
		try {
			list = resourceDao.selectResourceByID(resources.getSid());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null != list && 0 != list.size()) {
			Resources res = list.get(0);
			// 转义特殊字符
			res.setTitle(StringTransferUtils.string2html(res.getTitle()));
			return res;
		}

		return null;
	}

	@Override
	public int updateResource(Resources resources, HttpSession session) {
		int count = -1;
		Administor admin = (Administor) session
				.getAttribute(Constant.ADMIN_USER);
		resources.setUname(admin.getName());
		try {
			resources.setPublish_time(TimeUtils
					.getCurrentTime(TimeUtils.Time_FORMAT_YYYY_MM_DD));

			resources.setTitle(StringTransferUtils.string2html(resources
					.getTitle()));

			count = resourceDao.updateResource(resources);
			luceneService.updateResourceIndex(resources);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int deleteResource(String[] ids) {
		int count = -1;
		try {
			for (String id : ids) {
				luceneService.deleteResourceIndex(id);
				resourceDao.deleteResourceByID(id);
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
