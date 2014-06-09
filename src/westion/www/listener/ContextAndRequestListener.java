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
 * Context��request�ļ����� ��Ҫ���ܣ���ʼ��context����request���һЩ���� Properties errorList
 * params
 * 
 * ��ʼ��request�ı���UTF\-8
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
	 * servletRequest��destroy,�ÿ�errorList��params
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
	 * ��ʼ������һ����������ݷ���request�У�����ʼ���˴��״̬�����errorList
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
	 * servletContext��destroy
	 * 
	 * @param sce
	 *            ServletContextEvent
	 * */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		properties = null;
	}

	/**
	 * ��ʼ����������Ӧ�õ������ļ�����������Ӧ�õ�servletContext
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
