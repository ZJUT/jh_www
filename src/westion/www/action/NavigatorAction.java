package westion.www.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import westion.www.dao.impl.NavigatorDaoImpl;
import westion.www.entity.Navigator;
import westion.www.exception.QueryException;
import westion.www.service.NavigatorService;
import westion.www.service.impl.NavigatorServiceImpl;

/**
 * 
 * 导航业务类
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.dao.NavigatorService
 */
public class NavigatorAction {
	/** 请求参数 */
	private Map<String, String[]> params = null;
	/** 网站全局的配置文件 */
	private Properties properties = null;

	private ArrayList<String> errorList = null;

	/** Dao层对应操作实例 {@link NavigatorDaoImpl#NavigatorDaoImpl()} */
	private NavigatorService navigatorService = new NavigatorServiceImpl();
	
	

	/**
	 * 构造函数，从request中获取相应的属性，并对私有成员初始化
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	@SuppressWarnings("unchecked")
	public NavigatorAction(HttpServletRequest request) {
		super();
		this.params = request.getParameterMap();
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (ArrayList<String>) (request.getAttribute("errorList"));
	}

	/**
	 * 获取所有导航信息
	 * 
	 * @return events List\<Navigator\>
	 */
	public List<Navigator> list() {
		List<Navigator> navigators = null;
		try {
			navigators = navigatorService.list();
		} catch (QueryException e) {
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
		return navigators;
	}
}
