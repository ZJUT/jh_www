package westion.www.service;

import java.util.List;

import westion.www.entity.Event;

public interface EventService {

	List<Event> list();

	void delete(Integer id);

	void update(Integer id, String econtent, String ephoto_url, Integer etime,
			Integer create_time);

	void add(String econtent, String ephoto_url, Integer etime,
			Integer create_time);
}
