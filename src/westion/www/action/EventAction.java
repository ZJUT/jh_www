package westion.www.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import westion.www.entity.Event;
import westion.www.exception.AddException;
import westion.www.exception.QueryException;
import westion.www.service.EventService;
import westion.www.service.impl.EventServiceImpl;

/**
 * 
 * 事件业务类
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.dao.EventService
 */
public class EventAction {
	/** 请求参数 */
	private Map<String, String[]> params = null;
	/** 网站全局的配置文件 */
	private Properties properties = null;

	private ArrayList<String> errorList = null;

	private EventService eventService = new EventServiceImpl();

	private HttpServletRequest request = null;

	/**
	 * 构造函数，从request中获取相应的属性，并对私有成员初始化
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	@SuppressWarnings("unchecked")
	public EventAction(HttpServletRequest request) {
		super();
		this.params = (Map<String, String[]>) request.getAttribute("params");
		this.properties = (Properties) request.getServletContext()
				.getAttribute("pageConfig");
		this.errorList = (ArrayList<String>) (request.getAttribute("errorList"));
		this.request = request;
	}

	/**
	 * 获取所有事件信息
	 * 
	 * @return events List\<Event\>
	 */
	public List<Event> list() {
		List<Event> events = null;
		try {
			events = eventService.list();
		} catch (QueryException e) {
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
		return events;
	}

}
