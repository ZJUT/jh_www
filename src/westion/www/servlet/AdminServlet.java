package westion.www.servlet;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * �������Ա��������࣬���ö�Ӧ��action��
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * 
 */
public class AdminServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ���ܹ���Ա���GET������Ӧ�ĺ���
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
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) request
				.getAttribute("params");

		String model[] = params.get("model");
		String action[] = params.get("action");

		try {
			Class<?> clz = Class.forName("westion.www.admin.action.Admin"
					+ model[0] + "Action");
			Method method = clz.getMethod(action[0]);
			Constructor<?> constructor = clz
					.getConstructor(HttpServletRequest.class);
			Object object = constructor.newInstance(request);
			Object data = method.invoke(object);
			request.setAttribute("data", data);
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
			this.getServletContext().log(e.getMessage());
			errorList.add(config.getProperty("patternError"));
		} catch (Exception e) {
			e.printStackTrace();
			this.getServletContext().log(e.getMessage());
			errorList.add(config.getProperty("unknownError"));
		}
	}

	/**
	 * ���ܹ���Ա���POST������Ӧ�ĺ���
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

}
