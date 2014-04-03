package westion.www.service.impl;

import java.util.List;

import westion.www.dao.AdminUserDao;
import westion.www.dao.impl.AdminUserDaoImpl;
import westion.www.entity.AdminUser;
import westion.www.exception.LoginException;
import westion.www.service.AdminUserService;

public class AdminUserServiceImpl implements AdminUserService {

	private AdminUserDao adminUserDao = new AdminUserDaoImpl();

	@Override
	public List<AdminUser> list() {
		return adminUserDao.list();
	}

	@Override
	public AdminUser login(String stuId, String pwd) {
		try {
			AdminUser adminUser = adminUserDao.findByStuId(stuId);
			if (!adminUser.getPwd().equals(pwd)) {
				throw new LoginException();
			} else {
				return adminUser;
			}
		} catch (Exception e) {
			throw new LoginException(e);
		}

	}

}
