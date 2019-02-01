package cn.edu.gdin.ilep.manager.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import cn.edu.gdin.ilep.manager.dao.AdministorDao;
import cn.edu.gdin.ilep.manager.domain.Administor;
import cn.edu.gdin.ilep.manager.service.AdministorService;
import cn.edu.gdin.ilep.page.PageBean;
import cn.edu.gdin.ilep.util.CommonUtils;
import cn.edu.gdin.ilep.util.Constant;
import cn.edu.gdin.ilep.util.RSAUtils;

@Service("administorService")
public class AdministorServiceImpl implements AdministorService {
	@Resource(name="administorDao")
	private AdministorDao administorDao;
	
	@Override
	public boolean ajaxName(String name) {
		Administor admin = null;
		try {
			admin = administorDao.selectByName(name);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(null==admin)			
			return false;
		return true;
	}

	@Override
	public PageBean<Administor> getAdminInfo(PageBean<Administor> pageBean) {
		List<Administor> list = null;
		try {
			list = administorDao.selectAll(pageBean);
			pageBean.setBeanList(list);
			
			int count = administorDao.selectAdministorCount();
			pageBean.setPageTotal(count % pageBean.getPageSize() == 0 ? count
					/ pageBean.getPageSize() : 1 + count / pageBean.getPageSize());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pageBean;
	}

	@Override
	public Administor getAdministorInfo(Administor administor) {
		
		try {
			administor = administorDao.selectByID(administor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return administor;
	}

	@Override
	public int updateAmin(Administor administor) {
		int count = -1;
		try {
			count = administorDao.updateAdministor(administor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int addAdmin(Administor administor) {
		int count = -1;
		try {
			administor.setAid(CommonUtils.uuid());
			count = administorDao.insertAdministor(administor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	@Override
	public void pushKeyIntoSession(HttpSession session)
			throws NoSuchAlgorithmException {
		HashMap<String, Object>  map = RSAUtils.getKeys();
		
		RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
		RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
		
		session.setAttribute(Constant.RSA_PRIVATE_KEY, privateKey);
		
		session.setAttribute(Constant.RSA_PUBLIC_KEY_EXPONET, publicKey.getPublicExponent().toString(16));
		session.setAttribute(Constant.RSA_PUBLIC_KEY_MODULUS, publicKey.getModulus().toString(16));
	}

	@Override
	public boolean getAdmin(String aid, String name) {
		Administor admin = null;

		boolean validate = false;
		
		try {
			admin  = administorDao.selectByName(name);
			//可用
			if(null==admin)
				validate = true;
			//本身的管理员名,可用
			else if(admin.getAid().equals(aid))
				validate = true;
			else
				validate = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return validate;
	}

	@Override
	public Administor login(String name, String password) {
		Administor admin = new Administor();
		admin.setName(name);
		admin.setPassword(password);
		Administor user = null;
		try {
			user = administorDao.selectByNameAndPassword(admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public int deleteResource(String[] ids) {
		int count = -1;
		try {
			for(String id:ids){
				administorDao.deleteAdministor(id);
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}

	@Override
	public JsonObject validateAdmin(Administor administor, JsonObject gson,HttpSession session) {
		
		if(administor.getPhone().trim().equals("")||null==administor.getPhone()||11!=administor.getPhone().length()){
			gson.addProperty("phone","号码长度应该是11位" );
			return gson;
		}
		
		int count = administorDao.updatePassword(administor);
		
		if(count<=0)
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson;
	}
	
	
	@Override
	public boolean ajaxPassword(String aid, String password) {
		Administor admin = new Administor();
		admin.setAid(aid);
		boolean validate = false;
		try {
			Administor adminInfo = administorDao.selectByIDWithPassword(admin);
			if(adminInfo.getPassword().equals(password))
				validate = true;
			else
				validate = false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return validate;
	}

	@Override
	public boolean ajaxPhone(String aid, String phone) {
		Administor admin = new Administor();
		admin.setAid(aid);
		boolean validate = false;
		try {
			Administor adminInfo = administorDao.selectByID(admin);
			if(adminInfo.getPhone().equals(phone))
				validate = true;
			else
				validate = false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return validate;
	}
	
	@Override
	public JsonObject updatePassword(Administor administor, JsonObject gson) {
		
		int count = administorDao.updatePassword(administor);
		
		if(count<=0)
			gson.addProperty("status", Constant.FAIL);
		else
			gson.addProperty("status", Constant.SUCCESS);
		
		return gson;
	}

	@Override
	public int updateUser(Administor administor) {
		int count = -1;
		
		try {
			count = administorDao.updateUser(administor);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
}
