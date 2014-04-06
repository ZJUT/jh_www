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
 * 数据库event表Service类接口实现
 * 
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 */
public class EventServiceImpl implements EventService {

	/** Dao层对应操作实例 {@link EventDaoImpl#EventDaoImpl()} */

	private EventDao eventDao = new EventDaoImpl();

	/**
	 * 获取所有事件信息
	 * 
	 * @return events List\<Event\>
	 */
	@Override
	public List<Event> list() {
		return formatTime(eventDao.list());
	}

	/**
	 * 增加一条事件
	 * 
	 * @param econtent
	 *            String 事件内容
	 * @param ephoto_url
	 *            String 事件图片
	 * @param etime
	 *            Integer 事件的时间
	 * @param create_time
	 *            Integer 事件发布时间
	 * @throws AddException
	 *             添加失败
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
	 * 删除一条事件
	 * 
	 * @param Integer
	 *            id 事件主id
	 * @throws DeleteException
	 *             删除失败
	 * 
	 * */
	@Override
	public void delete(Integer id) {
		eventDao.delete(id);
	}

	/**
	 * 修改一条事件
	 * 
	 * @param Integer
	 *            id 事件主id
	 * @param econtent
	 *            String 事件内容
	 * @param ephoto_url
	 *            String 事件图片
	 * @param etime
	 *            Integer 事件的时间
	 * @param create_time
	 *            Integer 事件发布时间
	 * @throws UpdateException
	 *             更新失败
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
	 * 获取单个事件通过主键id
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
	 * 格式化对象的时间
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
