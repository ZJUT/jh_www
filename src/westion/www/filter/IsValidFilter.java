package westion.www.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;

import westion.www.utls.ReturnData;

/**
 * 
 * ���������������й���������Դ֮�ϣ� ��Ҫ���ܣ���֤token�ĺϷ��Ժ�ͳһ����װ���󣬷���json��ʽ������]
 * 
 * ����response����,���ظ��������ContentType text/html;charset=UTF\-8
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class IsValidFilter implements Filter {

	/** ��վȫ�ֵ������ļ� */
	private Properties properties = null;

	/**
	 * ����������ʱ��˽�б���ָ��null,���ڴ�������������
	 * 
	 * */
	@Override
	public void destroy() {
		properties = null;
	}

	/**
	 * ���������ܴ���
	 * 
	 * @param request
	 *            ServletRequest
	 * @param response
	 *            ServletResponse
	 * @param chain
	 *            FilterChain
	 * @throws ServletException
	 * @throws IOException
	 * */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		@SuppressWarnings("unchecked")
		List<String> errorList = (List<String>) request
				.getAttribute("errorList");
		@SuppressWarnings("unchecked")
		Map<String, String[]> params = (Map<String, String[]>) request
				.getAttribute("params");

		/*
		 * ��֤token�Ϸ���
		 */
		String token = null;
		String appid = null;
		String value = null;
		
		try {
			token = params.get("token")[0];
			appid = params.get("APPID")[0];
		} catch (Exception e) {
			errorList.add(properties.getProperty("validError"));
		}
		if (appid != null)
			value = properties.getProperty(appid);

		if (token == null || appid == null || value == null
				|| !value.equals(token)) {
			errorList.add(properties.getProperty("validError"));
		} else {
			chain.doFilter(request, response);
		}

		PrintWriter out = ((HttpServletResponse) response).getWriter();

		// ���ڷ�װ��������json
		ReturnData resultData = new ReturnData();
		if (errorList.size() > 0) {
			resultData.setStatus(errorList.get(0));
			resultData.setData(null);
		} else {
			resultData.setStatus(properties.getProperty("noError"));
			resultData.setData(request.getAttribute("data"));
		}

		String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create().toJson(resultData);
		out.print(json);
		out.close();
	}

	/**
	 * ��ȡservletContext��servletRequest���һЩ����
	 * 
	 * @param config
	 *            FilterConfig
	 * */
	@Override
	public void init(FilterConfig config) throws ServletException {
		properties = (Properties) config.getServletContext().getAttribute(
				"pageConfig");

	}

}
