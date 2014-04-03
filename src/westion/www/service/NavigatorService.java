package westion.www.service;

import java.util.List;

import westion.www.entity.Navigator;

public interface NavigatorService {
	List<Navigator> list();
	void add(String naname, String destination_url);

	void delete(Integer id);

	void update(Integer id, String naname, String destination_url);
}
