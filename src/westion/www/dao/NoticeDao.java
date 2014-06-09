package westion.www.dao;

import westion.www.entity.Notice;

/**
 * 
 * 数据库notice表Dao类接口
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * 
 */
public interface NoticeDao {

	Notice getNew();


	void delete(Integer id);

	void add(String ncontent, String destination_url, String nphoto_url,
			Long create_time);


	Notice findById(Integer id);


	void update(Integer id, String ncontent, String destination_url,
			String nphoto_url, Long create_time);
}
