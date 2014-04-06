package westion.www.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import westion.www.dao.NoticeDao;
import westion.www.dao.impl.NoticeDaoImpl;
import westion.www.entity.Notice;
import westion.www.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {

	/** Dao���Ӧ����ʵ�� {@link NoticeDaoImpl#NoticeDaoImpl()} */
	private NoticeDao noticeDao = new NoticeDaoImpl();

	@Override
	public Notice getNew() {
		List<Notice> list = new ArrayList<Notice>();
		list.add(noticeDao.getNew());
		Notice notice = formatTime(list).get(0);
		return notice;
	}

	@Override
	public void add(String ncontent, String destination_url, String nphoto_url,
			Long create_time) {
		if (create_time == null) {
			create_time = new Date().getTime();
		}
		noticeDao.add(ncontent, destination_url, nphoto_url, create_time);

	}

	@Override
	public void delete(Integer id) {
		noticeDao.delete(id);

	}

	@Override
	public void update(Integer id, String ncontent, String destination_url,
			String nphoto_url, Long create_time) {
		if (create_time == null) {
			create_time = new Date().getTime();
		}
		noticeDao.update(id, ncontent, destination_url, nphoto_url, create_time);

	}

	/**
	 * ��ʽ�������ʱ��
	 * 
	 * @param notices
	 *            List\<Notice\>
	 * @return List\<Notice\>
	 */
	private List<Notice> formatTime(List<Notice> notices) {
		Notice notice = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < notices.size(); i++) {
			notice = notices.get(i);
			notice.setFormatTime(format.format(notice.getCreate_time()));
			;
		}
		return notices;
	}

	
	@Override
	public Notice findById(Integer id) {
		return noticeDao.findById(id);
	}
}
