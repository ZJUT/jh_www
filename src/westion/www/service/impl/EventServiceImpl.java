package westion.www.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import westion.www.dao.EventDao;
import westion.www.dao.impl.EventDaoImpl;
import westion.www.entity.Event;
import westion.www.exception.AddException;
import westion.www.exception.DeleteException;
import westion.www.exception.UpdateException;
import westion.www.service.EventService;


/**
 * 
 * ���ݿ�event��Service��ӿ�ʵ��
 * 
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 */
public class EventServiceImpl implements EventService {

	/** Dao���Ӧ����ʵ�� {@link EventDaoImpl#EventDaoImpl()} */

	private EventDao eventDao = new EventDaoImpl();

	/**
	 * ��ȡ�����¼���Ϣ
	 * 
	 * @return events List\<Event\>
	 */
	@Override
	public List<Event> list() {
		return formatTime(eventDao.list());
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
	 * @throws AddException
	 *             ���ʧ��
	 * 
	 * */
	@Override
	public void add(String econtent, String ephoto_url, Long etime,Long create_time) {

		if (create_time == null) {
			create_time = new Date().getTime();
		}
		eventDao.add(econtent, ephoto_url, etime,create_time);
	}

	/**
	 * ɾ��һ���¼�
	 * 
	 * @param Integer
	 *            id �¼���id
	 * @throws DeleteException
	 *             ɾ��ʧ��
	 * 
	 * */
	@Override
	public void delete(Integer id) {
		eventDao.delete(id);
	}

	/**
	 * �޸�һ���¼�
	 * 
	 * @param Integer
	 *            id �¼���id
	 * @param econtent
	 *            String �¼�����
	 * @param ephoto_url
	 *            String �¼�ͼƬ
	 * @param etime
	 *            Integer �¼���ʱ��
	 * @param create_time
	 *            Integer �¼�����ʱ��
	 * @throws UpdateException
	 *             ����ʧ��
	 * 
	 * */
	@Override
	public void update(Integer eid, String econtent, String ephoto_url,
			Long etime,Long create_time) {
		if (create_time == null) {
			create_time = new Date().getTime();
		}
		eventDao.update(eid, econtent, ephoto_url,etime,create_time);

	}

	/**
	 * ��ȡ�����¼�ͨ������id
	 * 
	 * @param id
	 *           Integer
	 * @return Event
	 */
	@Override
	public Event findById(Integer id) {
		Event event = eventDao.findById(id);
		return event;
	}


	/**
	 * ��ʽ�������ʱ��
	 * 
	 * @param events
	 *            List\<Event\>
	 * @return List\<Event\>
	 */
	private List<Event> formatTime(List<Event> events) {
		Event event = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < events.size(); i++) {
			event = events.get(i);
			event.setFormatTime_etime(format.format(event.getEtime()));
			event.setFormatTime_create_time(format.format(event
					.getCreate_time()));
		}
		return events;
	}



}
