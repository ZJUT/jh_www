package westion.www.service;

import java.util.List;

import westion.www.entity.AdminUser;


/**
 * 
 * 数据库user表Service类接口
 * 
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 * 
 */
public interface AdminUserService {

	 List<AdminUser> list();
	 AdminUser login(String stuId, String pwd);
	
}
