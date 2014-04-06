package westion.www.service;

import java.util.List;

import westion.www.entity.Event;


/**
 * 
 * 数据库event表Service类接口
 * 
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 * 
 */
public interface EventService {

	List<Event> list();

	void delete(Integer id);

	Event findById(Integer id);


	void add(String econtent, String ephoto_url, Long etime,Long create_time);

	void update(Integer eid, String econtent, String ephoto_url, Long etime,
			Long create_time);
}
