package westion.www.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import westion.www.dao.EventDao;
import westion.www.dao.impl.EventDaoImpl;
import westion.www.entity.Event;
import westion.www.exception.AddException;
import westion.www.exception.QueryException;
import westion.www.service.EventService;
import westion.www.service.impl.EventServiceImpl;

/**
 * 
 * �¼�ҵ����
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * @see westion.www.dao.EventDao
 */
public class EventAction {
	/** ������� */
	private Map<String, String[]> params = null;
	/** ��վȫ�ֵ������ļ� */
	private Properties properties = null;

	private ArrayList<String> errorList = null;

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

	/**
	 * ����һ���¼�
	 * 
	 * @param econtent
	 *            String �¼�����
	 * @param ephoto_url
	 *            String �¼�ͼƬ
	 * @param etime
	 *            Integer �¼���ʱ��
	 * @param create_time
	 *            Integer �¼�����ʱ��
	 * 
	 * */
	public void addEvent() {
		try {
			eventService.add(params.get("econtent")[0],
					params.get("ephoto_url")[0],
					Integer.parseInt(params.get("etime")[0]),
					Integer.parseInt(params.get("create_time")[0]));
		} catch (AddException e) {
			errorList.add(properties.getProperty("sqlError"));
		} catch (Exception e) {
			errorList.add(properties.getProperty("unknownError"));
		}
	}

}
