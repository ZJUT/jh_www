package westion.www.dao;

import westion.www.entity.Notice;

/**
 * 
 * ���ݿ�notice��Dao��ӿ�
 * 
 * @version 1.0, 2014-3-20
 * @author westion
 * @since JDK1.7
 * 
 */
public interface NoticeDao {

	Notice getNew();

	void add(String ncontent, String destination_url, String nphoto_url,
			Integer create_time);

	void delete(Integer id);

	void update(Integer id, String ncontent, String destination_url,
			String nphoto_url, Integer create_time);
}
