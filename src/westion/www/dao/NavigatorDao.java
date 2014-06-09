package westion.www.dao;

import java.util.List;

import westion.www.entity.Navigator;

/**
 * 
 * 数据库navigator表Dao类接口
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * 
 */
public interface NavigatorDao {

	List<Navigator> list();

	void delete(Integer id);


	Navigator findById(Integer eid);

	void add(String naname, String destination_url, Integer weight);

	void update(Integer id, String naname, String destination_url,
			Integer weight);

}
