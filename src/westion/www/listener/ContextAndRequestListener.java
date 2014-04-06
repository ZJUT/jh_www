package westion.www.listener;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Context和request的监听器 主要功能，初始化context域与request域的一些对象 Properties errorList
 * params
 * 
 * 初始化request的编码UTF\-8
 * 
 * @version 1.0, 2014-4-2
 * @author westion
 * @since JDK1.7
 */

public class ContextAndRequestListener implements ServletContextListener,
		ServletRequestListener {

	private Properties properties = null;

	private List<String> errorList = null;
	private Map<String, String[]> params = null;

	/**
	 * servletRequest的destroy,置空errorList和params
	 * 
	 * @param sre
	 *            ServletRequestEvent
	 * */
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		errorList = null;
		params = null;
	}

	/**
	 * 初始化，将一次请求的数据放入request中，还初始化了存放状态错误的errorList
	 * 
	 * @param sre
	 *            ServletRequestEvent
	 * */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre
				.getServletRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		errorList = new ArrayList<String>();
		request.setAttribute("errorList", errorList);
		params = request.getParameterMap();
		request.setAttribute("params", params);
		
		for (Entry<String, String[]> para : params.entrySet()) {

			System.out.println(para.getKey() + " = " + para.getValue()[0]);

		}
	}

	/**
	 * servletContext的destroy
	 * 
	 * @param sce
	 *            ServletContextEvent
	 * */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		properties = null;
	}

	/**
	 * 初始化，将整个应用的配置文件，放入整个应用的servletContext
	 * 
	 * @param sce
	 *            ServletContextEvent
	 * */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		InputStream in = sce.getServletContext().getResourceAsStream(
				sce.getServletContext().getInitParameter("config"));
		properties = new Properties();

		try {
			properties.load(in);
			sce.getServletContext().setAttribute("pageConfig", properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (in != null)
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
