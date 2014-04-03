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
 * ��Ӧ����Ա����
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.dao.AdminUserDao
 */
public class AdminUserAction {

	/** ������� */
	private Map<String, String[]> params = null;
	/** �����û���session */
	private HttpSession session = null;
	/** ��վȫ�ֵ������ļ� */
	private Properties properties = null;
	/** ��վȫ�ֵķ���״̬�ļ� */
	private List<String> errorList = null;

	private AdminUserService adminUserService = new AdminUserServiceImpl();

	/**
	 * ���캯������request�л�ȡ��Ӧ�����ԣ�����˽�г�Ա��ʼ��
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
	 * ��ȡ���й���Ա��Ϣ
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
	 * ��¼����
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
