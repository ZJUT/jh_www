package westion.www.service;

import java.util.List;

import westion.www.entity.AdminUser;

public interface AdminUserService {

	 List<AdminUser> list();
	 AdminUser login(String stuId, String pwd);
	
}
