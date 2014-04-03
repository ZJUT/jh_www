package westion.www.dao;

import java.util.List;

import westion.www.entity.Event;

/**
 * 
 * 数据库event表Dao类接口
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * 
 */
public interface EventDao  {

	List<Event> list();
	
	void delete(Integer id);
	
	void update(Integer id,String econtent,String ephoto_url,Integer etime,Integer create_time);

	void add(String econtent, String ephoto_url, long time, long time2);
}
