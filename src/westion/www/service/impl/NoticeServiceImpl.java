package westion.www.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import westion.www.dao.NoticeDao;
import westion.www.dao.impl.NoticeDaoImpl;
import westion.www.entity.Notice;
import westion.www.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {

	/** Dao层对应操作实例 {@link NoticeDaoImpl#NoticeDaoImpl()} */
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
			Integer create_time) {
		noticeDao.add(ncontent, destination_url, nphoto_url, create_time);

	}

	@Override
	public void delete(Integer id) {
		noticeDao.delete(id);

	}

	@Override
	public void update(Integer id, String ncontent, String destination_url,
			String nphoto_url, Integer create_time) {
		noticeDao.update(id, ncontent, destination_url, nphoto_url, create_time);

	}

	/**
	 * 格式化对象的时间
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
}
