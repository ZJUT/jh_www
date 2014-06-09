package westion.www.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import westion.www.admin.action.AdminUserAction;

/**
 * 
 * 处理管理员登录
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * 
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 参见GET
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 * */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 用户登录功能
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 * */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		Properties config = (Properties) this.getServletContext().getAttribute(
				"pageConfig");
		@SuppressWarnings("unchecked")
		ArrayList<String> errorList = (ArrayList<String>) (request
				.getAttribute("errorList"));
		try {
			(new AdminUserAction(request)).login();
			errorList.add(config.getProperty("noError"));
		} catch (Exception e) {
			e.printStackTrace();
			this.getServletContext().log(e.getMessage());
			errorList.add(config.getProperty("unknownError"));
		}
	}
}
