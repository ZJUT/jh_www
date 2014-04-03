package westion.www.service;

import westion.www.entity.Notice;

public interface NoticeService {
	Notice getNew();

	void delete(Integer id);

	void update(Integer id, String ncontent, String destination_url,
			String nphoto_url, Integer create_time);

	void add(String ncontent, String destination_url, String nphoto_url,
			Integer create_time);
}
