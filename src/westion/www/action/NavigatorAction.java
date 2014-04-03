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
 * ����ҵ����
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.dao.NavigatorService
 */
public class NavigatorAction {
	/** ������� */
	private Map<String, String[]> params = null;
	/** ��վȫ�ֵ������ļ� */
	private Properties properties = null;

	private ArrayList<String> errorList = null;

	/** Dao���Ӧ����ʵ�� {@link NavigatorDaoImpl#NavigatorDaoImpl()} */
	private NavigatorService navigatorService = new NavigatorServiceImpl();
	
	

	/**
	 * ���캯������request�л�ȡ��Ӧ�����ԣ�����˽�г�Ա��ʼ��
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
	 * ��ȡ���е�����Ϣ
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
