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
 * 管理员操作拦截器,用session验证是否已经登录
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 */
public class LoginFilter implements Filter {

	/** 网站全局的配置文件 */
	private Properties properties = null;

	/**
	 * 过滤器销毁时让私有变量指向null,便于创建者垃圾回收
	 * 
	 * */
	@Override
	public void destroy() {
		properties = null;
	}

	/**
	 * 过滤器功能代码，验证是否登录了
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
	 * 过滤器初始化，获取整个应用的配置文件pageConfig{@link IsValidFilter#init(FilterConfig)}
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