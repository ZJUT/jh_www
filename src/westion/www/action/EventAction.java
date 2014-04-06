package westion.www.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import westion.www.entity.Event;
import westion.www.exception.QueryException;
import westion.www.service.EventService;
import westion.www.service.impl.EventServiceImpl;

/**
 * 
 * �¼�������
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.dao.EventService
 */
public class EventAction {
	/** ������� */
	private Map<String, String[]> params = null;
	/** ��վȫ�ֵ������ļ� */
	private Properties properties = null;
	/** ȫ�ִ�Ž�� */
	private ArrayList<String> errorList = null;
	
	/** service�� */
	private EventService eventService = new EventServiceImpl();


	/**
	 * ���캯������request�л�ȡ��Ӧ�����ԣ�����˽�г�Ա��ʼ��
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
	}

	/**
	 * ���һ���¼�
	 * @return event Event
	 * */
	public Event getEvent() {
		Event event = null;
		try {
			event = eventService
					.findById(Integer.parseInt(params.get("eid")[0]));
		} catch (QueryException e) {
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
		return event;
	}

	/**
	 * ��ȡ�����¼���Ϣ
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
