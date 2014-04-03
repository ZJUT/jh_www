package westion.www.admin.action;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import westion.www.dao.AdminUserDao;
import westion.www.dao.impl.AdminUserDaoImpl;
import westion.www.entity.AdminUser;
import westion.www.exception.LoginException;
import westion.www.exception.QueryException;
import westion.www.service.AdminUserService;
import westion.www.service.impl.AdminUserServiceImpl;

/**
 * 
 * 响应管理员请求
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.dao.AdminUserDao
 */
public class AdminUserAction {

	/** 请求参数 */
	private Map<String, String[]> params = null;
	/** 请求用户的session */
	private HttpSession session = null;
	/** 网站全局的配置文件 */
	private Properties properties = null;
	/** 网站全局的返回状态文件 */
	private List<String> errorList = null;

	private AdminUserService adminUserService = new AdminUserServiceImpl();

	/**
	 * 构造函数，从request中获取相应的属性，并对私有成员初始化
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	@SuppressWarnings("unchecked")
	public AdminUserAction(HttpServletRequest request) {
		super();
		this.params = (Map<String, String[]>) request.getAttribute("params");
		this.session = request.getSession();
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (List<String>) request.getAttribute("errorList");
	}

	/**
	 * 获取所有管理员信息
	 */
	public List<AdminUser> list() {
		List<AdminUser> adminUsers = null;
		try {
			adminUsers = adminUserService.list();
		} catch (QueryException e) {
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
		return adminUsers;
	}

	/**
	 * 登录功能
	 * 
	 * 
	 */
	public void login() {
		try {
			String[] stuIds = params.get("stuId");
			String[] pwds = params.get("pwd");
			AdminUser adminUser = adminUserService.login(stuIds[0], pwds[0]);
			session.setAttribute("isLogin", true);
			session.setAttribute("userInfo", adminUser);
		} catch (LoginException e) {
			errorList.add(properties.getProperty("stuOrPwdError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
	}

}
