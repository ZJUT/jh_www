package westion.www.service.impl;

import java.util.List;

import westion.www.dao.AdminUserDao;
import westion.www.dao.impl.AdminUserDaoImpl;
import westion.www.entity.AdminUser;
import westion.www.exception.LoginException;
import westion.www.service.AdminUserService;


/**
 * 
 * 数据库user表Service类接口实现
 * 
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 */
public class AdminUserServiceImpl implements AdminUserService {

	private AdminUserDao adminUserDao = new AdminUserDaoImpl();

	/**
	 * 返回用户列表
	 * @return List\<AdminUser\>
	 * 
	 */
	@Override
	public List<AdminUser> list() {
		return adminUserDao.list();
	}

	/**
	 * 用户登录 通过学号和密码
	 * @param stuId String
	 * @param pwd String
	 * @return AdminUser
	 */
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
