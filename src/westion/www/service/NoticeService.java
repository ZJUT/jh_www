package westion.www.service;

import westion.www.entity.Notice;


/**
 * 
 * 数据库notice表Service类接口
 * 
 * @version 1.0, 2014-4-6
 * @author westion
 * @since JDK1.7
 * 
 */
public interface NoticeService {
	Notice getNew();

	void delete(Integer id);


	void add(String ncontent, String destination_url, String nphoto_url,
			Long create_time);

	Notice findById(Integer id);

	void update(Integer id, String ncontent, String destination_url,
			String nphoto_url, Long create_time);
}
