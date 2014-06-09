package westion.www.filter;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * ����Ա����������,��session��֤�Ƿ��Ѿ���¼
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class LoginFilter implements Filter {

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
	 * ���������ܴ��룬��֤�Ƿ��¼��
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
	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		Boolean isLogin = null;
		if (session != null) {
			isLogin = (Boolean) session.getAttribute("isLogin");
		}
		if (isLogin != null && isLogin) {
			chain.doFilter(request, response);
		} else {
			((List<String>) request.getAttribute("errorList")).add(properties
					.getProperty("noLoginError"));
		}
	}

	/**
	 * ��������ʼ������ȡ����Ӧ�õ������ļ�pageConfig{@link IsValidFilter#init(FilterConfig)}
	 * 
	 * @param config
	 *            FilterConfig
	 * @throws ServletException
	 * */
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.properties = (Properties) config.getServletContext().getAttribute(
				"pageConfig");
	}
}