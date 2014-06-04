package westion.www.service;

import java.util.List;

import westion.www.entity.Navigator;


/**
 * 
 * 数据库navigator表Service类接口
 * 
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 * 
 */
public interface NavigatorService {
	List<Navigator> list();
	void delete(Integer id);
	Navigator findById(Integer id);
	void add(String naname, String destination_url, Integer weight);
	void update(Integer id, String naname, String destination_url,
			Integer weight);
}
