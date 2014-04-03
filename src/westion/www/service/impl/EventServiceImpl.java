package westion.www.service.impl;

import java.text.ParseException;
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
import westion.www.utls.JdbcUtls;

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
	public void add(String econtent, String ephoto_url, String etime) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(etime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date.getTime());
		eventDao.add(econtent, ephoto_url, date.getTime(), new Date().getTime());
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
	public void update(Integer id, String econtent, String ephoto_url,
			Integer etime, Integer create_time) {
		eventDao.update(id, econtent, ephoto_url, etime, create_time);
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
