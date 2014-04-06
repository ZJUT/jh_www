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
	void add(String naname, String destination_url);

	void delete(Integer id);

	void update(Integer id, String naname, String destination_url);
	Navigator findById(Integer id);
}
