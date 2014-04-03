package westion.www.dao;

import java.util.List;

import westion.www.entity.Navigator;

/**
 * 
 * ���ݿ�navigator��Dao��ӿ�
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * 
 */
public interface NavigatorDao {

	List<Navigator> list();

	void add(String naname, String destination_url);

	void delete(Integer id);

	void update(Integer id, String naname, String destination_url);

}
